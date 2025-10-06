package com.mod.advmod.item.weapon;

import com.mod.advmod.entity.weapon.BirdShotPelletEntity;
import com.mod.advmod.util.PowderedWeaponType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.Random;

public class PelletCluster {

    private Level level;
    private Player player;
    private Random rand;
    private int capacity;
    private PowderedWeaponType type;
    private BirdShotPelletEntity[] bsa;
    private boolean hasWallBreaker;
    private boolean hasDragonsBreath;
    private int chokeLevel = 0;
    public PelletCluster(Level level, Player player, PowderedWeaponType type, boolean wb, boolean db, int chokeLevel) {
        this.level = level;
        this.player = player;
        this.rand = new Random();
        this.type = type;
        this.capacity = !this.type.equals(PowderedWeaponType.DOUBLEBARREL) ? 9 : 17;
        this.bsa = new BirdShotPelletEntity[capacity];
        this.hasWallBreaker = wb;
        this.hasDragonsBreath = db;
        this.chokeLevel = chokeLevel;
    }
    protected void spawnPelletCluster() {
        for (int i = 0; i < capacity; i++) {
            bsa[i] = new BirdShotPelletEntity(level, player, hasWallBreaker, hasDragonsBreath);
            if(hasDragonsBreath) {
                bsa[i].setRemainingFireTicks(800);
                bsa[i].setSharedFlagOnFire(true);
            }
        }

        bsa[0].shootFromRotation(player, player.getXRot() + 1 + rand.nextFloat(0, (float)0.5), player.getYRot() + randomness(), 0.0F, 2.2F, 1.0F);
        bsa[1].shootFromRotation(player, player.getXRot() - 1 + rand.nextFloat((float) -0.5, 0), player.getYRot() - randomness(), 0.0F, 2.2F, 1.0F);
        bsa[2].shootFromRotation(player, player.getXRot() + 2 + rand.nextFloat((float) -0.5, (float)0.5), player.getYRot() + randomness(), 0.0F, 2.2F, 1.0F);
        bsa[3].shootFromRotation(player, player.getXRot() - 2 + rand.nextFloat((float) -0.5, (float)0.5), player.getYRot() - randomness(), 0.0F, 2.2F, 1.0F);

        bsa[4].shootFromRotation(player, player.getXRot(), player.getYRot() + 1 + randomness() + rand.nextFloat(0, (float)0.5), 0.0F, 2.2F, 1.0F);
        bsa[5].shootFromRotation(player, player.getXRot(), player.getYRot() + 2 + randomness() + rand.nextFloat((float) -0.5, (float)0.5), 0.0F, 2.2F, 1.0F);
        bsa[6].shootFromRotation(player, player.getXRot(), player.getYRot() - 1 - randomness() + rand.nextFloat((float) -0.5, 0), 0.0F, 2.2F, 1.0F);
        bsa[7].shootFromRotation(player, player.getXRot(), player.getYRot() - 2 - randomness() + rand.nextFloat((float) -0.5, (float)0.5), 0.0F, 2.2F, 1.0F);

        bsa[8].shootFromRotation(player, player.getXRot() + rand.nextFloat((float) -0.25, (float)0.25), player.getYRot() + rand.nextFloat((float) -0.25, (float)0.25), 0.0F, 2.2F, 1.0F);

        if (this.type == PowderedWeaponType.DOUBLEBARREL) {
            bsa[9].shootFromRotation(player, player.getXRot() + 3 + rand.nextFloat(0, (float)0.5), player.getYRot(), 0.0F, 2.2F, 1.0F);
            bsa[10].shootFromRotation(player, player.getXRot() - 3 + rand.nextFloat((float) -0.5, 0), player.getYRot(), 0.0F, 2.2F, 1.0F);
            bsa[11].shootFromRotation(player, player.getXRot() + 4 + rand.nextFloat((float) -0.5, (float)0.5), player.getYRot(), 0.0F, 2.2F, 1.0F);
            bsa[12].shootFromRotation(player, player.getXRot() - 4 + rand.nextFloat((float) -0.5, (float)0.5), player.getYRot(), 0.0F, 2.2F, 1.0F);

            bsa[13].shootFromRotation(player, player.getXRot(), player.getYRot() + 3 + rand.nextFloat(0, (float)0.5), 0.0F, 2.2F, 1.0F);
            bsa[14].shootFromRotation(player, player.getXRot(), player.getYRot() + 4 + rand.nextFloat((float) -0.5, (float)0.5), 0.0F, 2.2F, 1.0F);
            bsa[15].shootFromRotation(player, player.getXRot(), player.getYRot() - 3 + rand.nextFloat((float) -0.5, 0), 0.0F, 2.2F, 1.0F);
            bsa[16].shootFromRotation(player, player.getXRot(), player.getYRot() - 4 + rand.nextFloat((float) -0.5, (float)0.5), 0.0F, 2.2F, 1.0F);
        }

        for (int j = 0; j < capacity; j++) {
            level.addFreshEntity(bsa[j]);
        }
    }
    private float randomness() {
        return rand.nextFloat(0, (9.0F - ((float) chokeLevel/3) * 8.0F) );
    }
}
