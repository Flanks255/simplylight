package com.flanks255.simplylight.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.BiConsumer;

@SuppressWarnings("deprecation")
public class RodLamp extends LampBase implements SimpleWaterloggedBlock {
    public RodLamp () {
        super(Properties.of()
                .strength(1.0f)
                .lightLevel($ -> 15));

        registerDefaultState(getStateDefinition().any().setValue(BlockStateProperties.WATERLOGGED, false));
    }

    private final VoxelShape UpDown = Block.box(7,0,7, 9,16,9);
    private final VoxelShape EastWest = Block.box(0,7,7, 16,9,9);
    private final VoxelShape NorthSouth = Block.box(7,7,0, 9,9,16);

    
    @Override
    public VoxelShape getShape(BlockState pState,  BlockGetter pLevel,  BlockPos pPos,  CollisionContext pContext) {
        return switch (pState.getValue(BlockStateProperties.AXIS)) {
            case X -> EastWest;
            case Y -> UpDown;
            default -> NorthSouth;
        };
    }

    
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext placeContext) {
        return defaultBlockState().setValue(BlockStateProperties.AXIS, placeContext.getClickedFace().getAxis()).setValue(BlockStateProperties.WATERLOGGED, false);
    }

    @Override
    public boolean canPlaceLiquid(Player player,  BlockGetter blockGetter,  BlockPos blockPos,  BlockState blockState,  Fluid fluid) {
        return true;
    }

    
    @Override
    public FluidState getFluidState(BlockState p_204507_1_) {
        return p_204507_1_.getValue(BlockStateProperties.WATERLOGGED)? Fluids.WATER.getSource(false) : super.getFluidState(p_204507_1_);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockStateBuilder) {
        blockStateBuilder.add(BlockStateProperties.AXIS, BlockStateProperties.WATERLOGGED);
    }

    @Override
    public boolean isPathfindable( BlockState pState,  BlockGetter pLevel,  BlockPos pPos,  PathComputationType pType) {
        return false;
    }

    @Override
    public void addLang(BiConsumer<String, String> consumer) {
        String base = getDescriptionId();
        consumer.accept(base, "Illuminant Rod");
        consumer.accept(base + ".info", "A simple rod of light.");
        consumer.accept(base + ".info2", "Can be placed in any direction.");
    }

    
    @Override
    public BlockState rotate( BlockState pState,  Rotation pRotation) {
        if (pRotation != Rotation.NONE) {
            Direction.Axis axis = pState.getValue(BlockStateProperties.AXIS);
            if ((pRotation == Rotation.CLOCKWISE_90 || pRotation == Rotation.COUNTERCLOCKWISE_90) && axis.isHorizontal())
                return pState.setValue(BlockStateProperties.AXIS, axis == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X);
        }
        return pState;
    }
}
