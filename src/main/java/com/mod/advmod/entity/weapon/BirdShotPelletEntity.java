package com.mod.advmod.entity.weapon;

import com.mod.advmod.entity.ModEntities;
import com.mod.advmod.item.ModItems;
import com.mod.advmod.util.IndestructibleBlocks;
import com.mod.advmod.util.TwentySevenBlocks;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Random;


public class BirdShotPelletEntity extends ThrowableItemProjectile {

    private final int BASEDAMAGE = 2;
    private boolean hasWallBreaker = false;
    private boolean hasDragonsBreath = false;
    private Random smokeRandom = new Random();
    public BirdShotPelletEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public BirdShotPelletEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, double pX, double pY, double pZ, Level pLevel) {
        super(pEntityType, pX, pY, pZ, pLevel);
    }
    public BirdShotPelletEntity(Level pLevel, LivingEntity livingEntity, boolean wb, boolean db) {
        super(ModEntities.BIRD_SHOT_PELLET_ENTITY.get(), livingEntity, pLevel);
        this.hasWallBreaker = wb;
        this.hasDragonsBreath = db;
    }
    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);

        Arrow arrow = new Arrow(EntityType.ARROW, this.level());
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

                if (this.isHasDragonsBreath()) {
                    entity.setRemainingFireTicks(800);
                    entity.setSharedFlagOnFire(true);
                }


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
        BlockPos pos = pResult.getBlockPos();
        BlockState bs = BaseFireBlock.getState(this.level(), pos.above());
        if (this.isHasWallBreaker()) { //bs.getSpeed() >= 2.1 || bs.getTime() < bs.GRACE
            TwentySevenBlocks tb = new TwentySevenBlocks(this.level(), pResult.getBlockPos());
            if (!IndestructibleBlocks.blocks.contains(this.level().getBlockState(pos).getBlock())) {
                tb.destroySmallCrossAndDrop();
            }
        }
        if (this.isHasDragonsBreath()) { //bs.getSpeed() >= 2.1 || bs.getTime() < bs.GRACE
            if (!IndestructibleBlocks.blocks.contains(this.level().getBlockState(pos).getBlock()) && (
                    this.level().getBlockState(pos.above()).getBlock() == Blocks.AIR)) {
                this.level().setBlockAndUpdate(pos.above(), bs);
            }
        }
        this.discard();
    }
    @Override
    public void tick() {
        super.tick();
        for (int i = 0; i < 10; i++) {
            this.level().addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE,
                    true,
                    this.getX() + smokeRandom.nextFloat(-0.25F, 0.25F),
                    this.getY() + smokeRandom.nextFloat(-0.25F, 0.25F),
                    this.getZ() + smokeRandom.nextFloat(-0.25F, 0.25F),
                    0.0,
                    0.0,
                    0.0);
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.BIRD_SHOT_PELLETS.get();
    }
    public boolean isHasWallBreaker() {
        return this.hasWallBreaker;
    }
    public boolean isHasDragonsBreath() {
        return this.hasDragonsBreath;
    }
}
