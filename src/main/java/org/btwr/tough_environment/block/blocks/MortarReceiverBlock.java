package org.btwr.tough_environment.block.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.tick.OrderedTick;
import net.minecraft.world.tick.TickPriority;
import org.btwr.tough_environment.block.ModBlocks;
import org.btwr.tough_environment.sound.ModSoundEvents;
import org.btwr.tough_environment.tag.ModTags;
import org.btwr.tough_environment.util.BlockMortarMapper;

import java.util.Objects;

public class MortarReceiverBlock extends FallingBlock {

    private static final int TACKY_FALLING_BLOCK_TICK_RATE = 40;

    public static final MapCodec<MortarReceiverBlock> CODEC = MortarReceiverBlock.createCodec(MortarReceiverBlock::new);

    @Override
    protected MapCodec<? extends FallingBlock> getCodec() {
        return CODEC;
    }

    public MortarReceiverBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        boolean hasMortaredNeighbor = Direction.stream()
                .map(pos::offset)
                .map(world::getBlockState)
                .anyMatch(neighborState -> neighborState.isIn(ModTags.Blocks.MORTARED_BLOCKS));

        if (hasMortaredNeighbor) {
            // Create an OrderedTick for the block
            OrderedTick<Block> orderedTick = new OrderedTick<>(
                    state.getBlock(),
                    pos,
                    world.getTime() + TACKY_FALLING_BLOCK_TICK_RATE,
                    TickPriority.NORMAL,
                    0
            );
            world.getBlockTickScheduler().scheduleTick(orderedTick);
        } else {
            // Schedule the normal fall tick (default for falling blocks)
            super.onBlockAdded(state, world, pos, oldState, notify);
        }
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            // Check both hands to avoid shield interference
            ItemStack mainHandStack = player.getStackInHand(Hand.MAIN_HAND);
            ItemStack offHandStack = player.getStackInHand(Hand.OFF_HAND);

            if (mainHandStack.isIn(ModTags.Items.MORTARING_ITEMS)) {
                return applyMortar(state, world, pos, player, Hand.MAIN_HAND);
            }
            else if (offHandStack.isIn(ModTags.Items.MORTARING_ITEMS)) {
                return applyMortar(state, world, pos, player, Hand.OFF_HAND);
            }
        }

        return ActionResult.PASS;
    }

    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (state.isIn(ModTags.Blocks.LAVA_LOGGABLE)) {
            Block block = state.getBlock();

            if (hasLavaAbove(world, pos)) {
                // Transition to the lava-filled variant of the block
                if (block == ModBlocks.COBBLESTONE_LOOSE) {
                    world.setBlockState(pos, ModBlocks.LAVA_FILLED_COBBLESTONE_LOOSE.getDefaultState());
                    return;
                }

                if (block == ModBlocks.COBBLED_DEEPSLATE_LOOSE) {
                    world.setBlockState(pos, ModBlocks.LAVA_FILLED_COBBLED_DEEPSLATE_LOOSE.getDefaultState());
                    return;
                }

                if (block == ModBlocks.STONE_BRICKS_LOOSE) {
                    world.setBlockState(pos, ModBlocks.LAVA_FILLED_STONE_BRICKS_LOOSE.getDefaultState());
                    return;
                }

                if (block == ModBlocks.DEEPSLATE_BRICKS_LOOSE) {
                    world.setBlockState(pos, ModBlocks.LAVA_FILLED_DEEPSLATE_BRICKS_LOOSE.getDefaultState());
                }
            }
        }

        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
    }

    // Helper method to apply the mortar logic for a specific hand
    private ActionResult applyMortar(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand) {
        this.tryApplyMortar(state, world, pos, player);
        return ActionResult.CONSUME;
    }

    protected void tryApplyMortar(BlockState oldState, World world, BlockPos pos, PlayerEntity player) {
        Hand hand = player.getActiveHand();
        BlockState mortaredState = getMortaredState(oldState);

        if (mortaredState != null) {
            world.setBlockState(pos, mortaredState, Block.NOTIFY_ALL);
            world.playSound(null, pos, ModSoundEvents.APPLY_MORTAR, SoundCategory.BLOCKS);
        }

        // Reduce item stack size if not in creative mode
        if (!player.isCreative()) {
            player.getStackInHand(hand).decrement(1);
        }

        player.swingHand(hand);
    }

    /**
     * Returns the mortared version of this block state.
     * Default implementation uses the mapper, but other blocks can override
     * to set custom properties on the resulting state.
     */
    protected BlockState getMortaredState(BlockState state) {
        Block replacement = Objects.requireNonNull(BlockMortarMapper.getReplacement(state)).getBlock();
        if (replacement != null) {
            return replacement.getStateWithProperties(state);
        }
        return state; // fallback if no replacement exists
    }

    private boolean hasLavaAbove(World world, BlockPos pos) {
        FluidState fluidAbove = world.getFluidState(pos.offset(Direction.UP));
        return fluidAbove.isOf(Fluids.LAVA) || fluidAbove.isOf(Fluids.FLOWING_LAVA);
    }

}