package org.vanharneveldt.tinted_glass.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.vanharneveldt.tinted_glass.TintedGlass;
import org.vanharneveldt.tinted_glass.block.ModBlocks;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TintedGlass.MOD_ID);

    public static final Supplier<CreativeModeTab> TINTED_ITEMS = CREATIVE_MODE_TAB.register("tinted_glass_items_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.RAWALUMINIUM.get()))
                    .title(Component.translatable("creativetab.revampedglass.tinted_items"))
                    .displayItems(
                            (itemDisplayParameters, output) -> {
                                output.accept(ModItems.GLASSSHARDS);
                                output.accept(ModItems.MOLTENGLASS);
                                output.accept(ModItems.GLASSPIECE);
                                output.accept(ModItems.RAWALUMINIUM);
                                output.accept(ModItems.ALUMINIUMINGOT);
                                output.accept(ModItems.SYLVANFUEL);
                            }
                    ).build());

    public static final Supplier<CreativeModeTab> TINTED_TOOLS = CREATIVE_MODE_TAB.register("tinted_glass_tools_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.ALUMINIUMSWORD.get()))
                    .title(Component.translatable("creativetab.revampedglass.tinted_tools"))
                    .displayItems(
                            (itemDisplayParameters, output) -> {
                                output.accept(ModItems.ALUMINIUMSWORD);
                                output.accept(ModItems.ALUMINIUMPICKAXE);
                            }
                    ).build());

    public static final Supplier<CreativeModeTab> TINTED_BLOCK = CREATIVE_MODE_TAB.register("tinted_glass_blocks_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModBlocks.ALUMINIUMORE.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, "tinted_glass_items_tab"))
                    .title(Component.translatable("creativetab.revampedglass.tinted_blocks"))
                    .displayItems(
                            (itemDisplayParameters, output) -> {
                                output.accept(ModBlocks.SMILEPUMPKIN);
                                output.accept(ModBlocks.ALUMINIUMORE);
                                output.accept(ModBlocks.DEEPSLATEALUMINIUMORE);
                                output.accept(ModBlocks.ALUMINIUMCOBBLESTONE);
                                output.accept(ModBlocks.ALUMINIUMBLOCK);
                            }
                    ).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
