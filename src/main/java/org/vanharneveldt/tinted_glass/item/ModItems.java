package org.vanharneveldt.tinted_glass.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.vanharneveldt.tinted_glass.TintedGlass;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TintedGlass.MOD_ID);

    public static final DeferredItem<Item> GLASSSHARD = ITEMS.register("glassshard",
            () -> new Item(new Item.Properties()));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
