package org.btwr.tough_environment.util;

import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import org.btwr.tough_environment.config.TEModConfig;


public class PlayerEffectsManager {

    private static final PlayerEffectsManager INSTANCE = new PlayerEffectsManager();

    private PlayerEffectsManager() {}

    public static PlayerEffectsManager getInstance() {
        return INSTANCE;
    }

    // gets called in PlayerEntity only
    public void onTick(PlayerEntity player) {
    }

    // gets called in ServerPlayerEntity only
    public void onServerTick(PlayerEntity player) {
        this.updateAttributes(player);
    }

    // Determines if debuffs should affect player
    public boolean shouldBeAffected(PlayerEntity player) {
        return (!player.isCreative() && !player.isSpectator() && !player.isDead());
    }

    /**
     * Brings the player's block break speed modifier in line with the state they should be in right
     * now.
     * <p>
     * This is deliberately stateless. The wanted
     * state is derived from the player and compared against what is actually on the
     * attribute.
     */
    private void updateAttributes(PlayerEntity player) {
        EntityAttributeInstance blockBreakSpeedAttribute = player.getAttributeInstance(EntityAttributes.PLAYER_BLOCK_BREAK_SPEED);

        if (blockBreakSpeedAttribute == null) {
            return;
        }

        // Revert if the player shouldn't be affected at this time
        if (!TEModConfig.hcPlayerMiningSpeed.get()) {
            blockBreakSpeedAttribute.removeModifier(BlockBreakSpeedManager.SPEED_MODIFIER_ID);
            return;
        }

        EntityAttributeModifier wanted = BlockBreakSpeedManager.SpeedState.getStateFrom(player).getModifier();
        EntityAttributeModifier applied = blockBreakSpeedAttribute.getModifier(BlockBreakSpeedManager.SPEED_MODIFIER_ID);

        if (applied != null) {
            // Both states share one id, so only the value tells us which one is currently applied.
            if (applied.value() == wanted.value() && applied.operation() == wanted.operation()) {
                return;
            }
            blockBreakSpeedAttribute.removeModifier(BlockBreakSpeedManager.SPEED_MODIFIER_ID);
        }

        // Temporary rather than persistent: the value is derived from what the player is holding and
        // is recomputed every tick, so saving it to the player's data only risks it outliving the
        // config that asked for it.
        blockBreakSpeedAttribute.addTemporaryModifier(wanted);
    }

}