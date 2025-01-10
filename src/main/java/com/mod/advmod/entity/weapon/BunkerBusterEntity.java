package com.mod.advmod.entity.weapon;

import com.mod.advmod.entity.ModEntities;
import com.mod.advmod.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class BunkerBusterEntity extends ThrowableItemProjectile {

    private int blockBreakTicks = 40;
    private boolean start = false;
    private boolean hitMovementFlag = false;
    private Vec3 hitMovement;

    public BunkerBusterEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public BunkerBusterEntity(Level pLevel) {
        super(ModEntities.BUNKER_BUSTER_ENTITY.get(), pLevel);
    }

    public BunkerBusterEntity(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.BUNKER_BUSTER_ENTITY.get(), livingEntity, pLevel);
    }

    protected void explode(float explosionSize) {
        this.level().explode(this, this.getX(), this.getY(0.0625), this.getZ(), explosionSize, Level.ExplosionInteraction.TNT);
    }

    @Override
    public void tick() {
        this.applyGravity();
        this.move(MoverType.SELF, this.getDeltaMovement());
        if(!this.hitMovementFlag) {
            this.setDeltaMovement(this.getDeltaMovement().scale(0.98));
        } else {
            this.setDeltaMovement(this.hitMovement);
        }

        HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        if(hitresult.getType() == HitResult.Type.ENTITY) {
            if(!this.level().isClientSide) {
                this.explode(6.5f);
            }
            this.discard();
        }
        if(hitresult.getType() == HitResult.Type.BLOCK) {
            if(!this.start) {
                this.start = true;
            }
            if(!this.hitMovementFlag) {
                this.hitMovement = this.getDeltaMovement();
                this.hitMovementFlag = true;
            }
        }
        if(this.start) {
            this.blockBreakTicks--;
            if(this.blockBreakTicks <= 0) {
                this.discard();
            }
            if(!this.level().isClientSide) {
                this.explode(this.explosionRange());
            }
        }
    }

    private float explosionRange() {
        return ((float)Math.random() * 3f) + 2.05f;
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.BUNKER_BUSTER.get();
    }
}
