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
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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
        )).strength(1.0f).harvestLevel(0).harvestTool(ToolType.PICKAXE).lightLevel((bState) -> 15));

        registerDefaultState(getStateDefinition().any().setValue(BlockStateProperties.WATERLOGGED, false));
    }

    private final VoxelShape UpDown = Block.box(7,0,7, 9,16,9);
    private final VoxelShape EastWest = Block.box(0,7,7, 16,9,9);
    private final VoxelShape NorthSouth = Block.box(7,7,0, 9,9,16);

    @Nonnull
    @Override
    public VoxelShape getShape(BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
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
    public BlockState getStateForPlacement(BlockPlaceContext p_196258_1_) {
        return defaultBlockState().setValue(BlockStateProperties.AXIS, p_196258_1_.getClickedFace().getAxis()).setValue(BlockStateProperties.WATERLOGGED, false);
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
        p_206840_1_.add(BlockStateProperties.AXIS, BlockStateProperties.WATERLOGGED);
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter world, BlockPos pos) {
        return 15;
    }

}
