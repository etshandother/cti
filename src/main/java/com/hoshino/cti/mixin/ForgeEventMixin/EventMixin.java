package com.hoshino.cti.mixin.ForgeEventMixin;

import com.hoshino.cti.Entity.specialDamageSource.Environmental;
import com.hoshino.cti.Entity.specialDamageSource.PierceThrough;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.Event;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Event.class, remap = false)
public class EventMixin {
    @Inject(at = @At(value = "HEAD"), method = "setCanceled", cancellable = true)
    private void rejectCancel(boolean cancel, CallbackInfo ci) {
        if (cancel) {
            Event event = (Event) (Object) this;
            if (event instanceof LivingAttackEvent event1) {
                if (event1.getEntity() instanceof Player player && player.isCreative()) {
                    return;
                }
                DamageSource source = event1.getSource();
                if (source instanceof Environmental || source instanceof PierceThrough) {
                    ci.cancel();
                }
            }
            if (event instanceof LivingHurtEvent event1) {
                if (event1.getEntity() instanceof Player player && player.isCreative()) {
                    return;
                }
                DamageSource source = event1.getSource();
                if (source instanceof Environmental || source instanceof PierceThrough) {
                    ci.cancel();
                }
            }
            if (event instanceof LivingDamageEvent event1) {
                if (event1.getEntity() instanceof Player player && player.isCreative()) {
                    return;
                }
                DamageSource source = event1.getSource();
                if (source instanceof Environmental || source instanceof PierceThrough) {
                    ci.cancel();
                }
            }
            if (event instanceof LivingDeathEvent event1) {
                if (event1.getEntity() instanceof Player player && player.isCreative()) {
                    return;
                }
                DamageSource source = event1.getSource();
                if (source instanceof Environmental || source instanceof PierceThrough) {
                    ci.cancel();
                }
            }
        }
    }
}
