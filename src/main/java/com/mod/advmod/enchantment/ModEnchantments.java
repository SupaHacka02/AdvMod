package com.mod.advmod.enchantment;

import com.mod.advmod.AdvMod;
import com.mod.advmod.util.ModTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Optional;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, AdvMod.MODID);
    public static final RegistryObject<Enchantment> WALL_BREAKER = ENCHANTMENTS.register(
            "wall_breaker",
            () -> new WallBreakerEnchantment(Enchantment.definition(ItemTags.WEAPON_ENCHANTABLE, ModTags.blunderbussTag, 1, 1, Enchantment.dynamicCost(1, 11), Enchantment.dynamicCost(21, 11), 4, new EquipmentSlot[] { EquipmentSlot.MAINHAND })));
    public static final RegistryObject<Enchantment> DRAGONS_BREATH = ENCHANTMENTS.register(
            "dragons_breath",
            () -> new DragonsBreathEnchantment(Enchantment.definition(ItemTags.WEAPON_ENCHANTABLE, ModTags.blunderbussTag, 1, 1, Enchantment.dynamicCost(1, 11), Enchantment.dynamicCost(21, 11), 4, new EquipmentSlot[] { EquipmentSlot.MAINHAND })));
    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}
