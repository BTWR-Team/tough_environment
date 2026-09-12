package org.btwr.tough_environment.event;

import net.fabricmc.fabric.api.event.client.player.ClientPlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import org.btwr.tough_environment.block.BlockBreakHandler;
import org.btwr.tough_environment.block.blocks.StoneConvertingBlock;

public class ModClientEvents {

    public static void register() {
        // Player Block Events
        ClientPlayerBlockBreakEvents.AFTER.register(ModClientEvents::afterBlockBreak);
    }

    // Directly update client world block state to prevent flicker
    private static void afterBlockBreak(ClientWorld world, ClientPlayerEntity player, BlockPos pos, BlockState state) {
        ItemStack tool = player.getMainHandStack();

        BlockState newVanillaStoneState = BlockBreakHandler.getInstance().getNextStateForVanillaStone(state, tool);
        updateStoneState(world, pos, player, newVanillaStoneState);

        if (state.getBlock() instanceof StoneConvertingBlock) {
            BlockState newModdedStoneState = BlockBreakHandler.getInstance().getNextStateForModdedStone(state, tool);
            updateStoneState(world, pos, player, newModdedStoneState);
        }
    }

    private static void updateStoneState(ClientWorld world, BlockPos pos, ClientPlayerEntity player, BlockState state) {
        if (state != null) {
            if (!player.isCreative()) {
                world.setBlockState(pos, state);
            } else {
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        }
    }

}