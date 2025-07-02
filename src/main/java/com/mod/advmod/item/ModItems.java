package com.mod.advmod.item;

import com.mod.advmod.AdvMod;
import com.mod.advmod.item.ammo.BirdShotPelletsItem;
import com.mod.advmod.item.ammo.MusketBallItem;
import com.mod.advmod.item.weapon.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, AdvMod.MODID);

    public static final DeferredRegister<Item> DB =
            DeferredRegister.create(ForgeRegistries.ITEMS, AdvMod.MODID);


    public static final RegistryObject<Item> REINFORCED_STICK = ITEMS.register("reinforced_stick",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DYNAMITE_STICK = ITEMS.register("dynamite_stick",
            () -> new DynamiteStickItem(new Item.Properties()));

    public static final RegistryObject<Item> DYNAMITE_BUNDLE = ITEMS.register("dynamite_bundle",
            () -> new DynamiteBundleItem(new Item.Properties()));

    public static final RegistryObject<Item> BUNKER_BUSTER = ITEMS.register("bunker_buster",
            () -> new BunkerBusterItem(new Item.Properties()));

    public static final RegistryObject<Item> MUSKET_BALL = ITEMS.register("musket_ball",
            () -> new MusketBallItem(new Item.Properties()));
    public static final RegistryObject<Item> BIRD_SHOT_PELLETS = ITEMS.register("bird_shot_pellets",
            () -> new BirdShotPelletsItem(new Item.Properties()));

    public static final RegistryObject<Item> MUSKET = ITEMS.register("musket",
            () -> new MusketItem(new Item.Properties().durability(196).stacksTo(1)));

    public static final RegistryObject<Item> BLUNDERBUSS = ITEMS.register("blunderbuss",
            () -> new BlunderBussItem(new Item.Properties().durability(196).stacksTo(1)));


    public static final RegistryObject<Item> DOUBLEBARREL = DB.register("doublebarrel",
            () -> new DoubleBarrelItem(new Item.Properties().durability(196).stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
