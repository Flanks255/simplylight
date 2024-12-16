package com.flanks255.simplylight.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.*;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

@SuppressWarnings("deprecation")
public class LampPost extends LampBase implements SimpleWaterloggedBlock {
    public static final EnumProperty<Position> POSITION = EnumProperty.create("position", Position.class);

    private static final VoxelShape TOP_SHAPE = Stream.of(Block.box(6,0,6, 10, 5, 10), Block.box(5,5,5,11,6,11), Block.box(3,6,3,13,16,13)).reduce((a, b) -> Shapes.join(a,b, BooleanOp.OR)).get();
    private static final VoxelShape MIDDLE_SHAPE = Stream.of(Block.box(6,0,6,10,10,10), Block.box(5,10,5,11,16,11)).reduce((a,b) -> Shapes.join(a,b, BooleanOp.OR)).get();
    private static final VoxelShape BOTTOM_SHAPE = Stream.of(Block.box(3,0,3,13,2,13), Block.box(4,2,4,12,4,12), Block.box(6,4,6,10,16,10)).reduce((a,b) -> Shapes.join(a,b, BooleanOp.OR)).get();

    public final DyeColor color;

    public LampPost(DyeColor color) {
        super(Block.Properties.of()
            .mapColor(MapColor.COLOR_BLACK)
            .pushReaction(PushReaction.DESTROY)
            .lightLevel( (bState) -> bState.getValue(POSITION) == Position.TOP?15:0)
            .strength(1.0f));

        registerDefaultState(getStateDefinition().any().setValue(BlockStateProperties.WATERLOGGED, false));

        this.color = color;
    }

    @Override
    public boolean isPathfindable(@Nonnull BlockState pState, @Nonnull PathComputationType pType) {
        return false;
    }

    @Override
    public boolean canPlaceLiquid(Player player, @Nonnull BlockGetter pLevel, @Nonnull BlockPos pPos, @Nonnull BlockState pState, @Nonnull Fluid pFluid) {
        return SimpleWaterloggedBlock.super.canPlaceLiquid(player, pLevel, pPos, pState, pFluid);
    }

    @Nonnull
    @Override
    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(BlockStateProperties.WATERLOGGED)? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    @Nonnull
    @Override
    public VoxelShape getShape(@Nonnull BlockState pState, @Nonnull BlockGetter pLevel, @Nonnull BlockPos pPos, @Nonnull CollisionContext pContext) {
        return switch (pState.getValue(POSITION)) {
            case TOP -> TOP_SHAPE;
            case MIDDLE -> MIDDLE_SHAPE;
            default -> BOTTOM_SHAPE;
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockStateBuilder) {
        blockStateBuilder.add(POSITION, BlockStateProperties.WATERLOGGED);
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, @Nonnull ItemStack pStack) {
        pLevel.setBlock(pPos.above(), pState.setValue(POSITION, Position.MIDDLE), 3);
        pLevel.setBlock(pPos.above(2), pState.setValue(POSITION, Position.TOP), 3);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockPos clickedPos = pContext.getClickedPos();
        Level level = pContext.getLevel();
        if (clickedPos.getY() < level.getMaxBuildHeight() - 2  && level.getBlockState(clickedPos.above()).canBeReplaced(pContext) && level.getBlockState(clickedPos.above(2)).canBeReplaced(pContext))
            return defaultBlockState().setValue(POSITION, Position.BOTTOM).setValue(BlockStateProperties.WATERLOGGED, false);
        else
            return null;
    }

    @Override
    public void onRemove(@Nonnull BlockState pState, @Nonnull Level pLevel, @Nonnull BlockPos pPos, @Nonnull BlockState pNewState, boolean pIsMoving) {
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
        if (pNewState.getBlock() == this)
            return;
        switch (pState.getValue(POSITION)) {
            case TOP -> {
                if (pLevel.getBlockState(pPos.below()).getBlock() instanceof LampPost)
                    pLevel.setBlockAndUpdate(pPos.below(), Blocks.AIR.defaultBlockState());
                if (pLevel.getBlockState(pPos.below(2)).getBlock() instanceof LampPost)
                    pLevel.setBlockAndUpdate(pPos.below(2), Blocks.AIR.defaultBlockState());
            }
            case MIDDLE -> {
                if (pLevel.getBlockState(pPos.below()).getBlock() instanceof LampPost)
                    pLevel.setBlockAndUpdate(pPos.below(), Blocks.AIR.defaultBlockState());
                if (pLevel.getBlockState(pPos.above()).getBlock() instanceof LampPost)
                    pLevel.setBlockAndUpdate(pPos.above(), Blocks.AIR.defaultBlockState());
            }
            case BOTTOM -> {
                if (pLevel.getBlockState(pPos.above()).getBlock() instanceof LampPost)
                    pLevel.setBlockAndUpdate(pPos.above(), Blocks.AIR.defaultBlockState());
                if (pLevel.getBlockState(pPos.above(2)).getBlock() instanceof LampPost)
                    pLevel.setBlockAndUpdate(pPos.above(2), Blocks.AIR.defaultBlockState());
            }
        }
    }

    @Override
    public void addLang(BiConsumer<String, String> consumer) {
        String base = this.getDescriptionId();

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
            consumer.accept(base, "Illuminant Column");
        else
            consumer.accept(base, "Illuminant " + colorname + " Column");

        if (color != DyeColor.WHITE)
            return;
        consumer.accept(base + ".info", "3 Block tall lamp post.");
        consumer.accept(base + ".info2", "Top block emits light.");
    }

    public enum Position implements StringRepresentable {
        BOTTOM("bottom"),
        MIDDLE("middle"),
        TOP("top");

        Position(String name) {
            this.name = name;
        }

        private final String name;
        @Nonnull
        @Override
        public String getSerializedName() {
            return name;
        }
    }
}
