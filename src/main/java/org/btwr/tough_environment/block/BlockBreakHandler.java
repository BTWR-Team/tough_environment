package org.btwr.tough_environment.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.btwr.shared_library.api.tag.BTWRConventionalTags;
import org.btwr.tough_environment.block.blocks.DepletedStoneBlock;
import org.btwr.tough_environment.block.blocks.StoneConvertingBlock;
import org.btwr.tough_environment.tag.ModTags;

import java.util.HashMap;
import java.util.Map;

import static org.btwr.shared_library.api.tag.BTWRConventionalTags.Blocks.LOOSEN_ON_IMPROPER_BREAK;
import static org.btwr.shared_library.api.tag.BTWRConventionalTags.Blocks.LOOSEN_ON_IMPROPER_BREAK_SLABS;
import static org.btwr.shared_library.api.tag.BTWRConventionalTags.Items.PICKAXES_HARVEST_FULL_BLOCK;
import static org.btwr.shared_library.api.tag.BTWRConventionalTags.Items.SHOVELS_HARVEST_FULL_BLOCK;
import static org.btwr.tough_environment.block.blocks.ConvertingBlock.BREAK_LEVEL;

/** Manages logic for vanilla blocks affected by BTWR breaking mechanics. */
public class BlockBreakHandler {

    private static final BlockBreakHandler instance = new BlockBreakHandler();

    public BlockBreakHandler() {}

    public static BlockBreakHandler getInstance() {
        return instance;
    }

