package com.mod.advmod.item.weapon;

import com.mod.advmod.enchantment.ModEnchantments;
import com.mod.advmod.item.ModItems;
import com.mod.advmod.item.ammo.BirdShotPelletsItem;
import com.mod.advmod.util.PowderedWeaponType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
public class BlunderBussItem extends PowderedWeaponItem {
    public BlunderBussItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        itemstack.getEnchantments();
        boolean flag = pPlayer.getInventory().contains(new ItemStack(ModItems.BIRD_SHOT_PELLETS.get())) || pPlayer.isCreative();
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
            if (player.getInventory().contains((new ItemStack(ModItems.BIRD_SHOT_PELLETS.get())  )) || player.isCreative()) {
                int i = this.getUseDuration(pStack) - pTimeLeft;
                i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(pStack, pLevel, player, i, true);
                if (i < 0) return;
                float f = this.getPowerForTime(i, 50.0F);
                if( f >= 1 && !pLevel.isClientSide) {
                    int wb = pStack.getEnchantmentLevel(ModEnchantments.WALL_BREAKER.get());
                    int db = pStack.getEnchantmentLevel(ModEnchantments.DRAGONS_BREATH.get());
                    int c = pStack.getEnchantmentLevel(ModEnchantments.CHOKE.get());
                    PelletCluster pc = new PelletCluster(pLevel, player, PowderedWeaponType.BLUNDERBUSS, wb >= 1 ? true : false, db >= 1 ? true :false, c >= 1 ? c : 0);
                    pc.spawnPelletCluster();
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
                }
                if (!player.isCreative()) {
                    for (int k = 0; k < player.getInventory().getContainerSize(); k++) {
                        ItemStack item = player.getInventory().getItem(k);
                        if (!item.isEmpty() && item.getItem() instanceof BirdShotPelletsItem) {
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
