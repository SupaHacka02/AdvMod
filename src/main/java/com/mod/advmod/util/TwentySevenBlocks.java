package com.mod.advmod.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class TwentySevenBlocks {
    private BlockPos b1;
    private BlockPos b2;
    private BlockPos b3;
    private BlockPos b4;
    private BlockPos b5;
    private BlockPos b6;
    private BlockPos b7;
    private BlockPos b8;
    private BlockPos b9;
    private BlockPos b10;
    private BlockPos b11;
    private BlockPos b12;
    private BlockPos b13;
    private BlockPos b14;
    private BlockPos b15;
    private BlockPos b16;
    private BlockPos b17;
    private BlockPos b18;
    private BlockPos b19;
    private BlockPos b20;
    private BlockPos b21;
    private BlockPos b22;
    private BlockPos b23;
    private BlockPos b24;
    private BlockPos b25;
    private BlockPos b26;
    private BlockPos b27;
    private List<BlockPos> blocks = new ArrayList<BlockPos>(27);
    private Level level;
    public TwentySevenBlocks(Level level, BlockPos center) {
        this.setTwentySevenBlocks(center);
        this.level = level;
    }
    /**
     * 3, 9-block layers. input the center block, gets the other 26 surrounding it
     *
     *  ____________          +
     * |_1_|_2_|_3_|          X
     * |_4_|_5_|_6_|          |
     * |_7_|_8_|_9_|          |____Z +
     *
     *  ____________
     * |_10_|_11_|_12_|
     * |_13_|__c_|_15_|
     * |_16_|_17_|_18_|
     *
     *  ____________
     * |_19_|_20_|_21_|
     * |_22_|_23_|_24_|
     * |_25_|_26_|_27_|
     *
     */
    private void setTwentySevenBlocks(BlockPos center) {
        this.b1 = new BlockPos(center.getX() + 1, center.getY() + 1, center.getZ() - 1);
        this.blocks.add(this.b1);
        this.b2 = new BlockPos(center.getX() + 1, center.getY() + 1, center.getZ());
        this.blocks.add(this.b2);
        this.b3 = new BlockPos(center.getX() + 1, center.getY() + 1, center.getZ() + 1);
        this.blocks.add(this.b3);
        this.b4 = new BlockPos(center.getX(), center.getY() + 1, center.getZ() - 1);
        this.blocks.add(this.b4);
        this.b5 = new BlockPos(center.getX(), center.getY() + 1, center.getZ());
        this.blocks.add(this.b5);
        this.b6 = new BlockPos(center.getX(), center.getY() + 1, center.getZ() + 1);
        this.blocks.add(this.b6);
        this.b7 = new BlockPos(center.getX() - 1, center.getY() + 1, center.getZ() - 1);
        this.blocks.add(this.b7);
        this.b8 = new BlockPos(center.getX() - 1, center.getY() + 1, center.getZ());
        this.blocks.add(this.b8);
        this.b9 = new BlockPos(center.getX() - 1, center.getY() + 1, center.getZ() + 1);
        this.blocks.add(this.b9);
        this.b10 = new BlockPos(center.getX() + 1, center.getY(), center.getZ() - 1);
        this.blocks.add(this.b10);
        this.b11 = new BlockPos(center.getX() + 1, center.getY(), center.getZ());
        this.blocks.add(this.b11);
        this.b12 = new BlockPos(center.getX() + 1, center.getY(), center.getZ() + 1);
        this.blocks.add(this.b12);
        this.b13 = new BlockPos(center.getX(), center.getY(), center.getZ() - 1);
        this.blocks.add(this.b13);
        this.b14 = new BlockPos(center);
        this.blocks.add(this.b14);
        this.b15 = new BlockPos(center.getX(), center.getY(), center.getZ() + 1);
        this.blocks.add(this.b15);
        this.b16 = new BlockPos(center.getX() - 1, center.getY(), center.getZ() - 1);
        this.blocks.add(this.b16);
        this.b17 = new BlockPos(center.getX() - 1, center.getY(), center.getZ());
        this.blocks.add(this.b17);
        this.b18 = new BlockPos(center.getX() - 1, center.getY(), center.getZ() + 1);
        this.blocks.add(this.b18);
        this.b19 = new BlockPos(center.getX() + 1, center.getY() - 1, center.getZ() - 1);
        this.blocks.add(this.b19);
        this.b20 = new BlockPos(center.getX() + 1, center.getY() - 1, center.getZ());
        this.blocks.add(this.b20);
        this.b21 = new BlockPos(center.getX() + 1, center.getY() - 1, center.getZ() + 1);
        this.blocks.add(this.b21);
        this.b22 = new BlockPos(center.getX(), center.getY() - 1, center.getZ() - 1);
        this.blocks.add(this.b22);
        this.b23 = new BlockPos(center.getX(), center.getY() - 1, center.getZ());
        this.blocks.add(this.b23);
        this.b24 = new BlockPos(center.getX(), center.getY() - 1, center.getZ() + 1);
        this.blocks.add(this.b24);
        this.b25 = new BlockPos(center.getX() - 1, center.getY() - 1, center.getZ() - 1);
        this.blocks.add(this.b25);
        this.b26 = new BlockPos(center.getX() - 1, center.getY() - 1, center.getZ());
        this.blocks.add(this.b26);
        this.b27 = new BlockPos(center.getX() - 1, center.getY() - 1, center.getZ() + 1);
        this.blocks.add(this.b27);
    }

    public void destroyAllAndDrop() {
        for(BlockPos pos : this.blocks) {
            this.level.destroyBlock(pos, true);
        }
    }
    public void destroyAllNoDrop() {
        for(BlockPos pos : this.blocks) {
            this.level.destroyBlock(pos, false);
        }
    }
    public void destroySmallCrossAndDrop() {
        this.level.destroyBlock(this.b5, true);
        this.level.destroyBlock(this.b11, true);
        this.level.destroyBlock(this.b13, true);
        this.level.destroyBlock(this.b14, true);
        this.level.destroyBlock(this.b15, true);
        this.level.destroyBlock(this.b17, true);
        this.level.destroyBlock(this.b23, true);
    }
    public void destroySpinnerShapeAndDrop() {
        this.level.destroyBlock(this.b5, true);
        this.level.destroyBlock(this.b10, true);
        this.level.destroyBlock(this.b11, true);
        this.level.destroyBlock(this.b12, true);
        this.level.destroyBlock(this.b13, true);
        this.level.destroyBlock(this.b14, true);
        this.level.destroyBlock(this.b15, true);
        this.level.destroyBlock(this.b16, true);
        this.level.destroyBlock(this.b17, true);
        this.level.destroyBlock(this.b18, true);
        this.level.destroyBlock(this.b23, true);
    }
}
