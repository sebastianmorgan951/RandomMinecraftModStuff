package com.seb.mymod.world.gen;

import com.google.common.collect.Lists;
import com.seb.mymod.MyMod;
import net.minecraft.entity.passive.PandaEntity;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpreadConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.placement.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = MyMod.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModOreGeneration {

    @SubscribeEvent
    public static void generateMounds(FMLLoadCompleteEvent event){
        for(NonOreRandomlyPlacedType block : NonOreRandomlyPlacedType.values()){
            OreFeatureConfig moundFeatureConfig = new OreFeatureConfig(OreFeatureConfig
                    .FillerBlockType.BASE_STONE_OVERWORLD, block.getBlock().getDefaultState(),
                    block.getMaxVeinSize());

            ConfiguredPlacement placementHeight = Placement.DEPTH_AVERAGE.configure(
                    new DepthAverageConfig(block.getMinHeight(),block.getMaxHeight()));

            Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                    block.getBlock().getRegistryName(),
                    Feature.ORE.withConfiguration(moundFeatureConfig)
                            .withPlacement(placementHeight).square()
                            .func_242731_b(block.getMaxVeinSize()));

            for(Biome biome : ForgeRegistries.BIOMES) {
                for (Structure structure : ForgeRegistries.STRUCTURE_FEATURES) {
                    if (structure.getStructure().equals(StructureFeatures.MINESHAFT)) {
                        addFeatureToBiome(biome, GenerationStage.Decoration.UNDERGROUND_STRUCTURES,
                                WorldGenRegistries.CONFIGURED_FEATURE.getOrDefault(
                                        block.getBlock().getRegistryName()));
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void generateOres(FMLLoadCompleteEvent event){
        for(OreType ore : OreType.values()){
            /* The filler block tells the game what to generate in the non-ore parts of
            * an ore vein. */
            OreFeatureConfig oreFeatureConfig = new OreFeatureConfig(OreFeatureConfig
                    .FillerBlockType.BASE_STONE_OVERWORLD, ore.getBlock().getDefaultState(),
                    ore.getMaxVeinSize());

            ConfiguredPlacement configuredPlacement = Placement.DEPTH_AVERAGE.configure(
                    new DepthAverageConfig(ore.getMinHeight(),ore.getMaxHeight()));

            Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                    ore.getBlock().getRegistryName(),
                    Feature.ORE.withConfiguration(oreFeatureConfig)
                            .withPlacement(configuredPlacement).square()
                            .func_242731_b(ore.getMaxVeinSize()));

            for(Biome biome: ForgeRegistries.BIOMES){
                if(!biome.getCategory().equals(Biome.Category.NETHER)
                && !biome.getCategory().equals(Biome.Category.THEEND)){
                    addFeatureToBiome(biome,GenerationStage.Decoration.UNDERGROUND_ORES,
                            WorldGenRegistries.CONFIGURED_FEATURE.getOrDefault(
                                    ore.getBlock().getRegistryName()));
                    System.out.println("Here from " + biome.toString());
                }
            }
        }
    }



    public static void addFeatureToBiome(Biome biome, GenerationStage.Decoration decoration,
                                         ConfiguredFeature<?,?> configuredFeature){
        List<List<Supplier<ConfiguredFeature<?,?>>>> biomeFeatures = new ArrayList<>(
                biome.getGenerationSettings().getFeatures()
        );

        while(biomeFeatures.size() <= decoration.ordinal()){
            biomeFeatures.add(Lists.newArrayList());
        }

        List<Supplier<ConfiguredFeature<?,?>>> features = new ArrayList<>(biomeFeatures
                .get(decoration.ordinal()));
        features.add(() -> configuredFeature);
        biomeFeatures.set(decoration.ordinal(), features);

        ObfuscationReflectionHelper.setPrivateValue(BiomeGenerationSettings.class,
                biome.getGenerationSettings(), biomeFeatures, "field_242484_f");
    }
}
