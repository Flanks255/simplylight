package com.flanks255.simplylight.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.*;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.BiConsumer;

@SuppressWarnings("deprecation")
public class RodLamp extends LampBase implements SimpleWaterloggedBlock {
    public RodLamp () {
        super(Block.Properties.of(new Material(
            MaterialColor.TERRACOTTA_WHITE,
            false, //isLiquid
            false,  //isSolid
            true, //Blocks Movement
            false, //isOpaque
            false, //isFlammable
            false, //isReplaceable
            PushReaction.NORMAL
        )).strength(1.0f).lightLevel($ -> 15));

        registerDefaultState(getStateDefinition().any().setValue(BlockStateProperties.WATERLOGGED, false));
    }

    private final VoxelShape UpDown = Block.box(7,0,7, 9,16,9);
    private final VoxelShape EastWest = Block.box(0,7,7, 16,9,9);
    private final VoxelShape NorthSouth = Block.box(7,7,0, 9,9,16);

    @Nonnull
    @Override
    public VoxelShape getShape(BlockState pState, @Nonnull BlockGetter pLevel, @Nonnull BlockPos pPos, @Nonnull CollisionContext pContext) {
        return switch (pState.getValue(BlockStateProperties.AXIS)) {
            case X -> EastWest;
            case Y -> UpDown;
            default -> NorthSouth;
        };
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext placeContext) {
        return defaultBlockState().setValue(BlockStateProperties.AXIS, placeContext.getClickedFace().getAxis()).setValue(BlockStateProperties.WATERLOGGED, false);
    }

    @Override
    public boolean canPlaceLiquid(@Nonnull BlockGetter pLevel, @Nonnull BlockPos pPos, @Nonnull BlockState pState, @Nonnull Fluid pFluid) {
        return true;
    }

    @Nonnull
    @Override
    public FluidState getFluidState(BlockState p_204507_1_) {
        return p_204507_1_.getValue(BlockStateProperties.WATERLOGGED)? Fluids.WATER.getSource(false) : super.getFluidState(p_204507_1_);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockStateBuilder) {
        blockStateBuilder.add(BlockStateProperties.AXIS, BlockStateProperties.WATERLOGGED);
    }

    @Override
    public boolean isPathfindable(@Nonnull BlockState pState, @Nonnull BlockGetter pLevel, @Nonnull BlockPos pPos, @Nonnull PathComputationType pType) {
        return false;
    }

    @Override
    public void addLang(BiConsumer<String, String> consumer) {
        String base = getDescriptionId();
        consumer.accept(base, "Illuminant Rod");
        consumer.accept(base + ".info", "A simple rod of light.");
        consumer.accept(base + ".info2", "Can be placed in any direction.");
    }
}
