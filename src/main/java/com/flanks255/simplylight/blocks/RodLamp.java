package com.flanks255.simplylight.blocks;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.PushReaction;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class RodLamp extends LampBase implements IWaterLoggable {
    public RodLamp (String name) {
        super(name, Block.Properties.create(new Material(
                MaterialColor.WHITE_TERRACOTTA,
                false, //isLiquid
                false,  //isSolid
                true, //Blocks Movement
                false, //isOpaque
                false, //isFlammable
                false, //isReplaceable
                PushReaction.NORMAL
        )).hardnessAndResistance(1.0f).harvestLevel(0).harvestTool(ToolType.PICKAXE));


    }

    private VoxelShape UpDown = Block.makeCuboidShape(7,0,7, 9,16,9);
    private VoxelShape EastWest = Block.makeCuboidShape(0,7,7, 16,9,9);
    private VoxelShape NorthSouth = Block.makeCuboidShape(7,7,0, 9,9,16);

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape ret;
        switch (state.get(BlockStateProperties.AXIS)) {
            case X:
                ret = EastWest;
                break;
            case Y:
                ret = UpDown;
                break;
            default:
                ret = NorthSouth;
                break;
        }
        return ret;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
        return getDefaultState().with(BlockStateProperties.AXIS, p_196258_1_.getFace().getAxis()).with(BlockStateProperties.WATERLOGGED, false);
    }

    @Override
    public boolean canContainFluid(IBlockReader p_204510_1_, BlockPos p_204510_2_, BlockState p_204510_3_, Fluid p_204510_4_) {
        return true;
    }

    @Override
    public FluidState getFluidState(BlockState p_204507_1_) {
        return p_204507_1_.get(BlockStateProperties.WATERLOGGED)? Fluids.WATER.getStillFluidState(false) : super.getFluidState(p_204507_1_);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(BlockStateProperties.AXIS, BlockStateProperties.WATERLOGGED);
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 15;
    }

}
