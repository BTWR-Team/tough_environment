package org.btwr.tough_environment.util;

import net.minecraft.block.BlockState;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import org.btwr.tough_environment.ToughEnvironmentMod;
import org.btwr.tough_environment.config.TEModConfig;

public class BlockBreakSpeedManager {

    /**
     * Every {@link SpeedState} applies its modifier under this single id, so a player can never end
     * up holding two of them at once. The flip side is that a state can only be recognised by the
     * value of the modifier it applied - an id check alone matches any of them.
     */
    public static final Identifier SPEED_MODIFIER_ID = Identifier.of(ToughEnvironmentMod.MOD_ID, "generic_modifier");

    private static final BlockBreakSpeedManager INSTANCE = new BlockBreakSpeedManager();

    public static BlockBreakSpeedManager getInstance() {
        return INSTANCE;
    }

    private BlockBreakSpeedManager() {
        super();
    }

    public float getModifiedSpeed(PlayerEntity player, BlockState state, float currentSpeed) {
        ItemStack stack = player.getMainHandStack();
        float newSpeed;
        newSpeed = currentSpeed;

        // Only apply this logic if the item is a MiningToolItem
        if (stack.getItem() instanceof MiningToolItem) {
            if (!stack.isSuitableFor(state) && TEModConfig.hcPlayerMiningSpeed.get()) {
                newSpeed /= 8F;
            }

            if (isProblemToBreak(state, stack) && TEModConfig.stratificationToughness.get()) {
                newSpeed /= 80F;
            }

            // make it practically impossible(very long)
            if (isUnfeasibleToBreak(state, stack) && TEModConfig.strataBasedBlockBreakingRestrictions.get()) {
                newSpeed /= 8000F;
            }

            return newSpeed;
        }

        return currentSpeed;
    }

    public enum SpeedState {
        HARDCORE(0.16f),
        NORMAL(1.0f);

        private final EntityAttributeModifier genericModifier;

        SpeedState(float modifier) {
            this.genericModifier = new EntityAttributeModifier(
                    SPEED_MODIFIER_ID,
                    modifier - 1.0,
                    EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
            );
        }

        public EntityAttributeModifier getModifier() {
            return genericModifier;
        }

        public static SpeedState getState(int severity) {
            return severity == 1 ? HARDCORE : NORMAL;
        }

        public static SpeedState getStateFrom(PlayerEntity player) {
            int severity = 0;

            ItemStack stack = player.getMainHandStack();

            if (TEModConfig.hcPlayerMiningSpeed.get()) {
                severity = 1;
                if (stack.getItem() instanceof MiningToolItem) {
                    severity = 0;
                }
            }

            return getState(severity);
        }
    }

    public boolean isProblemToBreak(BlockState state, ItemStack stack) {
        if (StrataChecks.isStoneStrata3(state) && !StrataChecks.isAdvancedPickaxe(stack)) {
            return true;
        }

        return StrataChecks.isStoneStrata2(state) && !StrataChecks.isAdvancedPickaxe(stack) && !StrataChecks.isModernPickaxe(stack);
    }
    public boolean isUnfeasibleToBreak(BlockState state, ItemStack stack) {
        return cantBreakStrata3(state, stack) || cantBreakStrata2(state, stack);
    }
    public boolean cantBreakStrata2(BlockState state, ItemStack stack) {
        return StrataChecks.isStoneStrata2(state) & (StrataChecks.isPrimitivePickaxe(stack) || stack.isOf(Items.GOLDEN_PICKAXE));
    }
    public boolean cantBreakStrata3(BlockState state, ItemStack stack) {
        return StrataChecks.isStoneStrata3(state) && !StrataChecks.isAdvancedPickaxe(stack);
    }

}