package org.vanharneveldt.tinted_glass.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.vanharneveldt.tinted_glass.block.ModBlocks;
import org.vanharneveldt.tinted_glass.item.ModItems;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.ALUMINIUMCOBBLESTONE.get());
        dropSelf(ModBlocks.SMILEPUMPKIN.get());
        dropSelf(ModBlocks.ALUMINIUMCOBBLESTONESTAIRS.get());
        dropSelf(ModBlocks.ALUMINIUMBLOCK.get());
        dropSelf(ModBlocks.ALUMINIUMLAMP.get());
        dropSelf(ModBlocks.CUSTOM.get());

        add(ModBlocks.ALUMINIUMORE.get(), block -> createMultipleOreDrops(ModBlocks.ALUMINIUMORE.get(), ModItems.RAWALUMINIUM.get(), 1, 3));
//        add(Blocks.GLASS,block -> createMultipleOreDrops(Blocks.GLASS, ModItems.GLASSSHARDS.get(), 1, 3));
        add(ModBlocks.DEEPSLATEALUMINIUMORE.get(),block -> createMultipleOreDrops(ModBlocks.DEEPSLATEALUMINIUMORE.get(), ModItems.RAWALUMINIUM.get(), 1, 3));
        add(ModBlocks.SYLVANNETHERRACKORE.get(),block -> createMultipleOreDrops(ModBlocks.SYLVANNETHERRACKORE.get(), ModItems.SYLVANFUEL.get(), 1, 3));
//        dropSelf(ModBlocks.BISMUTH_BLOCK.get());
//        // dropSelf(ModBlocks.MAGIC_BLOCK.get());
//
//        add(ModBlocks.BISMUTH_ORE.get(),
//                block -> createOreDrop(ModBlocks.BISMUTH_ORE.get(), ModItems.RAW_BISMUTH.get()));
//        add(ModBlocks.BISMUTH_DEEPSLATE_ORE.get(),
//                block -> createMultipleOreDrops(ModBlocks.BISMUTH_DEEPSLATE_ORE.get(), ModItems.RAW_BISMUTH.get(), 2, 5));
//
//        dropSelf(ModBlocks.BISMUTH_STAIRS.get());
//        add(ModBlocks.BISMUTH_SLAB.get(),
//                block -> createSlabItemTable(ModBlocks.BISMUTH_SLAB.get()));
//
//        dropSelf(ModBlocks.BISMUTH_PRESSURE_PLATE.get());
//        dropSelf(ModBlocks.BISMUTH_BUTTON.get());
//
//        dropSelf(ModBlocks.BISMUTH_FENCE.get());
//        dropSelf(ModBlocks.BISMUTH_FENCE_GATE.get());
//        dropSelf(ModBlocks.BISMUTH_WALL.get());
//        dropSelf(ModBlocks.BISMUTH_TRAPDOOR.get());
//
//        add(ModBlocks.BISMUTH_DOOR.get(),
//                block -> createDoorTable(ModBlocks.BISMUTH_DOOR.get()));
//
//        dropSelf(ModBlocks.BISMUTH_LAMP.get());

    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
