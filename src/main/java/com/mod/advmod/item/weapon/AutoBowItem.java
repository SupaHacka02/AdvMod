package com.mod.advmod.item.weapon;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public class AutoBowItem extends CrossbowItem {
    private final int MAX_AMMO = 16;
    private final int FIRE_RATE = 10;
    private int ammo = 0;
    public AutoBowItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public Predicate<ItemStack> getSupportedHeldProjectiles() {
        return ARROW_OR_FIREWORK;
    }
    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return ARROW_ONLY;
    }
    @Override
    protected void shootProjectile(LivingEntity pShooter, Projectile pProjectile, int pIndex, float pVelocity, float pInaccuracy, float pAngle, @Nullable LivingEntity pTarget) {
        Vector3f vector3f;
        if (pTarget != null) {
            double d0 = pTarget.getX() - pShooter.getX();
            double d1 = pTarget.getZ() - pShooter.getZ();
            double d2 = Math.sqrt(d0 * d0 + d1 * d1);
            double d3 = pTarget.getY(0.3333333333333333) - pProjectile.getY() + d2 * 0.20000000298023224;
            vector3f = getProjectileShotVector(pShooter, new Vec3(d0, d3, d1), pAngle);
        } else {
            Vec3 vec3 = pShooter.getUpVector(1.0F);
            Quaternionf quaternionf = (new Quaternionf()).setAngleAxis((double)(pAngle * 0.017453292F), vec3.x, vec3.y, vec3.z);
            Vec3 vec31 = pShooter.getViewVector(1.0F);
            vector3f = vec31.toVector3f().rotate(quaternionf);
        }
        if (this.ammo > 0) {
            pProjectile.shoot((double)vector3f.x(), (double)vector3f.y(), (double)vector3f.z(), pVelocity, pInaccuracy);
            float f = getShotPitch(pShooter.getRandom(), pIndex);
            pShooter.level().playSound((Player)null, pShooter.getX(), pShooter.getY(), pShooter.getZ(), SoundEvents.CROSSBOW_SHOOT, pShooter.getSoundSource(), 1.0F, f);
        }
    }

    private static Vector3f getProjectileShotVector(LivingEntity pShooter, Vec3 pDistance, float pAngle) {
        Vector3f vector3f = pDistance.toVector3f().normalize();
        Vector3f vector3f1 = (new Vector3f(vector3f)).cross(new Vector3f(0.0F, 1.0F, 0.0F));
        if ((double)vector3f1.lengthSquared() <= 1.0E-7) {
            Vec3 vec3 = pShooter.getUpVector(1.0F);
            vector3f1 = (new Vector3f(vector3f)).cross(vec3.toVector3f());
        }

        Vector3f vector3f2 = (new Vector3f(vector3f)).rotateAxis(1.5707964F, vector3f1.x, vector3f1.y, vector3f1.z);
        return (new Vector3f(vector3f)).rotateAxis(pAngle * 0.017453292F, vector3f2.x, vector3f2.y, vector3f2.z);
    }

    private static float getShotPitch(RandomSource pRandom, int pIndex) {
        return pIndex == 0 ? 1.0F : getRandomShotPitch((pIndex & 1) == 1, pRandom);
    }

    private static float getRandomShotPitch(boolean pIsHighPitched, RandomSource pRandom) {
        float f = pIsHighPitched ? 0.63F : 0.43F;
        return 1.0F / (pRandom.nextFloat() * 0.5F + 1.8F) + f;
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        int i = this.getUseDuration(pStack) - pTimeLeft;
        float f = getPowerForTime(i, pStack);
        if(this.ammo <= 0) {
            if (f >= 1.0F && !isCharged(pStack) && !tryLoadProjectiles(pEntityLiving, pStack)) {
                pLevel.playSound((Player) null, pEntityLiving.getX(), pEntityLiving.getY(), pEntityLiving.getZ(), SoundEvents.CROSSBOW_LOADING_END, pEntityLiving.getSoundSource(), 1.0F, 1.0F / (pLevel.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F);
                this.ammo = this.MAX_AMMO;
            }
        }
        System.out.println("f: " + f);
        System.out.println("isCharged(pStack): " + isCharged(pStack));
        System.out.println("tryLoadProjectiles(pEntityLiving, pStack): " + tryLoadProjectiles(pEntityLiving, pStack));

    }

    private static boolean tryLoadProjectiles(LivingEntity pShooter, ItemStack pCrossbowStack) {
        List<ItemStack> list = draw(pCrossbowStack, pShooter.getProjectile(pCrossbowStack), pShooter);
        if (!list.isEmpty()) {
            pCrossbowStack.set(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.of(list));
            return true;
        } else {
            return false;
        }
    }

    private static float getPowerForTime(int pUseTime, ItemStack pCrossbowStack) {
        float f = (float)pUseTime / (float)getChargeDuration(pCrossbowStack);
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public void performShooting(Level pLevel, LivingEntity pShooter, InteractionHand pHand, ItemStack pWeapon, float pVelocity, float pInaccuracy, @Nullable LivingEntity pTarget) {
        if (!pLevel.isClientSide()) {
            if (pShooter instanceof Player) {
                Player player = (Player)pShooter;
                if (ForgeEventFactory.onArrowLoose(pWeapon, pShooter.level(), player, 1, true) < 0) {
                    return;
                }
            }

            ChargedProjectiles chargedprojectiles = (ChargedProjectiles)pWeapon.set(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY);
            if (chargedprojectiles != null && !chargedprojectiles.isEmpty() && this.ammo > 0) {
                this.shoot(pLevel, pShooter, pHand, pWeapon, chargedprojectiles.getItems(), pVelocity, pInaccuracy, pShooter instanceof Player, pTarget);
                this.ammo--;
                if (pShooter instanceof ServerPlayer) {
                    ServerPlayer serverplayer = (ServerPlayer)pShooter;
                    CriteriaTriggers.SHOT_CROSSBOW.trigger(serverplayer, pWeapon);
                    serverplayer.awardStat(Stats.ITEM_USED.get(pWeapon.getItem()));
                }
            }
        }

    }
}
