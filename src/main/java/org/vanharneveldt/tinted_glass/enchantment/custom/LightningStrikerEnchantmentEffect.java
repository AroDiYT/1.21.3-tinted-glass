package org.vanharneveldt.tinted_glass.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;

public record LightningStrikerEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<LightningStrikerEnchantmentEffect> CODEC = MapCodec.unit(LightningStrikerEnchantmentEffect::new);
    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 origin) {
        if(enchantmentLevel == 1) {
            EntityType.LIGHTNING_BOLT.spawn(level, entity.getOnPos(), EntitySpawnReason.TRIGGERED);
        } else if(enchantmentLevel == 2) {
            EntityType.LIGHTNING_BOLT.spawn(level, entity.getOnPos(), EntitySpawnReason.TRIGGERED);
            EntityType.LIGHTNING_BOLT.spawn(level, entity.getOnPos(), EntitySpawnReason.TRIGGERED);
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
