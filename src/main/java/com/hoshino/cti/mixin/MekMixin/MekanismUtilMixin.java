package com.hoshino.cti.mixin.MekMixin;

import mekanism.api.Upgrade;
import mekanism.api.math.FloatingLong;
import mekanism.common.config.MekanismConfig;
import mekanism.common.tile.interfaces.IUpgradeTile;
import mekanism.common.util.MekanismUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = MekanismUtils.class,remap = false)
public class MekanismUtilMixin {
    /**
     * @author EtSH_C2H6S
     * @reason 让超出8个的插件能够生效
     */
    @Overwrite
    public static double fractionUpgrades(IUpgradeTile tile, Upgrade type) {
        if (tile.supportsUpgrade(type)) {
            return tile.getComponent().getUpgrades(type) / (double) 8;
        }
        return 0;
    }

    /**
     * @author EtSH_C2H6S
     * @reason 让超出8个的能量插件不减少能耗
     */
    @Overwrite
    public static FloatingLong getEnergyPerTick(IUpgradeTile tile, FloatingLong def) {
        return tile.supportsUpgrades() ? def.multiply(Math.pow((double) MekanismConfig.general.maxUpgradeMultiplier.get(), 2.0 * fractionUpgrades(tile, Upgrade.SPEED) -Math.min( fractionUpgrades(tile, Upgrade.ENERGY),1))) : def;
    }
}
