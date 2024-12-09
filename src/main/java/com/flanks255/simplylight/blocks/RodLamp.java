package com.flanks255.simplylight.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
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
    public RodLamp (DyeColor color) {
        super(Block.Properties.of()
            .pushReaction(PushReaction.NORMAL)
            .mapColor(MapColor.TERRACOTTA_WHITE)
            .strength(1.0f)
            .lightLevel($ -> 15));

        this.color = color;

        registerDefaultState(getStateDefinition().any().setValue(BlockStateProperties.WATERLOGGED, false));
    }

    public final DyeColor color;

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
    public boolean canPlaceLiquid(Player player, @Nonnull BlockGetter pLevel, @Nonnull BlockPos pPos, @Nonnull BlockState pState, @Nonnull Fluid pFluid) {
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
    public boolean isPathfindable(@Nonnull BlockState pState, @Nonnull PathComputationType pType) {
        return false;
    }

    @Nonnull
    @Override
    public BlockState rotate(@Nonnull BlockState pState, @Nonnull Rotation pRotation) {
        if (pRotation != Rotation.NONE) {
            Direction.Axis axis = pState.getValue(BlockStateProperties.AXIS);
            if ((pRotation == Rotation.CLOCKWISE_90 || pRotation == Rotation.COUNTERCLOCKWISE_90) && axis.isHorizontal())
                return pState.setValue(BlockStateProperties.AXIS, axis == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X);
        }
        return pState;
    }

    @Override
    public void addLang(BiConsumer<String, String> consumer) {
        String base = getDescriptionId();

        String colorname = switch (color) {
            case RED -> "Red";
            case BLUE -> "Blue";
            case CYAN -> "Cyan";
            case GRAY -> "Gray";
            case LIME -> "Lime";
            case MAGENTA -> "Magenta";
            case PINK -> "Pink";
            case BLACK -> "Black";
            case BROWN -> "Brown";
            case GREEN -> "Green";
            case ORANGE -> "Orange";
            case PURPLE -> "Purple";
            case YELLOW -> "Yellow";
            case LIGHT_BLUE -> "Light Blue";
            case LIGHT_GRAY -> "Light Gray";
            default -> "";
        };

        if (color == DyeColor.WHITE)
            consumer.accept(base, "Illuminant Rod");
        else
            consumer.accept(base, "Illuminant " + colorname + " Rod");
        consumer.accept(base + ".info", "A simple rod of light.");
        consumer.accept(base + ".info2", "Can be placed in any direction.");
    }
}
