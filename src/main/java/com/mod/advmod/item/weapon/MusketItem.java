package com.mod.advmod.item.weapon;

import com.mod.advmod.entity.weapon.MusketBallEntity;
import com.mod.advmod.item.ModItems;
import com.mod.advmod.item.ammo.MusketBallItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MusketItem extends PowderedWeaponItem {
    public MusketItem(Properties pProperties) {
        super(pProperties);
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
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        if (pEntityLiving instanceof Player player) {
            if (player.getInventory().contains(new ItemStack(ModItems.MUSKET_BALL.get())) || player.isCreative()) {
                int i = this.getUseDuration(pStack) - pTimeLeft;
                i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(pStack, pLevel, player, i, true);
                if (i < 0) return;

                float f = this.getPowerForTime(i, 50.0F);
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
}