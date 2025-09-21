package com.mod.advmod.event.enchantmentevents;

import com.mod.advmod.AdvMod;
import com.mod.advmod.entity.weapon.BirdShotPelletEntity;
import com.mod.advmod.util.TwentySevenBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AdvMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class DragonsBreathEvent {
    @SubscribeEvent
    public static void onBlockImpact(ProjectileImpactEvent event) {
        if (event.getRayTraceResult().getType().equals(HitResult.Type.BLOCK) && event.getProjectile() instanceof BirdShotPelletEntity) {
            BirdShotPelletEntity bs = (BirdShotPelletEntity) event.getProjectile();
            bs.level().broadcastEntityEvent(bs, (byte) 3);
            if (!bs.level().isClientSide()) {
                if (bs.flag) { //bs.getSpeed() >= 2.1 || bs.getTime() < bs.GRACE
                    TwentySevenBlocks tb = new TwentySevenBlocks(bs.level(), bs.getBlockHitPos());
                    tb.destroySmallCrossAndDrop();
                    //bs.level().destroyBlock(bs.getBlockHitPos(), true);
                    bs.discard();
                }
            }
        }
    }
}
