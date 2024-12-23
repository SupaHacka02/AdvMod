package com.mod.advmod.entity.weapon;

import com.mod.advmod.entity.ModEntities;
import com.mod.advmod.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class DynamiteBundleEntity extends ThrowableItemProjectile {

    public DynamiteBundleEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public DynamiteBundleEntity(Level pLevel) {
        super(ModEntities.DYNAMITE_BUNDLE_ENTITY.get(), pLevel);
    }

    public DynamiteBundleEntity(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.DYNAMITE_BUNDLE_ENTITY.get(), livingEntity, pLevel);
    }

    @Override
    protected void onHit(HitResult hitResult) {
        this.explode();
        this.discard();
    }

    protected void explode() {
        this.level().explode(this, this.getX(), this.getY(0.0625), this.getZ(), 5.565F, Level.ExplosionInteraction.TNT);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.DYNAMITE_BUNDLE.get();
    }
}
