package org.vanharneveldt.tinted_glass.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import org.vanharneveldt.tinted_glass.TintedGlass;
import org.vanharneveldt.tinted_glass.block.ModBlocks;
import org.vanharneveldt.tinted_glass.util.ModTags;

import java.util.concurrent.CompletableFuture;


public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TintedGlass.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.ALUMINIUMBLOCK.get())
                .add(ModBlocks.SYLVANNETHERRACKORE.get())
                .add(ModBlocks.DEEPSLATEALUMINIUMORE.get())
                .add(ModBlocks.ALUMINIUMORE.get())
                .add(ModBlocks.ALUMINIUMCOBBLESTONE.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.ALUMINIUMORE.get())
                .add(ModBlocks.ALUMINIUMBLOCK.get())
                .add(ModBlocks.ALUMINIUMCOBBLESTONESTAIRS.get())
                .add(ModBlocks.DEEPSLATEALUMINIUMORE.get())
                .add(ModBlocks.ALUMINIUMCOBBLESTONE.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(ModBlocks.SYLVANNETHERRACKORE.get());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.SMILEPUMPKIN.get())
                .add(ModBlocks.CUSTOM.get());

        tag(ModTags.Blocks.NEEDS_ALUMINIUM_TOOL)
                .add(ModBlocks.SYLVANNETHERRACKORE.get())
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_ALUMINIUM_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .remove(ModTags.Blocks.NEEDS_ALUMINIUM_TOOL);

//        tag(BlockTags.FENCES).add(ModBlocks.BISMUTH_FENCE.get());
//        tag(BlockTags.FENCE_GATES).add(ModBlocks.BISMUTH_FENCE_GATE.get());
//        tag(BlockTags.WALLS).add(ModBlocks.BISMUTH_WALL.get());

    }
}
