package org.btwr.tough_environment.mixin.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.btwr.tough_environment.block.ModBlocks;
import org.btwr.tough_environment.config.TEModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FluidBlock.class)
public abstract class FluidBlockMixin {

    // Modify flowing lava to be set to lava pillow instead of cobblestone when water flows over it
    @Redirect(
            method = "receiveNeighborFluids",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)Z", ordinal = 0)
    )
    private boolean redirectCobblestoneToLavaPillow(World instance, BlockPos pos, BlockState state) {
        boolean isStill = instance.getFluidState(pos).isStill();
        boolean lavaPillowEnabled = TEModConfig.lavaPillowsEnabled.get();

        if (!lavaPillowEnabled) {
           return instance.setBlockState(pos, state);
        }

        Block block = isStill ? Blocks.OBSIDIAN : ModBlocks.LAVA_PILLOW;

        return instance.setBlockState(pos, block.getDefaultState());
    }}
