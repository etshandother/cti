package com.hoshino.cti.mixin.TIMixin;

import com.xiaoyue.tinkers_ingenuity.content.tools.item.tinker.BlowPipe;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(value = BlowPipe.class,remap = false)
public class BlowPipeMixin {
    @Inject(method = "m_7203_", at = @At("HEAD"), cancellable = true)
    private void addCooldown(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir){
        ItemStack itemStack =player.getItemInHand(hand);
        if (player.getCooldowns().isOnCooldown(itemStack.getItem())){
            cir.setReturnValue(InteractionResultHolder.fail(itemStack));
        }
        player.getCooldowns().addCooldown(itemStack.getItem(),4);
    }
}