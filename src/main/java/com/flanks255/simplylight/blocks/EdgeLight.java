package com.flanks255.simplylight.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EdgeLight extends LampBase implements IWaterLoggable {
    public EdgeLight(Boolean top) {
        super(Block.Properties.create(Material.MISCELLANEOUS)
                .harvestLevel(0)
                .harvestTool(ToolType.PICKAXE)
                .hardnessAndResistance(1.0f)
                .doesNotBlockMovement()
                .setLightLevel((bState) -> 14)
        );

        setDefaultState(getStateContainer().getBaseState().with(BlockStateProperties.WATERLOGGED, false));

        if (top) {
            VS_WEST = VoxelShapes.create(0.0, 0.9375, 0.0, 0.0625, 1.0, 1.0);
            VS_EAST = VoxelShapes.create(1.0, 0.9375, 0.0, 0.9375, 1.0, 1.0);
            VS_SOUTH = VoxelShapes.create(0.0, 0.9375, 0.9375, 1.0, 1.0, 1.0);
            VS_NORTH = VoxelShapes.create(0.0, 0.9375, 0.0, 1.0, 1.0, 0.0625);
            VS_ALL = VoxelShapes.or(VS_WEST, VS_EAST, VS_NORTH, VS_SOUTH);
        }
    }

    public VoxelShape VS_WEST = VoxelShapes.create(0.0, 0.0, 0.0, 0.0625, 0.0625, 1.0);
    public VoxelShape VS_EAST = VoxelShapes.create(1.0, 0.0, 0.0, 1.0-0.0625, 0.0625, 1.0);
    public VoxelShape VS_SOUTH = VoxelShapes.create(0.0, 0.0, 1.0 - 0.0625, 1.0, 0.0625, 1.0);
    public VoxelShape VS_NORTH = VoxelShapes.create(0.0, 0.0, 0.0, 1.0, 0.0625, 0.0625);
    public VoxelShape VS_ALL = VoxelShapes.or(VS_WEST, VS_EAST, VS_NORTH, VS_SOUTH);

    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty WEST = BooleanProperty.create("west");

    @Nonnull
    @Override
    public VoxelShape getShape(BlockState state, @Nonnull IBlockReader p_220053_2_, @Nonnull BlockPos p_220053_3_, @Nonnull ISelectionContext p_220053_4_) {
        VoxelShape shape = VoxelShapes.empty();
        if (state.get(NORTH))
            shape = VoxelShapes.or(shape, VS_NORTH);
        if (state.get(SOUTH))
            shape = VoxelShapes.or(shape, VS_SOUTH);
        if (state.get(EAST))
            shape = VoxelShapes.or(shape, VS_EAST);
        if (state.get(WEST))
            shape = VoxelShapes.or(shape, VS_WEST);

        if (shape.isEmpty())
            return VS_ALL;

        return shape;
    }

    public boolean checkSide(BlockItemUseContext context, Direction direction) {
        BlockPos pos = context.getPos().offset(direction);

        //return hasSolidSide(context.getWorld().getBlockState(pos), context.getWorld(), pos, direction.getOpposite());
        return hasEnoughSolidSide(context.getWorld(),pos,direction.getOpposite());
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        boolean waterlogged = context.getWorld().getFluidState(context.getPos()).getFluid() == Fluids.WATER;
        return getDefaultState().with(BlockStateProperties.WATERLOGGED, waterlogged)
                .with(NORTH, checkSide(context, Direction.NORTH))
                .with(SOUTH, checkSide(context, Direction.SOUTH))
                .with(EAST, checkSide(context, Direction.EAST))
                .with(WEST, checkSide(context, Direction.WEST));
    }

    @Override
    public boolean canContainFluid(@Nonnull IBlockReader p_204510_1_, @Nonnull BlockPos p_204510_2_, @Nonnull BlockState p_204510_3_, @Nonnull Fluid p_204510_4_) {
        return true;
    }

    @Nonnull
    @Override
    public FluidState getFluidState(BlockState p_204507_1_) {
        return p_204507_1_.get(BlockStateProperties.WATERLOGGED)? Fluids.WATER.getStillFluidState(false) : super.getFluidState(p_204507_1_);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(NORTH, SOUTH, EAST, WEST, BlockStateProperties.WATERLOGGED);
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 14;
    }
}
