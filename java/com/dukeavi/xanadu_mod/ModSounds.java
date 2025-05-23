package com.dukeavi.xanadu_mod;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    // Use the Identifier.of method that works in ItemNought.java
    public static final Identifier WAKE_UP_ID = Identifier.of(Xanadumod.MOD_ID, "wake_up");
    public static final Identifier GO_TO_SLEEP_ID = Identifier.of(Xanadumod.MOD_ID, "go_to_sleep");

    public static final SoundEvent WAKE_UP = SoundEvent.of(WAKE_UP_ID);
    public static final SoundEvent GO_TO_SLEEP = SoundEvent.of(GO_TO_SLEEP_ID);

    public static void registerSounds() {
        Registry.register(Registries.SOUND_EVENT, WAKE_UP_ID, WAKE_UP);
        Registry.register(Registries.SOUND_EVENT, GO_TO_SLEEP_ID, GO_TO_SLEEP);
    }
}
