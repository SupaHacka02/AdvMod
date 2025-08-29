package com.mod.advmod.item.weapon;

import com.mod.advmod.item.ModItems;
import com.mod.advmod.util.PowderedWeaponState;
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
        System.out.println(this.state);
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        switch (this.state) {
            case EMPTY -> {
                boolean flag = pPlayer.getInventory().contains(new ItemStack(ModItems.BIRD_SHOT_PELLETS.get())) || pPlayer.isCreative();
                if (!pPlayer.hasInfiniteMaterials() && !flag) {
                    this.state = PowderedWeaponState.LOADED;
                    return InteractionResultHolder.fail(itemstack);
                } else {
                    pPlayer.startUsingItem(pHand);
                    this.state = PowderedWeaponState.LOADED;
                    return InteractionResultHolder.consume(itemstack);
                }
            }
            case FIRED -> {
                this.state = PowderedWeaponState.EMPTY;
                return InteractionResultHolder.fail(itemstack);
            }
        }
        return InteractionResultHolder.fail(itemstack);
    }
}
