package org.vanharneveldt.tinted_glass.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.vanharneveldt.tinted_glass.TintedGlass;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_ALUMINIUM_TOOL = createTag("needs_aluminium_tool");
        public static final TagKey<Block> INCORRECT_FOR_ALUMINIUM_TOOL = createTag("incorrect_for_aluminium_tool");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> ALUMINIUM_REPAIR = createTag("aluminium_repair");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, name));
        }
    }
}