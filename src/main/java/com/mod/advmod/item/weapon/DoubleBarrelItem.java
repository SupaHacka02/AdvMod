package com.mod.advmod.item.weapon;

import com.mod.advmod.util.PowderedWeaponType;
import net.minecraft.world.item.CrossbowItem;

public class DoubleBarrelItem extends CrossbowItem {
    private PowderedWeaponType type;
    public DoubleBarrelItem(Properties pProperties) {
        super(pProperties);
        this.type = PowderedWeaponType.DOUBLEBARREL;
    }
}
