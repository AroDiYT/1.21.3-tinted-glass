package org.vanharneveldt.tinted_glass.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.minecraft.world.item.equipment.trim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import org.vanharneveldt.tinted_glass.TintedGlass;
import org.vanharneveldt.tinted_glass.item.ModItems;
import org.vanharneveldt.tinted_glass.item.custom.ModArmorItem;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TintedGlass.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.ALUMINIUMINGOT.get());
        basicItem(ModItems.RAWALUMINIUM.get());

        basicItem(ModItems.GLASSSHARDS.get());
        basicItem(ModItems.MOLTENGLASS.get());
        basicItem(ModItems.GLASSPIECE.get());

        basicItem(ModItems.SYLVANFUEL.get());

        handheldItem(ModItems.ALUMINIUMSWORD.get());
        handheldItem(ModItems.ALUMINIUMPICKAXE.get());
        handheldItem(ModItems.ALUMINIUMHAMMER.get());
//        basicItem(ModItems.BISMUTH.get());
//        basicItem(ModItems.RAW_BISMUTH.get());
//
//        basicItem(ModItems.RADISH.get());
//        basicItem(ModItems.STARLIGHT_ASHES.get());
//        basicItem(ModItems.FROSTFIRE_ICE.get());
//        // basicItem(ModItems.CHISEL.get());
//
//        buttonItem(ModBlocks.BISMUTH_BUTTON, ModBlocks.BISMUTH_BLOCK);
//        fenceItem(ModBlocks.BISMUTH_FENCE, ModBlocks.BISMUTH_BLOCK);
//        wallItem(ModBlocks.BISMUTH_WALL, ModBlocks.BISMUTH_BLOCK);
//
//        basicItem(ModBlocks.BISMUTH_DOOR.asItem());
//
//        handheldItem(ModItems.BISMUTH_SWORD);
//        handheldItem(ModItems.BISMUTH_PICKAXE);
//        handheldItem(ModItems.BISMUTH_SHOVEL);
//        handheldItem(ModItems.BISMUTH_AXE);
//        handheldItem(ModItems.BISMUTH_HOE);
//        handheldItem(ModItems.BISMUTH_HAMMER);
//
        trimmedArmorItem(ModItems.ALUMINIUM_HELMET);
        trimmedArmorItem(ModItems.ALUMINIUM_CHESTPLATE);
        trimmedArmorItem(ModItems.ALUMINIUM_LEGGINGS);
        trimmedArmorItem(ModItems.ALUMINIUM_BOOTS);
//
//        basicItem(ModItems.BISMUTH_HORSE_ARMOR.get());
//        basicItem(ModItems.KAUPEN_SMITHING_TEMPLATE.get());
//        basicItem(ModItems.BAR_BRAWL_MUSIC_DISC.get());
    }

    // Shoutout to El_Redstoniano for making this
    private void trimmedArmorItem(DeferredItem<ArmorItem> itemDeferredItem) {
        final String MOD_ID = TintedGlass.MOD_ID; // Change this to your mod id

        ArmorItem armorItem = itemDeferredItem.get();
        trimMaterials.forEach((trimMaterial, value) -> {
            float trimValue = value;

            String armorType = "";
            if (armorItem.toString().contains("helmet")) {
                armorType = "helmet";
            } else if (armorItem.toString().contains("chestplate")) {
                armorType = "chestplate";
            } else if (armorItem.toString().contains("leggings")) {
                armorType = "leggings";
            } else if (armorItem.toString().contains("boots")) {
                armorType = "boots";
            }

            String armorItemPath = armorItem.toString();
            String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
            String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
            ResourceLocation armorItemResLoc = ResourceLocation.parse(armorItemPath);
            ResourceLocation trimResLoc = ResourceLocation.parse(trimPath); // minecraft namespace
            ResourceLocation trimNameResLoc = ResourceLocation.parse(currentTrimName);

            // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
            // avoid an IllegalArgumentException
            existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

            // Trimmed armorItem files
            getBuilder(currentTrimName)
                    .parent(new ModelFile.UncheckedModelFile("item/generated"))
                    .texture("layer0", armorItemResLoc.getNamespace() + ":item/" + armorItemResLoc.getPath())
                    .texture("layer1", trimResLoc);

            // Non-trimmed armorItem file (normal variant)
            this.withExistingParent(itemDeferredItem.getId().getPath(),
                            mcLoc("item/generated"))
                    .override()
                    .model(new ModelFile.UncheckedModelFile(trimNameResLoc.getNamespace() + ":item/" + trimNameResLoc.getPath()))
                    .predicate(mcLoc("trim_type"), trimValue).end()
                    .texture("layer0",
                            ResourceLocation.fromNamespaceAndPath(MOD_ID,
                                    "item/" + itemDeferredItem.getId().getPath()));
        });
    }

    public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID,"item/" + item.getId().getPath()));
    }
}

