package com.dukeavi.xanadu_mod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.dukeavi.xanadu_mod.Items.SleepButton;

import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

@Mixin(ItemFrameEntity.class)
public class ItemFrameEntityMixin {
    
    @Inject(at = @At("HEAD"), method = "interact", cancellable = true)
    private void onInteract(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemFrameEntity entity = (ItemFrameEntity) (Object) this;
        
        // If the player is not holding anything, try to use the item in the item frame
        if (player.getStackInHand(hand).isEmpty()) {
            ActionResult result = SleepButton.onUseItemFrame(player, player.getWorld(), entity);
            
            // If the sleep button handled this interaction, cancel the original behavior
            if (result != ActionResult.PASS) {
                cir.setReturnValue(result);
            }
        }
    }
}
