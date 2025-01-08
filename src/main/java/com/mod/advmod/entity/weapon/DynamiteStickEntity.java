package com.mod.advmod.entity.weapon;

import com.mod.advmod.entity.ModEntities;
import com.mod.advmod.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class DynamiteStickEntity extends ThrowableItemProjectile {

    private int tickTillExplosion = 80;

    public DynamiteStickEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public DynamiteStickEntity(Level pLevel) {
        super(ModEntities.DYNAMITE_STICK_ENTITY.get(), pLevel);
    }

    public DynamiteStickEntity(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.DYNAMITE_STICK_ENTITY.get(), livingEntity, pLevel);
    }

    protected void explode() {
        this.level().explode(this, this.getX(), this.getY(0.0625), this.getZ(), 2.0F, Level.ExplosionInteraction.TNT);
    }

    @Override
    public void tick() {
        this.applyGravity();
        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.98));
        if (this.onGround()) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.7, -0.5, 0.7));
        }
        if(this.tickTillExplosion <= 0) {
            if(!this.level().isClientSide) {
                this.explode();
            }
            this.discard();
        }
        this.tickTillExplosion--;
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.DYNAMITE_STICK.get();
    }
}
