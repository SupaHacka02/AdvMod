package com.mod.advmod.event.enchantmentevents;

import com.mod.advmod.AdvMod;
import com.mod.advmod.entity.weapon.BirdShotPelletEntity;
import com.mod.advmod.util.TwentySevenBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(modid = AdvMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class WallBreakerEvent {
    @SubscribeEvent
    public static void onBlockImpact(ProjectileImpactEvent event) {
        if (event.getRayTraceResult().getType().equals(HitResult.Type.BLOCK) && event.getProjectile() instanceof BirdShotPelletEntity) {
            BirdShotPelletEntity bs = (BirdShotPelletEntity) event.getProjectile();
            bs.level().broadcastEntityEvent(bs, (byte) 3);
            System.out.println("Does it have WB?: " + bs.isHasWallBreaker());
            if (!bs.level().isClientSide()) {
                System.out.println("1");
                if (bs.isHasWallBreaker()) { //bs.getSpeed() >= 2.1 || bs.getTime() < bs.GRACE
//                    System.out.println("2");
//                    BlockPos pos = getBlockPos(bs);
//                    TwentySevenBlocks tb = new TwentySevenBlocks(bs.level(), pos);
//                    tb.destroySmallCrossAndDrop();
                    //bs.level().destroyBlock(bs.getBlockHitPos(), true);
                }
                bs.discard();
            }
        }
    }

    @NotNull
    private static BlockPos getBlockPos(BirdShotPelletEntity bs) {
        double Nx = bs.getDeltaMovement().x == 0 ? 0 : (bs.getDeltaMovement().x > 0 ? 0.75 : -0.75);
        double Ny = bs.getDeltaMovement().y == 0 ? 0 : (bs.getDeltaMovement().y > 0 ? 0.125 : -0.125);
        double Nz = bs.getDeltaMovement().z == 0 ? 0 : (bs.getDeltaMovement().z > 0 ? 0.75 : -0.75);
        BlockPos pos = new BlockPos(
                (int) (bs.getPosition(0).x + Nx),
                (int) (bs.getPosition(0).y + Ny),
                (int) (bs.getPosition(0).z + Nz));
        return pos;
    }
}
