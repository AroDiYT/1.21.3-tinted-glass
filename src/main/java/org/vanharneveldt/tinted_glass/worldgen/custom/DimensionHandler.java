package org.vanharneveldt.tinted_glass.worldgen.custom;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.phys.Vec3;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class DimensionHandler {
    private static final Map<String, ResourceKey<Level>> DIMENSIONS = new HashMap<>();
    
    public static void registerDimension(String modId, String dimensionId) {
        ResourceLocation dimensionLocation = ResourceLocation.fromNamespaceAndPath(modId, dimensionId);
        ResourceKey<Level> dimensionKey = ResourceKey.create(Registries.DIMENSION, dimensionLocation);
        DIMENSIONS.put(dimensionId, dimensionKey);
    }
    
    public static ResourceKey<Level> getDimensionKey(String dimensionId) {
        return DIMENSIONS.get(dimensionId);
    }
    
    public static ResourceKey<DimensionType> getDimensionTypeKey(String modId, String dimensionId) {
        return ResourceKey.create(Registries.DIMENSION_TYPE, ResourceLocation.fromNamespaceAndPath(modId, dimensionId));
    }

    public static Collection<ResourceKey<Level>> getAllDimensionKeys() {
        return DIMENSIONS.values();
    }

    public static void teleportToDimension(ServerPlayer player, ResourceKey<Level> dimension) {
        ServerLevel targetWorld = player.serverLevel().getServer().getLevel(dimension);
        if (targetWorld != null && targetWorld != player.level()) {
            double x = player.getX();
            double y = 100.0D;
            double z = player.getZ();
            float yaw = player.getYRot();
            float pitch = player.getXRot();
            
            // Teleport with absolute coordinates
            player.teleportTo(targetWorld, x, y, z, Set.of(), yaw, pitch, true);
        }
    }

    public static boolean isInDimension(Entity entity, ResourceKey<Level> dimension) {
        return entity.level().dimension().equals(dimension);
    }
}
