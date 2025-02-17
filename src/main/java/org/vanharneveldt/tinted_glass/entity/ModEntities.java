package org.vanharneveldt.tinted_glass.entity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import org.vanharneveldt.tinted_glass.TintedGlass;
import org.vanharneveldt.tinted_glass.entity.custom.FacelessEntity;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, TintedGlass.MOD_ID);

    public static final Supplier<EntityType<FacelessEntity>> FACELESS = ENTITY_TYPES.register("faceless",
            () -> EntityType.Builder.of(FacelessEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.95f)
                    .build(ResourceKey.create(BuiltInRegistries.ENTITY_TYPE.key(), ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, "faceless"))));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
