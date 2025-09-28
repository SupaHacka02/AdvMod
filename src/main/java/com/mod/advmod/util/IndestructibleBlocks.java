package com.mod.advmod.util;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public record IndestructibleBlocks() {
    public static Set<Block> blocks = new HashSet<>(
            Arrays.asList(
                    Blocks.OBSIDIAN,
                    Blocks.COMMAND_BLOCK,
                    Blocks.JIGSAW,
                    Blocks.STRUCTURE_BLOCK,
                    Blocks.END_GATEWAY,
                    Blocks.END_PORTAL,
                    Blocks.END_PORTAL_FRAME,
                    Blocks.BEDROCK,
                    Blocks.BARRIER)
    );
}
