package com.flanks255.simplylight.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings("deprecation")
public abstract class RotatableLamp extends LampBase implements SimpleWaterloggedBlock {
    public RotatableLamp(Properties props) {
        super(props.lightLevel((bState) -> 15));
        registerDefaultState(getStateDefinition().any().setValue(BlockStateProperties.WATERLOGGED, false));
    }
    public VoxelShape DOWN;
    public VoxelShape UP;
    public VoxelShape NORTH;
    public VoxelShape SOUTH;
    public VoxelShape WEST;
    public VoxelShape EAST;

    @Nonnull
    @Override
    public VoxelShape getShape(BlockState blockState, @Nonnull BlockGetter world, @Nonnull BlockPos blockPos, @Nonnull CollisionContext context) {
        Direction facing = blockState.getValue(BlockStateProperties.FACING);
        //D-U-N-S-W-E
        return switch (facing.get3DDataValue()) {
            case 0 -> DOWN;
            default -> UP;
            case 2 -> NORTH;
            case 3 -> SOUTH;
            case 4 -> WEST;
            case 5 -> EAST;
        };
    }

    @Override
    public boolean canPlaceLiquid(@Nonnull BlockGetter world, @Nonnull BlockPos blockpos, @Nonnull BlockState blockState, @Nonnull Fluid fluid) {
        return true;
    }

    @Nonnull
    @Override
    public FluidState getFluidState(BlockState blockState) {
        return blockState.getValue(BlockStateProperties.WATERLOGGED)? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.FACING, BlockStateProperties.WATERLOGGED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean waterlogged = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        return defaultBlockState().setValue(BlockStateProperties.FACING, context.getClickedFace()).setValue(BlockStateProperties.WATERLOGGED, waterlogged);
    }

    @Override
    @Nonnull
    public BlockState updateShape(BlockState blockState, @Nonnull Direction direction, @Nonnull BlockState blockState2, @Nonnull LevelAccessor levelAccessor, @Nonnull BlockPos blockPos, @Nonnull BlockPos blockPos2) {
        if (blockState.getValue(BlockStateProperties.WATERLOGGED))
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        return super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }

    @Override
    public int getLightBlock(@Nonnull BlockState state, @Nonnull BlockGetter world, @Nonnull BlockPos pos) {
        return 15;
    }

}
