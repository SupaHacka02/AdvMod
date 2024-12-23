package com.mod.advmod.entity.weapon;

import com.mod.advmod.entity.ModEntities;
import com.mod.advmod.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class BunkerBusterEntity extends ThrowableItemProjectile {

    private int blockBreaks = 5;

    public BunkerBusterEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public BunkerBusterEntity(Level pLevel) {
        super(ModEntities.BUNKER_BUSTER_ENTITY.get(), pLevel);
    }

    public BunkerBusterEntity(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.BUNKER_BUSTER_ENTITY.get(), livingEntity, pLevel);
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if(this.blockBreaks <= 0) {
            this.discard();
        }
        this.blockBreaks--;
        this.explode(4.25f);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        this.explode(6.5f);
        this.discard();
    }

    protected void explode(float explosionSize) {
        this.level().explode(this, this.getX(), this.getY(0.0625), this.getZ(), explosionSize, Level.ExplosionInteraction.TNT);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.BUNKER_BUSTER.get();
    }
}
