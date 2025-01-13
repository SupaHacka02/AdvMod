package com.mod.advmod.event;

import com.mod.advmod.AdvMod;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AdvMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BlockBreakEventHandler {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {

        if(event.getState().getBlock() == Blocks.BROWN_MUSHROOM) {
            if(!event.getLevel().isClientSide()) {
                Creeper creeper = new Creeper(EntityType.CREEPER, (Level) event.getLevel());
                creeper.setPos(event.getPos().getCenter());
                event.getLevel().addFreshEntity(creeper);
            }
        }
    }
}
