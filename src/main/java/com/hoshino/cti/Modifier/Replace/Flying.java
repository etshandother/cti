package com.hoshino.cti.Modifier.Replace;

import com.marth7th.solidarytinker.extend.superclass.ArmorModifier;
import net.minecraft.world.entity.player.Player;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.EquipmentChangeContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class Flying extends ArmorModifier {
    @Override
    public void onEquip(IToolStackView tool, ModifierEntry modifier, EquipmentChangeContext context) {
        if((context.getEntity() instanceof Player player && !player.getAbilities().mayfly&&!player.getAbilities().flying)) {
            player.getAbilities().flying=true;
            player.getAbilities().mayfly=true;
        }
    }

    @Override
    public void onUnequip(IToolStackView tool, ModifierEntry modifier, EquipmentChangeContext context) {
        if((context.getEntity() instanceof Player player && player.getAbilities().mayfly&&player.getAbilities().flying)) {
            player.getAbilities().flying = false;
            player.getAbilities().mayfly = false;
        }
    }
}