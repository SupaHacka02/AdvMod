package com.mod.advmod.item.weapon;

import com.mod.advmod.entity.weapon.BirdShotPelletEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.Random;

public class PelletCluster {
    protected static void spawnPelletsCluster(Level pLevel, Player player) {

        Random rand = new Random();

        BirdShotPelletEntity bs1 = new BirdShotPelletEntity(pLevel, player);
        BirdShotPelletEntity bs2 = new BirdShotPelletEntity(pLevel, player);
        BirdShotPelletEntity bs3 = new BirdShotPelletEntity(pLevel, player);
        BirdShotPelletEntity bs4 = new BirdShotPelletEntity(pLevel, player);

        BirdShotPelletEntity bs5 = new BirdShotPelletEntity(pLevel, player);
        BirdShotPelletEntity bs6 = new BirdShotPelletEntity(pLevel, player);
        BirdShotPelletEntity bs7 = new BirdShotPelletEntity(pLevel, player);
        BirdShotPelletEntity bs8 = new BirdShotPelletEntity(pLevel, player);

        BirdShotPelletEntity bs9 = new BirdShotPelletEntity(pLevel, player);

        bs1.shootFromRotation(player, player.getXRot() + 1 + rand.nextFloat(0, (float)0.5), player.getYRot(), 0.0F, 2.2F, 1.0F);
        bs2.shootFromRotation(player, player.getXRot() - 1 + rand.nextFloat((float) -0.5, 0), player.getYRot(), 0.0F, 2.2F, 1.0F);
        bs3.shootFromRotation(player, player.getXRot() + 2 + rand.nextFloat((float) -0.5, (float)0.5), player.getYRot(), 0.0F, 2.2F, 1.0F);
        bs4.shootFromRotation(player, player.getXRot() - 2 + rand.nextFloat((float) -0.5, (float)0.5), player.getYRot(), 0.0F, 2.2F, 1.0F);

        bs5.shootFromRotation(player, player.getXRot(), player.getYRot() + 1 + rand.nextFloat(0, (float)0.5), 0.0F, 2.2F, 1.0F);
        bs6.shootFromRotation(player, player.getXRot(), player.getYRot() + 2 + rand.nextFloat((float) -0.5, (float)0.5), 0.0F, 2.2F, 1.0F);
        bs7.shootFromRotation(player, player.getXRot(), player.getYRot() - 1 + rand.nextFloat((float) -0.5, 0), 0.0F, 2.2F, 1.0F);
        bs8.shootFromRotation(player, player.getXRot(), player.getYRot() - 2 + rand.nextFloat((float) -0.5, (float)0.5), 0.0F, 2.2F, 1.0F);

        bs9.shootFromRotation(player, player.getXRot() + rand.nextFloat((float) -0.25, (float)0.25), player.getYRot() + rand.nextFloat((float) -0.25, (float)0.25), 0.0F, 2.2F, 1.0F);

        pLevel.addFreshEntity(bs1);
        pLevel.addFreshEntity(bs2);
        pLevel.addFreshEntity(bs3);
        pLevel.addFreshEntity(bs4);

        pLevel.addFreshEntity(bs5);
        pLevel.addFreshEntity(bs6);
        pLevel.addFreshEntity(bs7);
        pLevel.addFreshEntity(bs8);

        pLevel.addFreshEntity(bs9);

    }
}
