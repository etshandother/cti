package com.hoshino.cti.Modifier;

import com.aizistral.enigmaticlegacy.handlers.SuperpositionHandler;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import java.util.List;

public class EndSlayer extends NoLevelsModifier implements MeleeDamageModifierHook , ModifyDamageModifierHook {
    public EndSlayer(){
        MinecraftForge.EVENT_BUS.addListener(this::OnChangeTarget);
        MinecraftForge.EVENT_BUS.addListener(this::onTeleport);
    }

    private void onTeleport(EntityTeleportEvent.EnderEntity event) {
        if (event.getEntityLiving()!=null){
            boolean b =false;
            LivingEntity living =event.getEntityLiving().getLastHurtByMob();
            if (living instanceof Player player) {
                if (!SuperpositionHandler.isTheCursedOne(player)) {
                    return;
                }
                for (EquipmentSlot slot : List.of(EquipmentSlot.CHEST, EquipmentSlot.FEET, EquipmentSlot.HEAD, EquipmentSlot.LEGS)) {
                    ItemStack stack = living.getItemBySlot(slot);
                    if (stack.getItem() instanceof IModifiable) {
                        ToolStack tool = ToolStack.from(stack);
                        if (tool.getModifierLevel(this) > 0) {
                            b = true;
                            break;
                        }
                    }
                }
            }
            if (b){
                event.setCanceled(true);
            }
        }
    }

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(ModifierHooks.MELEE_DAMAGE);
    }

    private void OnChangeTarget(LivingChangeTargetEvent event) {
        boolean b =false;
        LivingEntity living =event.getNewTarget();
        if (living instanceof Player player) {
            if (!SuperpositionHandler.isTheCursedOne(player)) {
                return;
            }
            for (EquipmentSlot slot : List.of(EquipmentSlot.CHEST, EquipmentSlot.FEET, EquipmentSlot.HEAD, EquipmentSlot.LEGS)) {
                ItemStack stack = living.getItemBySlot(slot);
                if (stack.getItem() instanceof IModifiable) {
                    ToolStack tool = ToolStack.from(stack);
                    if (tool.getModifierLevel(this) > 0) {
                        b = true;
                        break;
                    }
                }
            }
        }
        if (b&&event.getEntity() instanceof EnderMan enderMan &&enderMan.getLastHurtByMob()!=event.getNewTarget()){
            event.setCanceled(true);
        }
        if (b&&event.getEntity() instanceof Endermite enderMan &&enderMan.getLastHurtByMob()!=event.getNewTarget()){
            event.setCanceled(true);
        }
    }

    @Override
    public float getMeleeDamage(IToolStackView iToolStackView, ModifierEntry modifierEntry, ToolAttackContext context, float bdmg, float dmg) {
        Player player = context.getPlayerAttacker();
        Entity entity =context.getTarget();
        if (player!=null&&SuperpositionHandler.isTheCursedOne(player)&&(entity instanceof Endermite||entity instanceof EnderMan||entity instanceof Shulker)){
            return dmg*2;
        }
        return dmg;
    }


    @Override
    public float modifyDamageTaken(IToolStackView iToolStackView, ModifierEntry modifierEntry, EquipmentContext context, EquipmentSlot equipmentSlot, DamageSource damageSource, float v, boolean b) {
        LivingEntity living = context.getEntity();
        Entity entity =damageSource.getEntity();
        if (living instanceof Player player&&SuperpositionHandler.isTheCursedOne(player)&&(entity instanceof Endermite||entity instanceof EnderMan||entity instanceof Shulker)){
            return 0.5f*v;
        }
        return v;
    }
}