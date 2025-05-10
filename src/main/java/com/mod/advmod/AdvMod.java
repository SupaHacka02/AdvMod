package com.mod.advmod;

import com.mod.advmod.entity.ModEntities;
import com.mod.advmod.item.ModItems;
import com.mod.advmod.util.ModItemProperties;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(AdvMod.MODID)
public class AdvMod {
    public static final String MODID = "advmod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public AdvMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);

        ModEntities.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.REINFORCED_STICK);
        }
        if(event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ModItems.DYNAMITE_STICK);
            event.accept(ModItems.DYNAMITE_BUNDLE);
            event.accept(ModItems.BUNKER_BUSTER);
            event.accept(ModItems.MUSKET_BALL);
            event.accept(ModItems.MUSKET);
            event.accept(ModItems.BLUNDERBUSS);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ModItemProperties.addCustomProperties();

            EntityRenderers.register(ModEntities.DYNAMITE_STICK_ENTITY.get(), ThrownItemRenderer::new);
            EntityRenderers.register(ModEntities.DYNAMITE_BUNDLE_ENTITY.get(), ThrownItemRenderer::new);
            EntityRenderers.register(ModEntities.BUNKER_BUSTER_ENTITY.get(), ThrownItemRenderer::new);
            EntityRenderers.register(ModEntities.MUSKET_BALL_ENTITY.get(), ThrownItemRenderer::new);

        }
    }
}
