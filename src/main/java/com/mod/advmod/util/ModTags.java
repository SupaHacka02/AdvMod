package com.mod.advmod.util;

import com.mod.advmod.AdvMod;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> BLUNDERBUSS_TAG = ItemTags.create(new ResourceLocation(AdvMod.MODID, "blunderbuss_group"));

        //        public static final TagKey<Item> BLUNDERBUSS_TAG = tag("item/blunderbuss_group");
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(AdvMod.MODID, name));
        }
    }
}
