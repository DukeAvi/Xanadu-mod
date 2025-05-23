package com.dukeavi.xanadu_mod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.dukeavi.xanadu_mod.events.PlayerWakeUpHandler;

import net.minecraft.entity.player.PlayerEntity;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Inject(method = "wakeUp(ZZ)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;sendMessage(Lnet/minecraft/text/Text;Z)V", shift = At.Shift.AFTER, ordinal = 0))
    private void onWakeUp(boolean resetTime, boolean updatePlayers, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        PlayerWakeUpHandler.onPlayerWakeUp(player);
    }
}
