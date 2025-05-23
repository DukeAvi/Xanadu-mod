package com.dukeavi.xanadu_mod.events;

import com.dukeavi.xanadu_mod.ModSounds;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

/**
 * Handles events related to time of day messages for players
 */
public class TimeMessageHandler {
    
    private static final String SLEEP_MESSAGE = "Sleep before the monsters catch you";
    private static final String WAKE_MESSAGE = "Wake up otherwise you're gonna be late!!";
    
    // Keep track of whether messages have been sent to avoid spam
    private static boolean nightMessageSent = false;
    private static boolean dayMessageSent = false;
    private static long lastTimeChecked = 0;
    
    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(TimeMessageHandler::onServerTick);
    }
    
    private static void onServerTick(MinecraftServer server) {
        // Only check once every 100 ticks (5 seconds) to avoid unnecessary processing
        long currentTick = server.getTicks();
        if (currentTick - lastTimeChecked < 100) {
            return;
        }
        lastTimeChecked = currentTick;
        
        // If there are no players online, no need to check
        if (server.getCurrentPlayerCount() == 0) {
            return;
        }
        
        // Check the time in the overworld
        long timeOfDay = server.getOverworld().getTimeOfDay() % 24000;
        
        // Night time starts at 13000
        boolean isNightTime = timeOfDay >= 13000 && timeOfDay < 23000;
        
        // Day time is from 0 to 13000
        boolean isDayTime = timeOfDay >= 0 && timeOfDay < 13000;
        
        // Send night message once when night starts
        if (isNightTime && !nightMessageSent) {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                sendActionBar(player, "Sleep before the monsters get you");
            }
            nightMessageSent = true;
            dayMessageSent = false;
        }
        // Send day message once when day starts
        if (isDayTime && !dayMessageSent) {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                sendActionBar(player, "You're gonna be late for school");
            }
            dayMessageSent = true;
            nightMessageSent = false;
        }
    }

    private static void sendActionBar(ServerPlayerEntity player, String message) {
        player.sendMessage(Text.literal(message), true);
        if (message.toLowerCase().contains("sleep")) {
            player.playSound(ModSounds.GO_TO_SLEEP, 1.0f, 1.0f);
        } else if (message.toLowerCase().contains("late")) {
            player.playSound(ModSounds.WAKE_UP, 1.0f, 1.0f);
        }
    }
}
