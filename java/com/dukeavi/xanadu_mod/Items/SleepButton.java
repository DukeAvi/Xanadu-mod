package com.dukeavi.xanadu_mod.Items;

import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SleepButton extends Item {    public SleepButton(Settings settings) {
        super(settings);
    }
    
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        // When right-clicking with the item itself, don't do anything special
        // The player needs to place it in an item frame first
        player.sendMessage(Text.literal("Place this item in an item frame and right-click the frame to use it!"), true);
        return TypedActionResult.pass(player.getStackInHand(hand));
    }
    
    /**
     * This method handles the interaction with an item frame containing the sleep button
     */
    public static ActionResult onUseItemFrame(PlayerEntity player, World world, ItemFrameEntity itemFrame) {
        // Check if the item inside the frame is a sleep button
        ItemStack frameStack = itemFrame.getHeldItemStack();
        if (!(frameStack.getItem() instanceof SleepButton)) {
            return ActionResult.PASS;
        }
        
        if (!world.isClient) {
            if (world instanceof ServerWorld serverWorld) {
                // Set time to next morning
                long currentTime = serverWorld.getTimeOfDay();
                long nextMorning = currentTime + (24000 - (currentTime % 24000));
                serverWorld.setTimeOfDay(nextMorning);
                
                // Clear weather if it's raining
                if (serverWorld.isRaining()) {
                    serverWorld.setWeather(0, 0, false, false);
                }

                player.sendMessage(Text.literal("Skipped to next morning!"), true);
                return ActionResult.SUCCESS;
            }
        }
        
        return ActionResult.success(world.isClient);
    }
}
