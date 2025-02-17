package org.vanharneveldt.tinted_glass.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.vanharneveldt.tinted_glass.TintedGlass;

public class DimensionRegistry {
    public static final DeferredRegister<LevelStem> LEVEL_STEMS = DeferredRegister.create(Registries.LEVEL_STEM, TintedGlass.MOD_ID);
    
    public static final ResourceKey<Level> CUSTOM_DIMENSION = ResourceKey.create(Registries.DIMENSION, 
            ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, "custom_dimension"));
    
    public static final ResourceKey<DimensionType> CUSTOM_DIMENSION_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, "custom_dimension_type"));

    public static void register(IEventBus eventBus) {
        LEVEL_STEMS.register(eventBus);
    }
}
