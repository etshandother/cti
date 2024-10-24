package com.hoshino.cti.register;

import com.hoshino.cti.Blocks.Machine.*;
import com.hoshino.cti.Blocks.OverdenseGlacioStone;
import com.hoshino.cti.Blocks.unipolarBudding;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.tconstruct.world.block.CrystalClusterBlock;


public class ctiBlock {
    public static final DeferredRegister<Block> BLOCK = DeferredRegister.create(ForgeRegistries.BLOCKS, "cti");
    public static final RegistryObject<Block> unipolar_magnet_budding = BLOCK.register("unipolar_magnet_budding", () -> new unipolarBudding(BlockBehaviour.Properties.of(Material.AMETHYST).lightLevel((BlockStateBase)-> 15).sound(SoundType.AMETHYST).randomTicks()));
    public static final RegistryObject<Block> unipolar_magnet = BLOCK.register("unipolar_magnet", () -> new CrystalClusterBlock(SoundEvents.AMETHYST_BLOCK_CHIME,7,3, BlockBehaviour.Properties.of(Material.AMETHYST).noOcclusion().randomTicks().sound(SoundType.AMETHYST_CLUSTER).strength(0.5F).lightLevel((BlockStateBase)-> 5)));
    public static final RegistryObject<Block> overdense_glacio_stone = BLOCK.register("overdense_glacio_stone", () -> new OverdenseGlacioStone(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.ANCIENT_DEBRIS).randomTicks()));
    public static final RegistryObject<Block> ultra_dense_hydride_ore = BLOCK.register("ultra_dense_hydride_ore", () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST).sound(SoundType.AMETHYST).lightLevel((BlockStateBase)-> 15)));


    public static final RegistryObject<BaseEntityBlock> atmosphere_extractor =BLOCK.register("atmosphere_extractor",()->new AtmosphereExtractorBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).destroyTime(2).requiresCorrectToolForDrops()));
    public static final RegistryObject<BaseEntityBlock> atmosphere_condensator =BLOCK.register("atmosphere_condensator",()->new AtmosphereCondensatorBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).destroyTime(2).requiresCorrectToolForDrops()));
    public static final RegistryObject<BaseEntityBlock> quantum_miner =BLOCK.register("quantum_miner",()->new QuantumMinerBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).destroyTime(2).requiresCorrectToolForDrops()));
    public static final RegistryObject<BaseEntityBlock> quantum_miner_advanced =BLOCK.register("quantum_miner_advanced",()->new QuantumMinerAdvancdBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).destroyTime(2).requiresCorrectToolForDrops()));
    public static final RegistryObject<BaseEntityBlock> reactor_neutron_collector =BLOCK.register("reactor_neutron_collector",()->new ReactorNeutronCollectorBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).destroyTime(2).requiresCorrectToolForDrops()));
    public static final RegistryObject<BaseEntityBlock> alloy_centrifuge_block =BLOCK.register("alloy_centrifuge",()->new AlloyCentrifugeBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).destroyTime(2).requiresCorrectToolForDrops()));
    public static final RegistryObject<BaseEntityBlock> sodium_cooler_block =BLOCK.register("sodium_cooler",()->new SodiumCooler(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).destroyTime(2).requiresCorrectToolForDrops()));
}
