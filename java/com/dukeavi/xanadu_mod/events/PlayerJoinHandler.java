package com.dukeavi.xanadu_mod.events;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

/**
 * Handles events when a player joins the server
 */
public class PlayerJoinHandler {
    
    public static void register() {
        ServerPlayConnectionEvents.JOIN.register(PlayerJoinHandler::onPlayerJoin);
    }
    
    private static void onPlayerJoin(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        ServerPlayerEntity player = handler.getPlayer();
        
        // Send a welcome message to the player
        player.sendMessage(Text.literal("§6Welcome to Xanadu! §rYou'll get reminders about when to sleep and wake up!"), false);
        
        // Get the current time to determine which message to show
        long timeOfDay = server.getOverworld().getTimeOfDay() % 24000;
        boolean isNightTime = timeOfDay >= 13000 && timeOfDay < 23000;
        
        // Send the appropriate message based on time of day
        if (isNightTime) {
            sendActionBar(player, "Sleep before the monsters get you");
        } else {
            sendActionBar(player, "You're gonna be late for school");
        }
    }

    private static void sendActionBar(ServerPlayerEntity player, String message) {
        player.sendMessage(Text.literal(message), true);
    }
}
