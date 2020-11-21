package com.flanks255.simplylight.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

public class LightBulb extends RotatableLamp {
    public LightBulb() {
        super(Block.Properties.create(Material.MISCELLANEOUS)
                .doesNotBlockMovement()
                .harvestLevel(0)
                .harvestTool(ToolType.PICKAXE)
                .hardnessAndResistance(1.0f)
                .setLightLevel((bState) -> 14));
        UP = VoxelShapes.create(0.375,0.0, 0.375,0.625, 0.3125,0.625);
        DOWN = VoxelShapes.create(0.375, 1.0, 0.375, 0.625, 0.6875, 0.625);
        EAST = VoxelShapes.create(0.0, 0.375, 0.375, 0.3125, 0.625, 0.625);
        WEST = VoxelShapes.create(0.6875, 0.375, 0.375, 1.0, 0.625, 0.625);
        NORTH = VoxelShapes.create(0.375, 0.375, 0.6875, 0.625, 0.625, 1.0);
        SOUTH = VoxelShapes.create(0.375, 0.375, 0.325, 0.625, 0.625, 0.0);
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 14;
    }
}
