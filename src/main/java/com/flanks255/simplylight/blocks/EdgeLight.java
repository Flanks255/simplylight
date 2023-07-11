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
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.BiConsumer;

public class EdgeLight extends LampBase implements SimpleWaterloggedBlock {

    private final Boolean top;

    public EdgeLight(Boolean top) {
        super(Properties.of()
            .strength(1.0f)
            .noCollission()
            .lightLevel($ -> 14)
        );

        registerDefaultState(getStateDefinition().any().setValue(BlockStateProperties.WATERLOGGED, false));

        this.top = top;
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
    public VoxelShape getShape(BlockState pState, @Nonnull BlockGetter pLevel, @Nonnull BlockPos pPos, @Nonnull CollisionContext pContext) {
        VoxelShape shape = Shapes.empty();
        if (pState.getValue(NORTH))
            shape = Shapes.or(shape, VS_NORTH);
        if (pState.getValue(SOUTH))
            shape = Shapes.or(shape, VS_SOUTH);
        if (pState.getValue(EAST))
            shape = Shapes.or(shape, VS_EAST);
        if (pState.getValue(WEST))
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
    public boolean canPlaceLiquid(@Nonnull BlockGetter pLevel, @Nonnull BlockPos pPos, @Nonnull BlockState pState, @Nonnull Fluid pFluid) {
        return true;
    }

    @Nonnull
    @Override
    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(BlockStateProperties.WATERLOGGED)? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(NORTH, SOUTH, EAST, WEST, BlockStateProperties.WATERLOGGED);
    }

    @Override
    public void addLang(BiConsumer<String, String> consumer) {
        String base = getDescriptionId();

        if (top)
            consumer.accept(base, "Dynamic Edge Light (top)");
        else
            consumer.accept(base, "Dynamic Edge Light (bottom)");

        consumer.accept(base + ".info", "Follows walls around itself,");
        consumer.accept(base + ".info2", "perfect for hallways.");

        consumer.accept(base + ".jei.info", "Will morph depending on the blocks present around itself on placement.\nShape will persist afterward, letting you make shapes using temporary blocks.");
    }
}
