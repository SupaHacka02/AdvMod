package com.mod.advmod.entity.weapon;

import com.mod.advmod.entity.ModEntities;
import com.mod.advmod.item.ModItems;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class MusketBallEntity extends ThrowableItemProjectile {
    public MusketBallEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public MusketBallEntity(Level pLevel) {
        super(ModEntities.MUSKET_BALL_ENTITY.get(), pLevel);
    }

    public MusketBallEntity(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.MUSKET_BALL_ENTITY.get(), livingEntity, pLevel);
    }
    protected void explode() {
        this.level().explode(this, this.getX(), this.getY(0.0625), this.getZ(), 5.565F, Level.ExplosionInteraction.TNT);
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.explode();
            this.discard();
        }
    }
    @Override
    public void tick() {
        super.tick();
        for (int i = 0; i < 10; i++) {
            this.level().addParticle(ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, 255, 0, 0),
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    0.0,
                    0.0,
                    0.0);
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.MUSKET_BALL.get();
    }
}
