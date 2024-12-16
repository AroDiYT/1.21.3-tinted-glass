package org.vanharneveldt.tinted_glass.item;

import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import org.vanharneveldt.tinted_glass.TintedGlass;
import org.vanharneveldt.tinted_glass.util.ModTags;

import java.util.EnumMap;

public class ModArmorMaterials {
    public static final ArmorMaterial ALUMINIUM_ARMOR_MATERIAL = new ArmorMaterial(1200,
            Util.make(new EnumMap<>(ArmorType.class), attribute -> {
                attribute.put(ArmorType.BOOTS, 5);
                attribute.put(ArmorType.LEGGINGS, 7);
                attribute.put(ArmorType.CHESTPLATE, 9);
                attribute.put(ArmorType.HELMET, 5);
                attribute.put(ArmorType.BODY, 11);
            }), 16, SoundEvents.ARMOR_EQUIP_WOLF,
            2f, 0.5f, ModTags.Items.ALUMINIUM_REPAIR, ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, "aluminium"));

}