package org.vanharneveldt.tinted_glass.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.vanharneveldt.tinted_glass.TintedGlass;
import org.vanharneveldt.tinted_glass.enchantment.ModEnchantments;
import org.vanharneveldt.tinted_glass.worldgen.ModBiomeModifiers;
import org.vanharneveldt.tinted_glass.worldgen.ModConfiguredFeatures;
import org.vanharneveldt.tinted_glass.worldgen.ModPlacedFeatures;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDatapackProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.ENCHANTMENT, ModEnchantments::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap);
//            .add(Registries.TRIM_MATERIAL, ModTrimMaterials::bootstrap)
//            .add(Registries.TRIM_PATTERN, ModTrimPatterns::bootstrap)
            ;

    public ModDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(TintedGlass.MOD_ID));
    }
}

