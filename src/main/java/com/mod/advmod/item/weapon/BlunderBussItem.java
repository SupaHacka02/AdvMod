package com.mod.advmod.item.weapon;

import com.mod.advmod.entity.weapon.MusketBallEntity;
import com.mod.advmod.item.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class BlunderBussItem extends ProjectileWeaponItem {
    public BlunderBussItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        if (pEntityLiving instanceof Player player) {
            if (player.getInventory().contains(new ItemStack(ModItems.MUSKET_BALL.get())) || player.isCreative()) {
                int i = this.getUseDuration(pStack) - pTimeLeft;
                i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(pStack, pLevel, player, i, true);
                if (i < 0) return;

                float f = this.getPowerForTime(i);
                if( f >= 1 && !pLevel.isClientSide) {
                    MusketBallEntity mb = new MusketBallEntity(pLevel, player);
                    mb.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 4.0F, 1.0F);
                    pLevel.addFreshEntity(mb);

                    pLevel.playSound(
                            null,
                            player.getX(),
                            player.getY(),
                            player.getZ(),
                            SoundEvents.GENERIC_EXPLODE.get(),
                            SoundSource.PLAYERS,
                            1.0F,
                            1.0F / (pLevel.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F
                    );
                    if (!player.isCreative()) {
                        for (int k = 0; k < player.getInventory().getContainerSize(); k++) {
                            ItemStack item = player.getInventory().getItem(k);
                            if (!item.isEmpty() && item.getItem() instanceof MusketBallItem) {
                                item.shrink(1);
                                player.getInventory().setChanged();
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    @Override
    protected void shootProjectile(
            LivingEntity pShooter, Projectile pProjectile, int pIndex, float pVelocity, float pInaccuracy, float pAngle, @Nullable LivingEntity pTarget
    ) {
        pProjectile.shootFromRotation(pShooter, pShooter.getXRot(), pShooter.getYRot() + pAngle, 0.0F, pVelocity, pInaccuracy);
    }
    private float getPowerForTime(int pCharge) {
        float f = (float)pCharge / 50.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
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
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        boolean flag = pPlayer.getInventory().contains(new ItemStack(ModItems.MUSKET_BALL.get())) || pPlayer.isCreative();
        if (!pPlayer.hasInfiniteMaterials() && !flag) {
            return InteractionResultHolder.fail(itemstack);
        } else {
            pPlayer.startUsingItem(pHand);
            return InteractionResultHolder.consume(itemstack);
        }
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
