package com.flanks255.simplylight.blocks;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.PushReaction;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.BiConsumer;

public class RodLamp extends LampBase implements IWaterLoggable {
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
        )).strength(1.0f).harvestLevel(0).harvestTool(ToolType.PICKAXE).lightLevel((bState) -> 15));

        registerDefaultState(getStateDefinition().any().setValue(BlockStateProperties.WATERLOGGED, false));
    }

    private final VoxelShape UpDown = Block.box(7,0,7, 9,16,9);
    private final VoxelShape EastWest = Block.box(0,7,7, 16,9,9);
    private final VoxelShape NorthSouth = Block.box(7,7,0, 9,9,16);

    @Nonnull
    @Override
    public VoxelShape getShape(BlockState state, @Nonnull IBlockReader worldIn, @Nonnull BlockPos pos, @Nonnull ISelectionContext context) {
        VoxelShape ret;
        switch (state.getValue(BlockStateProperties.AXIS)) {
            case X:
                ret = EastWest;
                break;
            case Y:
                ret = UpDown;
                break;
            default:
                ret = NorthSouth;
                break;
        }
        return ret;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
        return defaultBlockState().setValue(BlockStateProperties.AXIS, p_196258_1_.getClickedFace().getAxis()).setValue(BlockStateProperties.WATERLOGGED, false);
    }

    @Override
    public boolean canPlaceLiquid(@Nonnull IBlockReader p_204510_1_, @Nonnull BlockPos p_204510_2_, @Nonnull BlockState p_204510_3_, @Nonnull Fluid p_204510_4_) {
        return true;
    }

    @Nonnull
    @Override
    public FluidState getFluidState(BlockState p_204507_1_) {
        return p_204507_1_.getValue(BlockStateProperties.WATERLOGGED)? Fluids.WATER.getSource(false) : super.getFluidState(p_204507_1_);
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(BlockStateProperties.AXIS, BlockStateProperties.WATERLOGGED);
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 15;
    }

    @Override
    public void addLang(BiConsumer<String, String> consumer) {
        String base = getLangBase();
        consumer.accept(base, "Illuminant Rod");
        consumer.accept(base + ".info", "A simple rod of light.");
        consumer.accept(base + ".info2", "Can be placed in any direction.");
    }
}
