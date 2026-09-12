package org.btwr.tough_environment.block.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class LavaPillowBlock extends Block {

    public LavaPillowBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        this.setBlockToLava(world, pos);
    }

    public void setBlockToLava(World world, BlockPos pos) {
        if (world.isAir(pos)) {
            world.playSound(
                    null,
                    pos,
                    SoundEvents.BLOCK_FIRE_EXTINGUISH,
                    SoundCategory.BLOCKS,
                    0.5f,
                    2.6F + (world.getRandom().nextFloat() - world.getRandom().nextFloat()) * 0.8F
            );

            if (!hasWaterToSidesOrTop(world, pos)) {
                int decayLevel = 7;

                BlockState flowingLava = Fluids.FLOWING_LAVA.getFlowing(decayLevel, false).getBlockState();
                world.setBlockState(pos, flowingLava, Block.NOTIFY_ALL);

                decayLevel--;

                // Spread additional lava in the horizontal directions around the center block
                for (Direction facing : Direction.Type.HORIZONTAL) {
                    BlockPos neighbour = pos.offset(facing);

                    if (world.isAir(neighbour)) {
                        BlockState neighbourLava = Fluids.FLOWING_LAVA.getFlowing(decayLevel, false).getBlockState();
                        world.setBlockState(neighbour, neighbourLava);
                    }
                }
            }
        }
    }

    public boolean hasWaterToSidesOrTop(World world, BlockPos pos) {
        for (Direction facing : new Direction[]{Direction.UP, Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST}) {
            BlockPos neighbour = pos.offset(facing);
            FluidState fluidState = world.getFluidState(neighbour);

            if (fluidState.isOf(Fluids.WATER) || fluidState.isOf(Fluids.FLOWING_WATER)) {
                return true;
            }
        }

        return false;
    }

}