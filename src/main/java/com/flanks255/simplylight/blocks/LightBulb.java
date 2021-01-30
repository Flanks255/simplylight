package com.flanks255.simplylight.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraftforge.common.ToolType;

public class LightBulb extends RotatableLamp {
    public LightBulb() {
        super(Block.Properties.create(Material.MISCELLANEOUS)
                .doesNotBlockMovement()
                .harvestLevel(0)
                .harvestTool(ToolType.PICKAXE)
                .hardnessAndResistance(1.0f)
                .setLightLevel((bState) -> 14));
        UP = Block.makeCuboidShape(6, 0, 6, 10, 5, 10);
        DOWN = Block.makeCuboidShape(6, 16, 6, 10, 11, 10);
        EAST = Block.makeCuboidShape(0, 6, 6, 5, 10, 10);
        WEST = Block.makeCuboidShape(11, 6, 6, 16, 11, 11);
        NORTH = Block.makeCuboidShape(6, 6, 11, 10, 10, 16);
        SOUTH = Block.makeCuboidShape(6, 6, 5, 10, 10, 0);
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 14;
    }
}
