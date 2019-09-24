package com.flanks255.simplylight.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public abstract class RotatableLamp extends LampBase implements IWaterLoggable {
    public RotatableLamp(String name, Properties props) {
        super(name, props);
    }
    public VoxelShape DOWN;
    public VoxelShape UP;
    public VoxelShape NORTH;
    public VoxelShape SOUTH;
    public VoxelShape WEST;
    public VoxelShape EAST;

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        VoxelShape ret;
        Direction facing = p_220053_1_.get(BlockStateProperties.FACING);
        //D-U-N-S-W-E
        switch (facing.getIndex()) {
            case 0:
                ret = DOWN;
                break;
            default:
            case 1:
                ret = UP;
                break;
            case 2:
                ret = NORTH;
                break;
            case 3:
                ret = SOUTH;
                break;
            case 4:
                ret = WEST;
                break;
            case 5:
                ret = EAST;
                break;
        }
        return ret;
    }

    @Override
    public boolean canContainFluid(IBlockReader p_204510_1_, BlockPos p_204510_2_, BlockState p_204510_3_, Fluid p_204510_4_) {
        return true;
    }

    @Override
    public IFluidState getFluidState(BlockState p_204507_1_) {
        return p_204507_1_.get(BlockStateProperties.WATERLOGGED)? Fluids.WATER.getStillFluidState(false) : super.getFluidState(p_204507_1_);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(BlockStateProperties.FACING, BlockStateProperties.WATERLOGGED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
        boolean waterlogged = p_196258_1_.getWorld().getFluidState(p_196258_1_.getPos()).getFluid() == Fluids.WATER;
        return getDefaultState().with(BlockStateProperties.FACING, p_196258_1_.getFace()).with(BlockStateProperties.WATERLOGGED, waterlogged);
    }

    @Override
    public int getLightValue(BlockState p_149750_1_) {
        return 15;
    }

}
