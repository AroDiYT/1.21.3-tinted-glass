package org.vanharneveldt.tinted_glass.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import org.vanharneveldt.tinted_glass.TintedGlass;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> ALUMINIUM_ORE_PLACED_KEY = registerKey("aluminium_ore_placed");
    public static final ResourceKey<PlacedFeature> NETHER_SYLVAN_ORE_PLACED_KEY = registerKey("nether_sylvan_ore_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, ALUMINIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_ALUMINIUM_ORE_KEY),
                ModOrePlacement.commonOrePlacement(3, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(8))));
        register(context, NETHER_SYLVAN_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_SYLVAN_NETHERACK_ORE_KEY),
                ModOrePlacement.commonOrePlacement(4, HeightRangePlacement.uniform(VerticalAnchor.absolute(-3), VerticalAnchor.absolute(15))));

    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}