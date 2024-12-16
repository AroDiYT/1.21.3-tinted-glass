package org.vanharneveldt.tinted_glass.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.vanharneveldt.tinted_glass.TintedGlass;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TintedGlass.MOD_ID);

    public static final DeferredItem<Item> GLASSSHARDS = ITEMS.registerSimpleItem("glassshards");
    public static final DeferredItem<Item> MOLTENGLASS = ITEMS.registerSimpleItem("moltenglass");
    public static final DeferredItem<Item> GLASSPIECE = ITEMS.registerSimpleItem("glasspiece");
    public static final DeferredItem<Item> RAWALUMINIUM = ITEMS.registerSimpleItem("raw_aluminium");
    public static final DeferredItem<Item> ALUMINIUMINGOT = ITEMS.registerSimpleItem("aluminium_ingot");
    public static final DeferredItem<Item> ALUMINIUMSWORD = ITEMS.registerSimpleItem("aluminium_sword");
    public static final DeferredItem<Item> ALUMINIUMPICKAXE = ITEMS.registerSimpleItem("aluminium_pickaxe");



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
