package org.vanharneveldt.tinted_glass.block;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.vanharneveldt.tinted_glass.TintedGlass;
import org.vanharneveldt.tinted_glass.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TintedGlass.MOD_ID);

    public static final DeferredBlock<Block> SYLVANNETHERRACKORE = registerBlock("sylvan_netherrack_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.NETHERRACK)
                    .setId(
                            ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, "sylvan_netherrack_ore"))
                    )));


    public static final DeferredBlock<Block> ALUMINIUMBLOCK = registerBlock("aluminium_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.HEAVY_CORE)
                    .setId(
                            ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, "aluminium_block"))
                    )));


    public static final DeferredBlock<Block> ALUMINIUMCOBBLESTONE = registerBlock("aluminium_cobblestone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
                    .setId(
                            ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, "aluminium_cobblestone"))
                    )));

    public static final DeferredBlock<StairBlock> ALUMINIUMCOBBLESTONESTAIRS = registerBlock("aluminium_cobblestone_stairs",
            () -> new StairBlock(ModBlocks.ALUMINIUMCOBBLESTONE.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, "aluminium_cobblestone_stairs")))
                            .strength(2f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> SMILEPUMPKIN = registerBlock("smilepumpkin",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.GILDED_BLACKSTONE)
                    .setId(
                        ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, "smilepumpkin"))
                    )));

    public static final DeferredBlock<Block> ALUMINIUMORE = registerBlock("aluminium_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
                    .setId(
                        ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, "aluminium_ore"))
                    )));

    public static final DeferredBlock<Block> DEEPSLATEALUMINIUMORE = registerBlock("deepslate_aluminium_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)
                    .setId(
                            ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, "deepslate_aluminium_ore"))
                    )));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.registerSimpleBlockItem(name, block);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
