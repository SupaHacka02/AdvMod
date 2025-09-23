package com.mod.advmod.enchantment;

import net.minecraft.world.item.enchantment.Enchantment;

public class WallBreakerEnchantment extends Enchantment {
    public WallBreakerEnchantment(Enchantment.EnchantmentDefinition definition) {
        super(definition);
    }
//    @Override
//    public boolean canEnchant(ItemStack pStack) {
//        return ((pStack.getItem() instanceof net.minecraft.world.item.AxeItem || super.canEnchant(pStack)) && ((Boolean)JLMEConfiguration.ADEPT.get()).booleanValue());
//    }
}
