package com.hoshino.cti.Screen.menu;

import com.hoshino.cti.cti;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ctiMenu {
    public static final DeferredRegister<MenuType<?>> MENU_TYPE =DeferredRegister.create(ForgeRegistries.MENU_TYPES, cti.MOD_ID);
    public static final RegistryObject<MenuType<AtmosphereExtractorMenu>> ATMOSPHERE_EXT_MENU = MENU_TYPE.register("atmosphere_extractor_menu",()-> IForgeMenuType.create(AtmosphereExtractorMenu::new));

    public static void register(IEventBus eventBus){
        MENU_TYPE.register(eventBus);
    }
}