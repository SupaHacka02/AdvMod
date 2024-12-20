package com.mod.advmod.entity.weapon;

import com.mod.advmod.entity.ModEntities;
import com.mod.advmod.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

public class DynamiteStickEntity extends ThrowableItemProjectile {

    public DynamiteStickEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public DynamiteStickEntity(Level pLevel) {
        super(ModEntities.DYNAMITE_STICK_ENTITY.get(), pLevel);
    }

    public DynamiteStickEntity(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.DYNAMITE_STICK_ENTITY.get() , livingEntity, pLevel);
    }

    protected void explode() {
        this.level().explode(this, this.getX(), this.getY(0.0625), this.getZ(), 4.0F, Level.ExplosionInteraction.TNT);
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        this.explode();
        this.discard();
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.DYNAMITE_STICK.get();
    }
}
