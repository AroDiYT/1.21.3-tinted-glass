package org.vanharneveldt.tinted_glass.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.vanharneveldt.tinted_glass.TintedGlass;
import org.vanharneveldt.tinted_glass.block.ModBlocks;
import org.vanharneveldt.tinted_glass.block.custom.LampBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TintedGlass.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.ALUMINIUMBLOCK);
        blockWithItem(ModBlocks.SYLVANNETHERRACKORE);
        blockWithItem(ModBlocks.ALUMINIUMCOBBLESTONE);
        blockWithItem(ModBlocks.ALUMINIUMORE);
        blockWithItem(ModBlocks.DEEPSLATEALUMINIUMORE);
        blockWithItemCustom(ModBlocks.SMILEPUMPKIN, "pumpk");
        blockWithItemCustom(ModBlocks.CUSTOM, "custom");


        stairsBlock(ModBlocks.ALUMINIUMCOBBLESTONESTAIRS.get(), blockTexture(ModBlocks.ALUMINIUMCOBBLESTONE.get()));
        blockItem(ModBlocks.ALUMINIUMCOBBLESTONESTAIRS);

//        blockWithItem(ModBlocks.BISMUTH_BLOCK);
//
//        blockWithItem(ModBlocks.BISMUTH_ORE);
//        blockWithItem(ModBlocks.BISMUTH_DEEPSLATE_ORE);
//
//        blockWithItem(ModBlocks.MAGIC_BLOCK);
//
//        stairsBlock(ModBlocks.BISMUTH_STAIRS.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));
//        slabBlock(ModBlocks.BISMUTH_SLAB.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));
//
//        buttonBlock(ModBlocks.BISMUTH_BUTTON.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));
//        pressurePlateBlock(ModBlocks.BISMUTH_PRESSURE_PLATE.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));
//
//        fenceBlock(ModBlocks.BISMUTH_FENCE.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));
//        fenceGateBlock(ModBlocks.BISMUTH_FENCE_GATE.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));
//        wallBlock(ModBlocks.BISMUTH_WALL.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));
//
//        doorBlockWithRenderType(ModBlocks.BISMUTH_DOOR.get(), modLoc("block/bismuth_door_bottom"), modLoc("block/bismuth_door_top"), "cutout");
//        trapdoorBlockWithRenderType(ModBlocks.BISMUTH_TRAPDOOR.get(), modLoc("block/bismuth_trapdoor"), true, "cutout");
//
//        blockItem(ModBlocks.BISMUTH_STAIRS);
//        blockItem(ModBlocks.BISMUTH_SLAB);
//        blockItem(ModBlocks.BISMUTH_PRESSURE_PLATE);
//        blockItem(ModBlocks.BISMUTH_FENCE_GATE);
//        blockItem(ModBlocks.BISMUTH_TRAPDOOR, "_bottom");
//
        customLamp();
    }

    private void customLamp() {
        getVariantBuilder(ModBlocks.ALUMINIUMLAMP.get()).forAllStates(state -> {
            if(state.getValue(LampBlock.CLICKED)) {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("aluminium_lamp_on",
                        ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, "block/" + "aluminium_lamp_on")))};
            } else {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("aluminium_lamp_off",
                        ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, "block/" + "aluminium_lamp_off")))};
            }
        });

        simpleBlockItem(ModBlocks.ALUMINIUMLAMP.get(), models().cubeAll("aluminium_lamp_on",
                ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, "block/" + "aluminium_lamp_on")));
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockWithItemCustom(DeferredBlock<?> deferredBlock, String model) {
        simpleBlockWithItem(
                deferredBlock.get(),
                new ModelFile.ExistingModelFile(
                        ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, model),
                        this.models().existingFileHelper
                )
        );
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("revampedglass:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("revampedglass:block/" + deferredBlock.getId().getPath() + appendix));
    }
}

