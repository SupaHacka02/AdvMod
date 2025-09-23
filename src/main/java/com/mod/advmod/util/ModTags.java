package com.mod.advmod.util;

import com.mod.advmod.AdvMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
    public static class Items {
//        private static TagKey<Item> createTag(String name) {
//            return ItemTags.create(ResourceLocation.read())
//        }
    }
    public static final TagKey<Item> blunderbussTag = ItemTags.create(new ResourceLocation(AdvMod.MODID, "blunderbussgroup"));
}
