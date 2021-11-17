package com.flanks255.simplylight.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import java.util.function.BiConsumer;

public class WallLamp extends RotatableLamp{
    public WallLamp() {
        super(Block.Properties.of(Material.DECORATION).noCollission().harvestLevel(0).harvestTool(ToolType.PICKAXE).strength(1.0f).lightLevel((bState) -> 15));

        UP = VoxelShapes.box(0.375,0.0, 0.375,0.625, 0.125,0.625);
        DOWN = VoxelShapes.box(0.375, 1.0, 0.375, 0.625, 1.0 - 0.125, 0.625);
        EAST = VoxelShapes.box(0.0, 0.375, 0.25, 0.1875, 0.625, 0.75);
        WEST = VoxelShapes.box(1.0, 0.375, 0.25, 1.0 - 0.1875, 0.625, 0.75);
        NORTH = VoxelShapes.box(0.25, 0.375, 1.0, 0.75, 0.625, 1.0 - 0.1875);
        SOUTH = VoxelShapes.box(0.25, 0.375, 0.0, 0.75, 0.625, 0.1875);
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 15;
    }

    @Override
    public void addLang(BiConsumer<String, String> consumer) {
        String base = getLangBase();

        consumer.accept(base, "Illuminant Fixture");
        consumer.accept(base + ".info", "Hangs from walls, or sticks to ceilings and floors.");
    }
}
