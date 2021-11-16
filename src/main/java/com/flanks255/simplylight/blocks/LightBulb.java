package com.flanks255.simplylight.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

public class LightBulb extends RotatableLamp {
    public LightBulb() {
        super(Block.Properties.of(Material.DECORATION)
                .noCollission()
                .harvestLevel(0)
                .harvestTool(ToolType.PICKAXE)
                .strength(1.0f)
                .lightLevel((bState) -> 14));
        UP = Block.box(6, 0, 6, 10, 5, 10);
        DOWN = Block.box(6, 16, 6, 10, 11, 10);
        EAST = Block.box(0, 6, 6, 5, 10, 10);
        WEST = Block.box(11, 6, 6, 16, 11, 11);
        NORTH = Block.box(6, 6, 11, 10, 10, 16);
        SOUTH = Block.box(6, 6, 5, 10, 10, 0);
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 14;
    }
}
