package com.mod.advmod.enchantment;

import com.mod.advmod.item.weapon.BlunderBussItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;

public class DragonsBreathEnchantment extends Enchantment {
    public DragonsBreathEnchantment(Enchantment.EnchantmentDefinition definition) {
        super(definition);
    }
//    @Override
//    public boolean canEnchant(ItemStack pStack) {
//        return ((pStack.getItem() instanceof net.minecraft.world.item.AxeItem || super.canEnchant(pStack)) && ((Boolean)JLMEConfiguration.ADEPT.get()).booleanValue());
//    }
}
