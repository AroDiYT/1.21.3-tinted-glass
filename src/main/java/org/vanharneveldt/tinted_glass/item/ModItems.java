package org.vanharneveldt.tinted_glass.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ToolMaterial;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.vanharneveldt.tinted_glass.TintedGlass;



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

    public static final DeferredItem<Item> ALUMINIUMSWORD = ITEMS.register("aluminium_sword",
            () -> new SwordItem(
                    ToolMaterial.IRON,
                    2.3f,
                    -1.2f,
                    new Item.Properties().durability(220).setId(
                    ResourceKey.create(
                            Registries.ITEM,
                            ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, "aluminium_sword")
                    )
            )
            ));


    public static final DeferredItem<Item> ALUMINIUMPICKAXE = ITEMS.register("aluminium_pickaxe",
            () -> new PickaxeItem(
                    ToolMaterial.IRON,
                    1.2f,
                    -1.2f,
                    new Item.Properties().durability(300).setId(
                            ResourceKey.create(
                                    Registries.ITEM,
                                    ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, "aluminium_pickaxe")
                            )
                    )
            ));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
