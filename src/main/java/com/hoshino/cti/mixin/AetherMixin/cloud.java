package com.hoshino.cti.mixin.AetherMixin;

import com.aetherteam.aether.entity.projectile.ZephyrSnowball;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ZephyrSnowball.class,remap = false)
public abstract class cloud extends Fireball {
    public cloud(EntityType<? extends Fireball> p_37006_, Level p_37007_) {
        super(p_37006_, p_37007_);
    }

    @Inject(at = @At("HEAD"),method = "m_8119_",cancellable = true)
    private void tick(CallbackInfo ci){
        this.discard();
        ci.cancel();
    }
}