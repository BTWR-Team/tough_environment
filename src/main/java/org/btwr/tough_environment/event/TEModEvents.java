package org.btwr.tough_environment.event;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.btwr.shared_library.api.event.ServerChunkGenerateEvents;
import org.btwr.tough_environment.block.ModBlocks;
import org.btwr.tough_environment.sound.ModSoundEvents;
import org.jetbrains.annotations.Nullable;
import org.btwr.tough_environment.block.BlockBreakHandler;

public class TEModEvents {

    public static void register() {
        // Server Events
        ServerChunkGenerateEvents.createChunkReplaceEventGlobally(Blocks.CLAY, ModBlocks.CLAY_ORE);

        // Player Block Events
        PlayerBlockBreakEvents.AFTER.register(TEModEvents::afterBlockBreak);

        // Item Component Events
        //DefaultItemComponentEvents.MODIFY.register(ModEvents::modifyToolComponents);

        // Loot Table Events
        //LootTableEvents.REPLACE...
    }

    private static void afterBlockBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity) {
        if (!world.isClient()) {
            BlockBreakHandler handler = new BlockBreakHandler();
            handler.setStateForDirt(world, pos, state, player);
            handler.setStateForStone(world, pos, state, player);
            handler.setStateForConvertedStone(world, pos, state, player);

            if (handler.shouldPlayCrackingSound(state, player.getMainHandStack()) && !player.isCreative()) {
                world.playSound(null, pos, ModSoundEvents.STONE_CRACKING, SoundCategory.BLOCKS,
                        0.5F, 1.75F + world.random.nextFloat() * 0.25F
                );
            }
        }
    }

}