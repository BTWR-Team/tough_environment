package org.btwr.tough_environment.block.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.btwr.shared_library.api.tag.BTWRConventionalTags;
import org.jetbrains.annotations.Nullable;
import org.btwr.tough_environment.block.ModBlocks;

import java.util.Map;

public class LooseAggregateSlabBlock extends FallingBlock implements Waterloggable {

    public static final EnumProperty<SlabType> TYPE = Properties.SLAB_TYPE;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    protected static final VoxelShape BOTTOM_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);
    protected static final VoxelShape TOP_SHAPE = Block.createCuboidShape(0.0, 8.0, 0.0, 16.0, 16.0, 16.0);

    public static final MapCodec<LooseAggregateSlabBlock> CODEC = LooseAggregateSlabBlock.createCodec(LooseAggregateSlabBlock::new);

    @Override
    protected MapCodec<? extends FallingBlock> getCodec() {
        return CODEC;
    }

    public LooseAggregateSlabBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(TYPE, SlabType.BOTTOM)
                .with(WATERLOGGED, false)
        );
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
    {
        builder.add(TYPE, WATERLOGGED);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack)
    {
        BlockPos downPos = pos.down();
        BlockState downState = world.getBlockState(downPos);

        if (world.getBlockState(downPos) == this.getDefaultState().with(TYPE, SlabType.BOTTOM)) {
            world.setBlockState(downPos, downState.with(TYPE, SlabType.DOUBLE));
            world.removeBlock(pos, true);
        }
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (!world.isClient && entity instanceof PlayerEntity) {
            BlockPos downPos = pos.down();
            BlockState downState = world.getBlockState(downPos);
            if (downState.isOf(this) && downState.get(TYPE) == SlabType.BOTTOM) {
                world.setBlockState(downPos, downState.with(TYPE, SlabType.DOUBLE));
            }
        }
        super.onLandedUpon(world, state, pos, entity, fallDistance);
    }

    // Block specific logic //
    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool)
    {
        // handles the case where the LooseBlock is a DIRT_LOOSE and mined with a hoe
        if (tool.isIn(BTWRConventionalTags.Items.MODERN_HOES) || tool.isIn(BTWRConventionalTags.Items.ADVANCED_HOES)) {
            if (state.isOf(ModBlocks.SLAB_DIRT) && state.get(TYPE) == SlabType.DOUBLE) {
                world.setBlockState(pos, Blocks.FARMLAND.getDefaultState());
            }
        }

        super.afterBreak(world, player, pos, state, blockEntity, tool);
    }

    public boolean hasSidedTransparency(BlockState state) {
        return state.get(TYPE) != SlabType.DOUBLE;
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        SlabType slabType = state.get(TYPE);

        if (slabType == SlabType.DOUBLE) {
            return VoxelShapes.fullCube();
        }
        else if (slabType == SlabType.TOP) {
            return TOP_SHAPE;
        }

        return BOTTOM_SHAPE;
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        BlockState blockState = ctx.getWorld().getBlockState(blockPos);
        FluidState fluidState = ctx.getWorld().getFluidState(blockPos);
        Direction direction = ctx.getSide();

        if (blockState.isOf(this)) {
            // If the block is the same as the LooseSlabBlock, set it to double slab
            //return blockState.with(TYPE, SlabType.DOUBLE).with(WATERLOGGED, false);

            return getReplacementBlockState(blockState.getBlock());
        } else {
            // Otherwise, handle placement based on the direction and hit position
            boolean isTopHalf = ctx.getHitPos().y - blockPos.getY() > 0.5;

            if (direction == Direction.DOWN || isTopHalf) {
                // If placing on the bottom part or top half, place as a bottom slab
                return this.getDefaultState().with(TYPE, SlabType.BOTTOM).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
            }
            else {
                // If placing on the top part, place as a bottom slab instead
                return this.getDefaultState().with(TYPE, SlabType.BOTTOM).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
            }
        }
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        ItemStack itemStack = context.getStack();
        SlabType slabType = state.get(TYPE);
        if (slabType != SlabType.DOUBLE && itemStack.isOf(this.asItem())) {
            if (context.canReplaceExisting()) {
                boolean bl = context.getHitPos().y - (double)context.getBlockPos().getY() > 0.5;
                Direction direction = context.getSide();
                if (slabType == SlabType.BOTTOM) {
                    return direction == Direction.UP || bl && direction.getAxis().isHorizontal();
                } else {
                    return direction == Direction.DOWN || !bl && direction.getAxis().isHorizontal();
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
        return state.get(TYPE) != SlabType.DOUBLE && Waterloggable.super.tryFillWithFluid(world, pos, state, fluidState);
    }

    @Override
    public boolean canFillWithFluid(PlayerEntity player, BlockView world, BlockPos pos, BlockState state, Fluid fluid) {
        return state.get(TYPE) != SlabType.DOUBLE && Waterloggable.super.canFillWithFluid(player, world, pos, state, fluid);
    }

    @Override
    public BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState,
            WorldAccess world, BlockPos pos, BlockPos neighborPos
    ) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return switch (type) {
            case LAND, AIR -> false;
            case WATER -> state.getFluidState().isIn(FluidTags.WATER);
        };
    }

    private BlockState getReplacementBlockState(Block originalBlock) {
        // Mapping of slab blocks to their corresponding mortared Minecraft variant (top or bottom)
        Map<Block, Block> replacementMap = Map.of(
                ModBlocks.SLAB_DIRT, ModBlocks.DIRT_LOOSE,
                ModBlocks.SLAB_GRAVEL, Blocks.GRAVEL,
                ModBlocks.SLAB_SAND, Blocks.SAND,
                ModBlocks.SLAB_RED_SAND, Blocks.RED_SAND
        );

        // Handle DOUBLE slabs by mapping to the full block
        if (replacementMap.containsKey(originalBlock)) {
            return replacementMap.get(originalBlock).getDefaultState(); // Return the full block
        }

        // Return null if no match is found
        return null;
    }

}