package com.flanks255.simplylight.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EdgeLight extends LampBase implements SimpleWaterloggedBlock {
    public EdgeLight(Boolean top) {
        super(Block.Properties.of(Material.DECORATION)
                .harvestLevel(0)
                .harvestTool(ToolType.PICKAXE)
                .strength(1.0f)
                .noCollission()
                .lightLevel((bState) -> 14)
        );

        registerDefaultState(getStateDefinition().any().setValue(BlockStateProperties.WATERLOGGED, false));

        if (top) {
            VS_WEST = Shapes.box(0.0, 0.9375, 0.0, 0.0625, 1.0, 1.0);
            VS_EAST = Shapes.box(0.9375, 0.9375, 0.0, 1.0, 1.0, 1.0);
            VS_SOUTH = Shapes.box(0.0, 0.9375, 0.9375, 1.0, 1.0, 1.0);
            VS_NORTH = Shapes.box(0.0, 0.9375, 0.0, 1.0, 1.0, 0.0625);
            VS_ALL = Shapes.or(VS_WEST, VS_EAST, VS_NORTH, VS_SOUTH);
        }
    }

    public VoxelShape VS_WEST = Shapes.box(0.0, 0.0, 0.0, 0.0625, 0.0625, 1.0);
    public VoxelShape VS_EAST = Shapes.box(1.0-0.0625, 0.0, 0.0, 1.0, 0.0625, 1.0);
    public VoxelShape VS_SOUTH = Shapes.box(0.0, 0.0, 1.0 - 0.0625, 1.0, 0.0625, 1.0);
    public VoxelShape VS_NORTH = Shapes.box(0.0, 0.0, 0.0, 1.0, 0.0625, 0.0625);
    public VoxelShape VS_ALL = Shapes.or(VS_WEST, VS_EAST, VS_NORTH, VS_SOUTH);

    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty WEST = BooleanProperty.create("west");

    @Nonnull
    @Override
    public VoxelShape getShape(BlockState state, @Nonnull BlockGetter p_220053_2_, @Nonnull BlockPos p_220053_3_, @Nonnull CollisionContext p_220053_4_) {
        VoxelShape shape = Shapes.empty();
        if (state.getValue(NORTH))
            shape = Shapes.or(shape, VS_NORTH);
        if (state.getValue(SOUTH))
            shape = Shapes.or(shape, VS_SOUTH);
        if (state.getValue(EAST))
            shape = Shapes.or(shape, VS_EAST);
        if (state.getValue(WEST))
            shape = Shapes.or(shape, VS_WEST);

        if (shape.isEmpty())
            return VS_ALL;

        return shape;
    }

    public boolean checkSide(BlockPlaceContext context, Direction direction) {
        BlockPos pos = context.getClickedPos().relative(direction);

        //return hasSolidSide(context.getWorld().getBlockState(pos), context.getWorld(), pos, direction.getOpposite());
        return canSupportCenter(context.getLevel(),pos,direction.getOpposite());
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean waterlogged = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        return defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, waterlogged)
                .setValue(NORTH, checkSide(context, Direction.NORTH))
                .setValue(SOUTH, checkSide(context, Direction.SOUTH))
                .setValue(EAST, checkSide(context, Direction.EAST))
                .setValue(WEST, checkSide(context, Direction.WEST));
    }

    @Override
    public boolean canPlaceLiquid(@Nonnull BlockGetter p_204510_1_, @Nonnull BlockPos p_204510_2_, @Nonnull BlockState p_204510_3_, @Nonnull Fluid p_204510_4_) {
        return true;
    }

    @Nonnull
    @Override
    public FluidState getFluidState(BlockState p_204507_1_) {
        return p_204507_1_.getValue(BlockStateProperties.WATERLOGGED)? Fluids.WATER.getSource(false) : super.getFluidState(p_204507_1_);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(NORTH, SOUTH, EAST, WEST, BlockStateProperties.WATERLOGGED);
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter world, BlockPos pos) {
        return 14;
    }
}
