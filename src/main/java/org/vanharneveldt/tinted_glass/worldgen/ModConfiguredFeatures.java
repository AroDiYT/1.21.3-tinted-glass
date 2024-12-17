package org.vanharneveldt.tinted_glass.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import org.vanharneveldt.tinted_glass.TintedGlass;
import org.vanharneveldt.tinted_glass.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?,?>> OVERWORLD_ALUMINIUM_ORE_KEY = registerKey("aluminium_ore");
    public static final ResourceKey<ConfiguredFeature<?,?>> NETHER_SYLVAN_NETHERACK_ORE_KEY = registerKey("nether_sylvan_ore");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK);

        List<OreConfiguration.TargetBlockState> overworldAluminiumOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.ALUMINIUMORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATEALUMINIUMORE.get().defaultBlockState())
        );

        register(context,
                OVERWORLD_ALUMINIUM_ORE_KEY,
                Feature.ORE,
                new OreConfiguration(overworldAluminiumOres, 5));
        register(context,
                NETHER_SYLVAN_NETHERACK_ORE_KEY,
                Feature.ORE,
                new OreConfiguration(netherrackReplaceables, ModBlocks.SYLVANNETHERRACKORE.get().defaultBlockState(), 4));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}