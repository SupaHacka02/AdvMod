package com.mod.advmod.entity;

import com.mod.advmod.AdvMod;
import com.mod.advmod.entity.weapon.BunkerBusterEntity;
import com.mod.advmod.entity.weapon.DynamiteBundleEntity;
import com.mod.advmod.entity.weapon.DynamiteStickEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, AdvMod.MODID);

    public static final RegistryObject<EntityType<DynamiteStickEntity>> DYNAMITE_STICK_ENTITY =
            ENTITY_TYPES.register("dynamite_stick", () -> EntityType.Builder.<DynamiteStickEntity>of(DynamiteStickEntity::new, MobCategory.MISC)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .sized(0.5f, 0.5f)
                    .build("dynamite_stick"));

    public static final RegistryObject<EntityType<DynamiteBundleEntity>> DYNAMITE_BUNDLE_ENTITY =
            ENTITY_TYPES.register("dynamite_bundle", () -> EntityType.Builder.<DynamiteBundleEntity>of(DynamiteBundleEntity::new, MobCategory.MISC)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .sized(0.5f, 0.5f)
                    .build("dynamite_bundle"));

    public static final RegistryObject<EntityType<BunkerBusterEntity>> BUNKER_BUSTER_ENTITY =
            ENTITY_TYPES.register("bunker_buster", () -> EntityType.Builder.<BunkerBusterEntity>of(BunkerBusterEntity::new, MobCategory.MISC)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .sized(0.5f, 0.5f)
                    .build("bunker_buster"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
