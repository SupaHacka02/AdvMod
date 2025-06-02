package com.mod.advmod.item.weapon;

import com.mod.advmod.item.ModItems;
import com.mod.advmod.util.PowderedWeaponType;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
public class BlunderBussItem extends PowderedWeaponItem {
    public BlunderBussItem(Properties pProperties) {
        super(pProperties);
        setType(PowderedWeaponType.BLUNDERBUSS);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        boolean flag = pPlayer.getInventory().contains(new ItemStack(ModItems.BIRD_SHOT_PELLETS.get())) || pPlayer.isCreative();
        if (!pPlayer.hasInfiniteMaterials() && !flag) {
            return InteractionResultHolder.fail(itemstack);
        } else {
            pPlayer.startUsingItem(pHand);
            return InteractionResultHolder.consume(itemstack);
        }
    }
}
