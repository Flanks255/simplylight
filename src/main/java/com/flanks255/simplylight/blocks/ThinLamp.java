package com.flanks255.simplylight.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.PushReaction;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class ThinLamp extends RotatableLamp {
    public ThinLamp(String name, double thickness) {
        super(name, Block.Properties.create(new Material(
                MaterialColor.WHITE_TERRACOTTA,
                false, //isLiquid
                true,  //isSolid
                true, //Blocks Movement
                false, //isOpaque
                false, //isFlammable
                false, //isReplaceable
                PushReaction.NORMAL
        )).hardnessAndResistance(1.0f).harvestLevel(0).harvestTool(ToolType.PICKAXE));

        UP = Block.makeCuboidShape(0,0,0, 16,thickness,16);
        DOWN = Block.makeCuboidShape(0,16 - thickness,0, 16,16,16);
        EAST = Block.makeCuboidShape(0,0,0, thickness,16,16);
        WEST = Block.makeCuboidShape(16 - thickness,0,0, 16,16,16);
        NORTH = Block.makeCuboidShape(0,0,16 - thickness, 16,16,16);
        SOUTH = Block.makeCuboidShape(0,0,0, 16,16,thickness);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext useContext) {
        BlockPos pos = useContext.getPos().offset(useContext.getFace().getOpposite());
        boolean waterlogged = useContext.getWorld().getFluidState(useContext.getPos()).getFluid() == Fluids.WATER;
        if (useContext.getPlayer() != null && useContext.getWorld().getBlockState(pos).getBlock() instanceof ThinLamp && !useContext.getPlayer().isCrouching())
            return getDefaultState().with(BlockStateProperties.WATERLOGGED, waterlogged).with(BlockStateProperties.FACING, useContext.getWorld().getBlockState(pos).get(BlockStateProperties.FACING));
        else
            return getDefaultState().with(BlockStateProperties.FACING, useContext.getFace()).with(BlockStateProperties.WATERLOGGED, waterlogged);
    }

}
