package com.flanks255.simplylight.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

@SuppressWarnings("deprecation")
public class LampPost extends LampBase implements IWaterLoggable {
    public static final EnumProperty<Position> POSITION = EnumProperty.create("position", Position.class);

    private static final VoxelShape TOP_SHAPE = Stream.of(Block.box(6,0,6, 10, 5, 10), Block.box(5,5,5,11,6,11), Block.box(3,6,3,13,16,13)).reduce((a,b) -> VoxelShapes.join(a,b, IBooleanFunction.OR)).get();
    private static final VoxelShape MIDDLE_SHAPE = Stream.of(Block.box(6,0,6,10,10,10), Block.box(5,10,5,11,16,11)).reduce((a,b) -> VoxelShapes.join(a,b, IBooleanFunction.OR)).get();
    private static final VoxelShape BOTTOM_SHAPE = Stream.of(Block.box(3,0,3,13,2,13), Block.box(4,2,4,12,4,12), Block.box(6,4,6,10,16,10)).reduce((a,b) -> VoxelShapes.join(a,b, IBooleanFunction.OR)).get();

    public LampPost() {
        super(AbstractBlock.Properties.of(new Material(MaterialColor.COLOR_BLACK,
            false, //isLiquid
            true,  //isSolid
            true, //Blocks Movement
            false, //isOpaque
            false, //isFlammable
            false, //isReplaceable
            PushReaction.DESTROY
        )).harvestTool(ToolType.PICKAXE).lightLevel( (bState) -> bState.getValue(POSITION) == Position.TOP?15:0).strength(2.0f).harvestLevel(0));

        registerDefaultState(getStateDefinition().any().setValue(BlockStateProperties.WATERLOGGED, false));
    }

    @Override
    public boolean isPathfindable(@Nonnull BlockState p_196266_1_, @Nonnull IBlockReader p_196266_2_, @Nonnull BlockPos p_196266_3_, @Nonnull PathType p_196266_4_) {
        return false;
    }

    @Nonnull
    @Override
    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(BlockStateProperties.WATERLOGGED)? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    @Nonnull
    @Override
    public VoxelShape getShape(BlockState pState, @Nonnull IBlockReader pLevel, @Nonnull BlockPos pPos, @Nonnull ISelectionContext pContext) {
        switch (pState.getValue(POSITION)){
            case TOP:
                return TOP_SHAPE;
            case MIDDLE:
                return MIDDLE_SHAPE;
            default:
                return BOTTOM_SHAPE;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(POSITION, BlockStateProperties.WATERLOGGED);
    }

    @Override
    public void setPlacedBy(World pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, @Nonnull ItemStack pStack) {
        pLevel.setBlockAndUpdate(pPos.above(), pState.setValue(POSITION, Position.MIDDLE));
        pLevel.setBlockAndUpdate(pPos.above(2), pState.setValue(POSITION, Position.TOP));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext pContext) {
        BlockPos clickedPos = pContext.getClickedPos();
        World level = pContext.getLevel();
        if (clickedPos.getY() < 254 && level.getBlockState(clickedPos.above()).canBeReplaced(pContext) && level.getBlockState(clickedPos.above(2)).canBeReplaced(pContext))
            return defaultBlockState().setValue(POSITION, Position.BOTTOM).setValue(BlockStateProperties.WATERLOGGED, false);
        else
            return null;
    }

    @Override
    public void onRemove(@Nonnull BlockState pState, @Nonnull World pLevel, @Nonnull BlockPos pPos, @Nonnull BlockState pNewState, boolean pIsMoving) {
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
        if (pNewState.getBlock() == this)
            return;
        switch (pState.getValue(POSITION)) {
            case TOP:
                if (pLevel.getBlockState(pPos.below()).getBlock() instanceof LampPost)
                    pLevel.setBlockAndUpdate(pPos.below(), Blocks.AIR.defaultBlockState());
                if (pLevel.getBlockState(pPos.below(2)).getBlock() instanceof LampPost)
                    pLevel.setBlockAndUpdate(pPos.below(2), Blocks.AIR.defaultBlockState());
                break;
            case MIDDLE:
                if (pLevel.getBlockState(pPos.below()).getBlock() instanceof LampPost)
                    pLevel.setBlockAndUpdate(pPos.below(), Blocks.AIR.defaultBlockState());
                if (pLevel.getBlockState(pPos.above()).getBlock() instanceof LampPost)
                    pLevel.setBlockAndUpdate(pPos.above(), Blocks.AIR.defaultBlockState());
                break;
            case BOTTOM:
                if (pLevel.getBlockState(pPos.above()).getBlock() instanceof LampPost)
                    pLevel.setBlockAndUpdate(pPos.above(), Blocks.AIR.defaultBlockState());
                if (pLevel.getBlockState(pPos.above(2)).getBlock() instanceof LampPost)
                    pLevel.setBlockAndUpdate(pPos.above(2), Blocks.AIR.defaultBlockState());
                break;
        }
    }

    @Override
    public void addLang(BiConsumer<String, String> consumer) {
        String base = getLangBase();
        consumer.accept(base, "Illuminant Column");
        consumer.accept(base + ".info", "3 Block tall lamp post.");
        consumer.accept(base + ".info2", "Top block emits light.");
    }

    public enum Position implements IStringSerializable {
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
