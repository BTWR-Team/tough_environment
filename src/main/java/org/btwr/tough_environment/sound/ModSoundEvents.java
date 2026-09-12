package org.btwr.tough_environment.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.btwr.shared_library.util.utils.IdUtils;

public class ModSoundEvents {
    public static final SoundEvent STONE_CRACKING = register("stone_cracking");
    public static final SoundEvent APPLY_MORTAR = register("apply_mortar");
    public static final SoundEvent UNFIRED_BRICK_DESTROYED = register("unfired_brick_destroyed");

    private static SoundEvent register(String name) {
        Identifier id = IdUtils.ofTE(name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void register() {}
}
