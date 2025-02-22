package org.vanharneveldt.tinted_glass.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.vanharneveldt.tinted_glass.TintedGlass;
import org.vanharneveldt.tinted_glass.block.ModBlocks;
import org.vanharneveldt.tinted_glass.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    protected ModRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        super(provider, recipeOutput);
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider) {
            super(packOutput, provider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            return new ModRecipeProvider(provider, recipeOutput);
        }

        @Override
        public String getName() {
            return "My Recipes";
        }
    }

    @Override
    protected void buildRecipes() {
        List<ItemLike> ALUMINIUM_SMELTABLES = List.of(ModItems.RAWALUMINIUM, ModBlocks.ALUMINIUMORE, ModBlocks.DEEPSLATEALUMINIUMORE);
        List<ItemLike> GLASS_SMELTABLES = List.of(ModItems.GLASSSHARDS);

        oreSmelting(ALUMINIUM_SMELTABLES, RecipeCategory.MISC, ModItems.ALUMINIUMINGOT.get(), 0.25f, 200, "aluminium");
        oreBlasting(ALUMINIUM_SMELTABLES, RecipeCategory.MISC, ModItems.ALUMINIUMINGOT.get(), 0.25f, 100, "aluminium");

        oreSmelting(GLASS_SMELTABLES, RecipeCategory.MISC, ModItems.MOLTENGLASS.get(), 0.25f, 200, "glass");
        oreBlasting(GLASS_SMELTABLES, RecipeCategory.MISC, ModItems.MOLTENGLASS.get(), 0.25f, 100, "glass");


        shaped(RecipeCategory.MISC, ModItems.GLASSPIECE.get())
                .pattern("##")
                .pattern("##")
                .define('#', ModItems.MOLTENGLASS.get())
                .unlockedBy("has_glassshards", has(ModItems.GLASSSHARDS))
                .save(this.output);

        shaped(RecipeCategory.MISC, Blocks.GLASS)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.GLASSPIECE.get())
                .unlockedBy("has_glasspiece", has(ModItems.GLASSPIECE))
                .save(this.output);

        shaped(RecipeCategory.MISC, ModBlocks.ALUMINIUMBLOCK)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.ALUMINIUMINGOT.get()).unlockedBy("has_aluminium_ingot", has(ModItems.ALUMINIUMINGOT))
                .save(this.output);

        shaped(RecipeCategory.MISC, ModItems.ALUMINIUM_HELMET)
                .pattern("BBB")
                .pattern("BCB")
                .define('B', ModItems.ALUMINIUMINGOT.get())
                .define('C', Items.DIAMOND)
                .unlockedBy("has_aluminium_ingot", has(ModItems.ALUMINIUMINGOT))
                .save(this.output);
//
//        shapeless(RecipeCategory.MISC, ModItems.BISMUTH.get(), 9)
//                .requires(ModBlocks.BISMUTH_BLOCK)
//                .unlockedBy("has_bismuth_block", has(ModBlocks.BISMUTH_BLOCK)).save(this.output);
//
//        shapeless(RecipeCategory.MISC, ModItems.BISMUTH.get(), 18)
//                .requires(ModBlocks.MAGIC_BLOCK)
//                .unlockedBy("has_magic_block", has(ModBlocks.MAGIC_BLOCK))
//                .save(this.output, "tutorialmod:bismuth_from_magic_block");
//
//        oreSmelting(this.output, BISMUTH_SMELTABLES, RecipeCategory.MISC, ModItems.BISMUTH.get(), 0.25f, 200, "bismuth");
//        oreBlasting(this.output, BISMUTH_SMELTABLES, RecipeCategory.MISC, ModItems.BISMUTH.get(), 0.25f, 100, "bismuth");
//
//        stairBuilder(ModBlocks.BISMUTH_STAIRS.get(), Ingredient.of(ModItems.BISMUTH)).group("bismuth")
//                .unlockedBy("has_bismuth", has(ModItems.BISMUTH)).save(this.output);
//        slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BISMUTH_SLAB.get(), ModItems.BISMUTH.get());
//
//        buttonBuilder(ModBlocks.BISMUTH_BUTTON.get(), Ingredient.of(ModItems.BISMUTH.get())).group("bismuth")
//                .unlockedBy("has_bismuth", has(ModItems.BISMUTH.get())).save(this.output);
//        pressurePlate(ModBlocks.BISMUTH_PRESSURE_PLATE.get(), ModItems.BISMUTH.get());
//
//        fenceBuilder(ModBlocks.BISMUTH_FENCE.get(), Ingredient.of(ModItems.BISMUTH.get())).group("bismuth")
//                .unlockedBy("has_bismuth", has(ModItems.BISMUTH.get())).save(this.output);
//        fenceGateBuilder(ModBlocks.BISMUTH_FENCE_GATE.get(), Ingredient.of(ModItems.BISMUTH.get())).group("bismuth")
//                .unlockedBy("has_bismuth", has(ModItems.BISMUTH.get())).save(this.output);
//        wall(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BISMUTH_WALL.get(), ModItems.BISMUTH.get());
//
//        doorBuilder(ModBlocks.BISMUTH_DOOR.get(), Ingredient.of(ModItems.BISMUTH.get())).group("bismuth")
//                .unlockedBy("has_bismuth", has(ModItems.BISMUTH.get())).save(this.output);
//        trapdoorBuilder(ModBlocks.BISMUTH_TRAPDOOR.get(), Ingredient.of(ModItems.BISMUTH.get())).group("bismuth")
//                .unlockedBy("has_bismuth", has(ModItems.BISMUTH.get())).save(this.output);
//
//        trimSmithing(ModItems.KAUPEN_SMITHING_TEMPLATE.get(), ResourceKey.create(Registries.RECIPE,
//                ResourceLocation.parse(getItemName(ModItems.KAUPEN_SMITHING_TEMPLATE) + "_smithing_trim")));
    }

    protected void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                               float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                               float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, TintedGlass.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
