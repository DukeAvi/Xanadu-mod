package com.dukeavi.xanadu_mod.events;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import com.dukeavi.xanadu_mod.ModSounds;

public class PlayerWakeUpHandler implements ServerPlayerEvents.AfterRespawn {

    public static void register() {
        ServerPlayerEvents.AFTER_RESPAWN.register(new PlayerWakeUpHandler());
    }

    @Override
    public void afterRespawn(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
        
    }

    public static void onPlayerWakeUp(PlayerEntity player) {
        if (!player.getWorld().isClient && player instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
            serverPlayer.sendMessage(Text.literal("You're gonna be late for school"), true);
            serverPlayer.playSound(ModSounds.WAKE_UP, 1.0f, 1.0f);
        }
    }
}