    public void setStateForStone(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        BlockState converted = getNextStateForVanillaStone(state, player.getMainHandStack());
        if (converted == null) return;
        if (!player.isCreative()) {
            world.setBlockState(pos, converted, Block.NOTIFY_LISTENERS);
        } else {
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
    }

    public void setStateForConvertedStone(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (state.getBlock() instanceof StoneConvertingBlock) {
            BlockState converted = getNextStateForModdedStone(state, player.getMainHandStack());
            if (converted == null) return;
            if (!player.isCreative()) {
                world.setBlockState(pos, converted, Block.NOTIFY_LISTENERS);
            } else {
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        }
    }

    public void setStateForDirt(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (state.isIn(LOOSEN_ON_IMPROPER_BREAK) || state.isIn(LOOSEN_ON_IMPROPER_BREAK_SLABS)) {
            ItemStack tool = player.getMainHandStack();
            if (tool.isIn(SHOVELS_HARVEST_FULL_BLOCK) || player.isCreative()) {
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            } else {
                boolean isUpsideDown = state.isIn(LOOSEN_ON_IMPROPER_BREAK_SLABS) && state.get(Properties.SLAB_TYPE) == SlabType.TOP;
                if (state.getBlock() == ModBlocks.DIRT_LOOSE) return;
                onDirtDugWithImproperTool(world, pos);
                onDirtSlabDugWithImproperTool(world, pos, isUpsideDown);
            }
        }
    }

    /**
     * Notifies neighbors of dirt blocks that they should be loosened
     */
    protected void onDirtDugWithImproperTool(World world, BlockPos pos) {
        for (Direction facing : Direction.values()) {
            notifyNeighborDirtDugWithImproperTool(world, pos, facing);
        }
    }

    protected void onDirtSlabDugWithImproperTool(World world, BlockPos pos, boolean isUpsideDown) {
        for (Direction facing : Direction.values()) {
            if (!(isUpsideDown && facing == Direction.DOWN) && !(!isUpsideDown && facing == Direction.UP)) {
                notifyNeighborDirtDugWithImproperTool(world, pos, facing);
            }
        }
    }

    protected void notifyNeighborDirtDugWithImproperTool(World world, BlockPos pos, Direction facing) {
        BlockPos neighborPos = pos.offset(facing);

        BlockState state = world.getBlockState(neighborPos);

        if (state != null) {
            onNeighborDirtDugWithImproperTool(world, neighborPos, facing.getOpposite());
        }
    }

    protected void onNeighborDirtDugWithImproperTool(World world, BlockPos neighborPos, Direction facing) {
        BlockState neighborState = world.getBlockState(neighborPos);

        if (!neighborState.isIn(LOOSEN_ON_IMPROPER_BREAK) && !neighborState.isIn(LOOSEN_ON_IMPROPER_BREAK_SLABS)) {
            return;
        }

        if (neighborState.isOf(Blocks.GRASS_BLOCK) || neighborState.isOf(Blocks.MYCELIUM)) {
            if (facing == Direction.DOWN) {
                setToLooseDirt(world, neighborPos);
            }
        } else {
            setToLooseDirt(world, neighborPos);
        }
    }

    private void setToLooseDirt(World world, BlockPos neighborPos) {
        world.setBlockState(neighborPos, ModBlocks.DIRT_LOOSE.getDefaultState(), Block.NOTIFY_LISTENERS);
    }

    // Returns the next converted state from vanilla blocks
    public BlockState getNextStateForVanillaStone(BlockState state, ItemStack tool) {
        if (state.isIn(ModTags.Blocks.STONE_ORES)) {
            return getConvertedOreState(state, tool, ModBlocks.STONE_CONVERTING);
        }

        if (state.isIn(ModTags.Blocks.DEEPSLATE_ORES)) {
            return getConvertedOreState(state, tool, ModBlocks.DEEPSLATE_CONVERTING);
        }

        return getConvertedStoneState(state, tool);
    }
    
    public BlockState getNextStateForModdedStone(BlockState state, ItemStack tool) {
        int breakLevel = state.get(BREAK_LEVEL);
        boolean isModernChisel = tool.isIn(BTWRConventionalTags.Items.MODERN_CHISELS);
        boolean isAdvancedChisel = tool.isIn(BTWRConventionalTags.Items.ADVANCED_CHISELS);
        boolean isPrimitivePickaxe = tool.isIn(BTWRConventionalTags.Items.PRIMITIVE_PICKAXES);
        boolean isModernPickaxe = tool.isIn(BTWRConventionalTags.Items.MODERN_PICKAXES);
        boolean isAdvancedPickaxe = tool.isIn(BTWRConventionalTags.Items.ADVANCED_PICKAXES);

        if ((isAdvancedPickaxe || isModernPickaxe) || (isPrimitivePickaxe && breakLevel >= 5)) {
            return Blocks.AIR.getDefaultState();
        }

        if (isPrimitivePickaxe) {
            return state.with(BREAK_LEVEL, 5);
        }

        if (isModernChisel || isAdvancedChisel) {
            // Calculate the new break level
            int nextBreakLevel = Math.min(breakLevel + 2, 8);

            // If break level is greater or equal to 7, set to broken state;
            // otherwise, update the block state
            if (breakLevel >= 7) {
                return ModBlocks.STONE_BROKEN.getDefaultState();
            } else {
                return state.with(BREAK_LEVEL, nextBreakLevel);
            }
        }

        if (breakLevel >= 8 && !(state.getBlock() instanceof DepletedStoneBlock)) {

            if (state.isIn(ModTags.Blocks.CONVERTED_STONE_BLOCKS)) {
                return ModBlocks.STONE_BROKEN.getDefaultState();
            }
        }

        return this.getNextStateForConvertedBlocks(state);
    }

    // Returns the next converted state from general converted blocks
    private BlockState getNextStateForConvertedBlocks(BlockState state) {
        // Increment to the next break level
        return state.with(BREAK_LEVEL, state.get(BREAK_LEVEL) + 1);
    }

    public boolean shouldPlayCrackingSound(BlockState state, ItemStack tool) {
        boolean isFullyBreakingPickaxe = tool.isIn(PICKAXES_HARVEST_FULL_BLOCK);
        if (state.getBlock() instanceof StoneConvertingBlock && !state.isIn(ModTags.Blocks.BROKEN_STONE_BLOCKS)) {
            int breakLevel = state.get(BREAK_LEVEL);

            return ((breakLevel % 2 == 0) && !isFullyBreakingPickaxe) || ((breakLevel == 3) && isModernOrAdvancedChisel(tool));
        }

        if (state.getBlock() instanceof ExperienceDroppingBlock) {
            return !isFullyBreakingPickaxe;
        }

        return state.isIn(BTWRConventionalTags.Blocks.STONE_STRATA1) && (isModernOrAdvancedChisel(tool) || tool.isIn(BTWRConventionalTags.Items.PRIMITIVE_PICKAXES));
    }

    private BlockState getConvertedOreState(BlockState state, ItemStack tool, Block convertingBlock) {
        if (!shouldConvertOre(state, tool)) {
            return Blocks.AIR.getDefaultState();
        }
        return convertingBlock.getDefaultState().with(BREAK_LEVEL, 5);
    }

    private BlockState getConvertedStoneState(BlockState state, ItemStack tool) {
        Map<Block, Block> blockMap = new HashMap<>();

        blockMap.put(Blocks.STONE, ModBlocks.STONE_CONVERTING);
        blockMap.put(Blocks.GRANITE, ModBlocks.GRANITE_CONVERTING);
        blockMap.put(Blocks.ANDESITE, ModBlocks.ANDESITE_CONVERTING);
        blockMap.put(Blocks.DIORITE, ModBlocks.DIORITE_CONVERTING);
        blockMap.put(Blocks.CALCITE, ModBlocks.CALCITE_CONVERTING);
        blockMap.put(Blocks.TUFF, ModBlocks.TUFF_CONVERTING);
        blockMap.put(Blocks.BLACKSTONE, ModBlocks.BLACKSTONE_CONVERTING);
        blockMap.put(Blocks.DEEPSLATE, ModBlocks.DEEPSLATE_CONVERTING);
        blockMap.put(Blocks.BASALT, ModBlocks.BASALT_CONVERTING);
        blockMap.put(Blocks.END_STONE, ModBlocks.END_STONE_CONVERTING);

        for (Map.Entry<Block, Block> entry : blockMap.entrySet()) {
            Block originalBlock = entry.getKey();
            Block convertedBlock = entry.getValue();

            if (state.isOf(originalBlock)) {
                return getStoneConvertedState(tool, convertedBlock);
            }
        }

        return null;
    }

    private BlockState getStoneConvertedState(ItemStack tool, Block convertedBlock) {
        BlockState state = convertedBlock.getDefaultState();

        if (tool.isIn(BTWRConventionalTags.Items.ADVANCED_PICKAXES)
                || (tool.isIn(BTWRConventionalTags.Items.MODERN_PICKAXES) && !state.isIn(BTWRConventionalTags.Blocks.STONE_STRATA3))) {
            return Blocks.AIR.getDefaultState();
        }

        if (tool.isIn(BTWRConventionalTags.Items.PRIMITIVE_PICKAXES) && state.get(BREAK_LEVEL) < 5) {
            return state.with(BREAK_LEVEL, 5);
        }

        if (tool.isIn(BTWRConventionalTags.Items.MODERN_CHISELS) || tool.isIn(BTWRConventionalTags.Items.ADVANCED_CHISELS)) {
            return state.with(BREAK_LEVEL, 3);
        }

        return state.with(BREAK_LEVEL, 0);
    }

    private boolean shouldConvertOre(BlockState state, ItemStack tool) {
        return !(tool.isIn(BTWRConventionalTags.Items.ADVANCED_PICKAXES)
                || (tool.isIn(BTWRConventionalTags.Items.MODERN_PICKAXES) && !state.isIn(ModTags.Blocks.DEEPSLATE_ORES)));
    }

    private boolean isModernOrAdvancedChisel(ItemStack tool) {
        return tool.isIn(BTWRConventionalTags.Items.MODERN_CHISELS) || tool.isIn(BTWRConventionalTags.Items.ADVANCED_CHISELS);
    }

}