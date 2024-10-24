package com.hoshino.cti.mixin;

import com.hoshino.cti.Entity.Systems.EnvironmentSystem;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.hoshino.cti.Entity.Systems.EnvironmentSystem.getFreezeResistance;


@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(at = @At(value = "HEAD"), method = "tick")
    public void CtiTick(CallbackInfo callbackInfo){
        LivingEntity entity = ((LivingEntity) (Object) this);
        Level level = entity.level;
        if (entity instanceof Player player && (player.isCreative() || player.isSpectator())) {
            return;
        }
        if (!(entity instanceof Player)&&entity!=null&&entity.getPersistentData().getBoolean("vulnerable")){
            entity.invulnerableTime=0;
        }
        if (!level.isClientSide) {
            if (level.getGameTime()%10==0){
                EnvironmentSystem.EnvironmentTick(entity,(ServerLevel) level);
            }
            if (getFreezeResistance(entity)>0.5&&entity instanceof Player){
                entity.setIsInPowderSnow(false);
                entity.setTicksFrozen(-10);
            }
        }
    }
}
