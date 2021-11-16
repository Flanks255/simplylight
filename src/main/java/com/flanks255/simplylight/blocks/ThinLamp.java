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
    public ThinLamp(double thickness) {
        super(Block.Properties.of(new Material(
                MaterialColor.TERRACOTTA_WHITE,
                false, //isLiquid
                true,  //isSolid
                true, //Blocks Movement
                false, //isOpaque
                false, //isFlammable
                false, //isReplaceable
                PushReaction.NORMAL
        )).strength(1.0f).harvestLevel(0).harvestTool(ToolType.PICKAXE));

        UP = Block.box(0,0,0, 16,thickness,16);
        DOWN = Block.box(0,16 - thickness,0, 16,16,16);
        EAST = Block.box(0,0,0, thickness,16,16);
        WEST = Block.box(16 - thickness,0,0, 16,16,16);
        NORTH = Block.box(0,0,16 - thickness, 16,16,16);
        SOUTH = Block.box(0,0,0, 16,16,thickness);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext useContext) {
        BlockPos pos = useContext.getClickedPos().relative(useContext.getClickedFace().getOpposite());
        boolean waterlogged = useContext.getLevel().getFluidState(useContext.getClickedPos()).getType() == Fluids.WATER;
        if (useContext.getPlayer() != null && useContext.getLevel().getBlockState(pos).getBlock() instanceof ThinLamp && !useContext.getPlayer().isCrouching())
            return defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, waterlogged).setValue(BlockStateProperties.FACING, useContext.getLevel().getBlockState(pos).getValue(BlockStateProperties.FACING));
        else
            return defaultBlockState().setValue(BlockStateProperties.FACING, useContext.getClickedFace()).setValue(BlockStateProperties.WATERLOGGED, waterlogged);
    }

}
