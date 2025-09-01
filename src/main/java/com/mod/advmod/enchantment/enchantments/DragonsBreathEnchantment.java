package com.mod.advmod.enchantment.enchantments;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;

public class DragonsBreathEnchantment extends Enchantment {
    public DragonsBreathEnchantment(EnchantmentDefinition pDefinition) {
        super(pDefinition);
    }
    @Override
    public void doPostAttack(LivingEntity pAttacker, Entity pTarget, int pLevel) {

    }
}
