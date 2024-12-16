package org.vanharneveldt.tinted_glass.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.vanharneveldt.tinted_glass.TintedGlass;
import org.vanharneveldt.tinted_glass.item.custom.FuelItem;
import org.vanharneveldt.tinted_glass.item.custom.HammerItem;
import org.vanharneveldt.tinted_glass.item.custom.ModArmorItem;


public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TintedGlass.MOD_ID);

    public static final DeferredItem<Item> GLASSSHARDS = ITEMS.register("glassshards",
            () -> new Item(
                    new Item.Properties()
                            .setId(
                                    ResourceKey.create(
                                            Registries.ITEM,
                                            ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, "glassshards")
                                    )
                            )
            )
    );
    public static final DeferredItem<Item> SYLVANFUEL = ITEMS.register("sylvan_fuel",
            () -> new FuelItem(new Item.Properties().setId(
                    ResourceKey.create(
                            Registries.ITEM,
                            ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, "sylvan_fuel")
                    )
            ), 1450)
    );
    public static final DeferredItem<Item> MOLTENGLASS = ITEMS.register("moltenglass", () -> new Item(
            new Item.Properties()
                    .setId(
                            ResourceKey.create(
                                    Registries.ITEM,
                                    ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, "moltenglass")
                            )
                    )
    ));
    public static final DeferredItem<Item> GLASSPIECE = ITEMS.register("glasspiece", () -> new Item(
            new Item.Properties()
                    .setId(
                            ResourceKey.create(
                                    Registries.ITEM,
                                    ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, "glasspiece")
                            )
                    )
    ));
    public static final DeferredItem<Item> RAWALUMINIUM = ITEMS.register("raw_aluminium", () -> new Item(
            new Item.Properties()
                    .setId(
                            ResourceKey.create(
                                    Registries.ITEM,
                                    ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, "raw_aluminium")
                            )
                    )
    ));
    public static final DeferredItem<Item> ALUMINIUMINGOT = ITEMS.register("aluminium_ingot", () -> new Item(
            new Item.Properties()
                    .setId(
                            ResourceKey.create(
                                    Registries.ITEM,
                                    ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, "aluminium_ingot")
                            )
                    )
    ));

    public static final DeferredItem<SwordItem> ALUMINIUMSWORD = ITEMS.register("aluminium_sword",
            () -> new SwordItem(
                    ModToolTiers.ALUMINIUM,
                    4.5f,
                    -2.4f,
                    new SwordItem.Properties().durability(1340).setId(
                    ResourceKey.create(
                            Registries.ITEM,
                            ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, "aluminium_sword")
                    )
            )
            ));


    public static final DeferredItem<PickaxeItem> ALUMINIUMPICKAXE = ITEMS.register("aluminium_pickaxe",
            () -> new PickaxeItem(
                    ModToolTiers.ALUMINIUM,
                    2.5f,
                    -2.6f,
                    new PickaxeItem.Properties().durability(1342).setId(
                            ResourceKey.create(
                                    Registries.ITEM,
                                    ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, "aluminium_pickaxe")
                            )
                    )
            ));

    public static final DeferredItem<HammerItem> ALUMINIUMHAMMER = ITEMS.register("aluminium_hammer",
            () -> new HammerItem(
                    ModToolTiers.ALUMINIUM,
                    7.5f,
                    -3.2f,
                    new PickaxeItem.Properties().durability(1342).setId(
                            ResourceKey.create(
                                    Registries.ITEM,
                                    ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, "aluminium_hammer")
                            )
                    )
            ));
    public static final DeferredItem<ArmorItem> ALUMINIUM_HELMET = ITEMS.register("aluminium_helmet",
            () -> new ModArmorItem(ModArmorMaterials.ALUMINIUM_ARMOR_MATERIAL, ArmorType.HELMET,
                    new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, "aluminium_helmet")))));
    public static final DeferredItem<ArmorItem> ALUMINIUM_CHESTPLATE = ITEMS.register("aluminium_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.ALUMINIUM_ARMOR_MATERIAL, ArmorType.CHESTPLATE,
                    new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, "aluminium_chestplate")))));
    public static final DeferredItem<ArmorItem> ALUMINIUM_LEGGINGS = ITEMS.register("aluminium_leggings",
            () -> new ModArmorItem(ModArmorMaterials.ALUMINIUM_ARMOR_MATERIAL, ArmorType.LEGGINGS,
                    new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, "aluminium_leggings")))));
    public static final DeferredItem<ArmorItem> ALUMINIUM_BOOTS = ITEMS.register("aluminium_boots",
            () -> new ModArmorItem(ModArmorMaterials.ALUMINIUM_ARMOR_MATERIAL, ArmorType.BOOTS,
                    new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, "aluminium_boots")))));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
