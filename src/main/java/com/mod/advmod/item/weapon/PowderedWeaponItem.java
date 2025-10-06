package com.mod.advmod.item.weapon;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.UseAnim;

import javax.annotation.Nullable;
import java.util.function.Predicate;

abstract public class PowderedWeaponItem extends ProjectileWeaponItem {
    public PowderedWeaponItem(Properties pProperties) {
        super(pProperties);
    }

    protected float getPowerForTime(int pCharge, float base) {
        float f = (float)pCharge / base;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }
        return f;
    }
    @Override
    protected void shootProjectile(
            LivingEntity pShooter, Projectile pProjectile, int pIndex, float pVelocity, float pInaccuracy, float pAngle, @Nullable LivingEntity pTarget
    ) {
        pProjectile.shootFromRotation(pShooter, pShooter.getXRot(), pShooter.getYRot() + pAngle, 0.0F, pVelocity, pInaccuracy);
    }
    @Override
    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }
    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return ARROW_ONLY;
    }
    @Override
    public int getDefaultProjectileRange() {
        return 15;
    }
}
