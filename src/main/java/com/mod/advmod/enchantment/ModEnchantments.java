package com.mod.advmod.enchantment;

import com.mod.advmod.AdvMod;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, AdvMod.MODID);
//    public static final RegistryObject<Enchantment> DRAGONS_BREATH = ENCHANTMENTS.register("dragons_breath", () -> new DragonsBreathEnchantment(
//            Enchantment.definition(ItemTags.))
//    );
    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}
