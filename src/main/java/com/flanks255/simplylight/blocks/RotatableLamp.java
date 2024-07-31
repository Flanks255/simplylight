package com.flanks255.simplylight.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecation")
public abstract class RotatableLamp extends LampBase implements SimpleWaterloggedBlock {
    public RotatableLamp(Properties props) {
        super(props);
        registerDefaultState(getStateDefinition().any().setValue(BlockStateProperties.WATERLOGGED, false));
    }
    public VoxelShape DOWN;
    public VoxelShape UP;
    public VoxelShape NORTH;
    public VoxelShape SOUTH;
    public VoxelShape WEST;
    public VoxelShape EAST;

    
    @Override
    public VoxelShape getShape(BlockState blockState,  BlockGetter world,  BlockPos blockPos,  CollisionContext context) {
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
    public boolean canPlaceLiquid(Player player,  BlockGetter blockGetter,  BlockPos blockPos,  BlockState blockState,  Fluid fluid) {
        return true;
    }

    
    @Override
    public FluidState getFluidState(BlockState blockState) {
        return blockState.getValue(BlockStateProperties.WATERLOGGED)? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.FACING, BlockStateProperties.WATERLOGGED);
    }

    
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean waterlogged = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        return defaultBlockState().setValue(BlockStateProperties.FACING, context.getClickedFace()).setValue(BlockStateProperties.WATERLOGGED, waterlogged);
    }

    @Override
    
    public BlockState updateShape(BlockState blockState,  Direction direction,  BlockState blockState2,  LevelAccessor levelAccessor,  BlockPos blockPos,  BlockPos blockPos2) {
        if (blockState.getValue(BlockStateProperties.WATERLOGGED))
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        return super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }

    
    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(BlockStateProperties.FACING)));
    }

    
    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(BlockStateProperties.FACING, pRotation.rotate(pState.getValue(BlockStateProperties.FACING)));
    }
}
