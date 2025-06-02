package com.mod.advmod.item.weapon;

import com.mod.advmod.item.ModItems;
import com.mod.advmod.util.PowderedWeaponType;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MusketItem extends PowderedWeaponItem {
    public MusketItem(Properties pProperties) {
        super(pProperties);
        super.setType(PowderedWeaponType.MUSKET);
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
}