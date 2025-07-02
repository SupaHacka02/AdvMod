package com.mod.advmod.event;

import com.mod.advmod.AdvMod;
import com.mod.advmod.item.ModItems;
import com.mod.advmod.item.weapon.DoubleBarrelItem;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = AdvMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModClientEvents {
    @SubscribeEvent
    public static void setup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ModItems.DB.getEntries().forEach(item -> registerCrossbowResourceLocations(item));
        });
    }
    @SubscribeEvent
    public static void onComputerFovModifierEvent(ComputeFovModifierEvent event) {
        if(event.getPlayer().isUsingItem() && event.getPlayer().getUseItem().getItem() == ModItems.MUSKET.get()) {
            float fovModifier = 1f;
            int ticksUsingItem = event.getPlayer().getTicksUsingItem();
            float deltaTicks = (float)ticksUsingItem / 20f;
            if(deltaTicks > 1f) {
                deltaTicks = 1f;
            } else {
                deltaTicks *= deltaTicks;
            }
            fovModifier *= 1f - deltaTicks * 0.15f;
            event.setNewFovModifier(fovModifier);
        }
    }

    private static void registerCrossbowResourceLocations(RegistryObject<Item> item) {
        ItemProperties.register(
                item.get(),
                ResourceLocation.read("pull"),
                (p_340947_, p_340948_, p_340949_, p_340950_) -> {
                    if (p_340949_ == null) {
                        return 0.0F;
                    } else {
                        return DoubleBarrelItem.isCharged(p_340947_)
                                ? 0.0F
                                : (float)(p_340947_.getUseDuration() - p_340949_.getUseItemRemainingTicks()) / (float)DoubleBarrelItem.getChargeDuration(p_340947_, p_340949_);
                    }
                }
        );
        ItemProperties.register(
                item.get(),
                ResourceLocation.withDefaultNamespace("pulling"),
                (p_174605_, p_174606_, p_174607_, p_174608_) -> p_174607_ != null
                        && p_174607_.isUsingItem()
                        && p_174607_.getUseItem() == p_174605_
                        && !DoubleBarrelItem.isCharged(p_174605_)
                        ? 1.0F
                        : 0.0F
        );
        ItemProperties.register(
                item.get(),
                ResourceLocation.withDefaultNamespace("charged"),
                (p_275891_, p_275892_, p_275893_, p_275894_) -> DoubleBarrelItem.isCharged(p_275891_) ? 1.0F : 0.0F
        );
        ItemProperties.register(item.get(), ResourceLocation.withDefaultNamespace("firework"), (p_325563_, p_325564_, p_325565_, p_325566_) -> {
            ChargedProjectiles chargedprojectiles = p_325563_.get(DataComponents.CHARGED_PROJECTILES);
            return chargedprojectiles != null && chargedprojectiles.contains(net.minecraft.world.item.Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
        });
    }
}
