package com.mod.advmod.entity.weapon;

import com.mod.advmod.entity.ModEntities;
import com.mod.advmod.item.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;


public class BirdShotPelletEntity extends ThrowableItemProjectile {

    private final int BASEDAMAGE = 4;
    private final int MAX_TIME = 45;
    private volatile int time = 0;
    private Level lvl;
    public BirdShotPelletEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.lvl = pLevel;
    }

    public BirdShotPelletEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, double pX, double pY, double pZ, Level pLevel) {
        super(pEntityType, pX, pY, pZ, pLevel);
        this.lvl = pLevel;
    }

    public BirdShotPelletEntity(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.BIRD_SHOT_PELLET_ENTITY.get(), livingEntity, pLevel);
        this.lvl = pLevel;
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);

        Arrow arrow = new Arrow(EntityType.ARROW, this.lvl);
        Entity entity = pResult.getEntity();
        LivingEntity entity2 = (LivingEntity) entity;
        double d0 = Math.max(0.0, 1.0 - entity2.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
        Vec3 vec3 = this.getDeltaMovement().multiply(1.0, 0.0, 1.0).normalize().scale(3.0 * 0.6 * d0);
        if (vec3.lengthSqr() > 0.0) {
            entity.push(vec3.x, 0.1, vec3.z);
        }

        entity.invulnerableTime = 0;

        Entity entity1 = this.getOwner();
        DamageSource damagesource;
        if (entity1 == null) {
            damagesource = this.damageSources().generic();
        } else {
            damagesource = this.damageSources().arrow(arrow, entity1);
            if (entity1 instanceof LivingEntity) {
                ((LivingEntity)entity1).setLastHurtMob(entity);
            }
        }

        if (entity.hurt(damagesource, (float) BASEDAMAGE)) {


            if (entity instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity)entity;


                if (!this.level().isClientSide && entity1 instanceof LivingEntity) {
                    EnchantmentHelper.doPostHurtEffects(livingentity, entity1);
                    EnchantmentHelper.doPostDamageEffects((LivingEntity)entity1, livingentity);
                }

                if (entity1 != null && livingentity != entity1 && livingentity instanceof Player && entity1 instanceof ServerPlayer && !this.isSilent()) {
                    ((ServerPlayer)entity1).connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.ARROW_HIT_PLAYER, 0.0F));
                }
            }


        } else {
            this.deflect(ProjectileDeflection.REVERSE, entity, this.getOwner(), false);
            this.setDeltaMovement(this.getDeltaMovement().scale(0.2));
            if (!this.level().isClientSide && this.getDeltaMovement().lengthSqr() < 1.0E-7) {
                this.discard();
            }
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if(this.time != this.MAX_TIME ) {
            this.lvl.destroyBlock(pResult.getBlockPos(), true);
        }
        this.discard();
    }
    @Override
    public void tick() {
        super.tick();
        if(this.time < this.MAX_TIME) {
            this.time++;
        }
        for (int i = 0; i < 10; i++) {
            this.level().addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE,
                    true,
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
        return ModItems.BIRD_SHOT_PELLETS.get();
    }
}
