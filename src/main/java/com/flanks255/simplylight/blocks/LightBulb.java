package com.flanks255.simplylight.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.PushReaction;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraftforge.common.ToolType;

public class LightBulb extends RotatableLamp {
    public LightBulb(String name) {
        super(name, Block.Properties.create(new Material(
                MaterialColor.WHITE_TERRACOTTA,
                false, //isLiquid
                false,  //isSolid
                false, //Blocks Movement
                false, //isOpaque
                true, //requires no tool
                false, //isFlammable
                false, //isReplaceable
                PushReaction.NORMAL
        )).hardnessAndResistance(1.0f).harvestLevel(0).harvestTool(ToolType.PICKAXE));

        UP = VoxelShapes.create(0.375,0.0, 0.375,0.625, 0.3125,0.625);
        DOWN = VoxelShapes.create(0.375, 1.0, 0.375, 0.625, 0.6875, 0.625);
        EAST = VoxelShapes.create(0.0, 0.375, 0.375, 0.3125, 0.625, 0.625);
        WEST = VoxelShapes.create(0.6875, 0.375, 0.375, 1.0, 0.625, 0.625);
        NORTH = VoxelShapes.create(0.375, 0.375, 0.6875, 0.625, 0.625, 1.0);
        SOUTH = VoxelShapes.create(0.375, 0.375, 0.325, 0.625, 0.625, 0.0);
    }

    @Override
    public int getLightValue(BlockState p_149750_1_) {
        return 14;
    }
/*
    @Override
    public boolean canRenderInLayer(BlockState state, BlockRenderLayer layer) {
        return (layer == BlockRenderLayer.SOLID || layer == BlockRenderLayer.TRANSLUCENT);
    }*/
}
