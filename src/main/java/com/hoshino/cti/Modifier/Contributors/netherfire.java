package com.hoshino.cti.Modifier.Contributors;

import com.marth7th.solidarytinker.extend.superclass.BattleModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;

public class netherfire extends BattleModifier {
    @Override
    public float staticdamage(IToolStackView tool, int level, ToolAttackContext context, LivingEntity attacker, LivingEntity livingTarget, float baseDamage, float damage) {
        if (livingTarget.getTags().contains("wick")) {
            if (livingTarget.getArmorValue() > attacker.getArmorValue()) {
                return damage + livingTarget.getArmorValue() * (float) livingTarget.getAttributeValue(Attributes.ARMOR_TOUGHNESS) * 2 * level;
            } else
                return damage + livingTarget.getArmorValue() * (float) livingTarget.getAttributeValue(Attributes.ARMOR_TOUGHNESS) * level;
        }
        return damage;
    }

    @Override
    public void arrowhurt(ModifierNBT modifiers, NamespacedNBT persistentData, int level, Projectile projectile, AbstractArrow arrow, EntityHitResult hit, LivingEntity attacker, LivingEntity target) {
        if (target != null && target.getTags().contains("wick")) {
            if (target.getArmorValue() > attacker.getArmorValue()) {
                arrow.setBaseDamage(arrow.getBaseDamage() + target.getArmorValue() * (float) target.getAttributeValue(Attributes.ARMOR_TOUGHNESS) * 2 * level);
            } else
                arrow.setBaseDamage(arrow.getBaseDamage() + target.getArmorValue() * (float) target.getAttributeValue(Attributes.ARMOR_TOUGHNESS) * level);
        }
    }
}