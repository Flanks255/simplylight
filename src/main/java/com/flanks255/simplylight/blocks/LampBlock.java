package com.flanks255.simplylight.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class LampBlock extends LampBase {
    public LampBlock(boolean Default) {
        super(Block.Properties.of(new Material(
                MaterialColor.TERRACOTTA_WHITE,
                false,
                true,
                true,
                true,
                false,
                false,
                PushReaction.NORMAL
        )).strength(1.0f)
                .harvestLevel(0)
                .harvestTool(ToolType.PICKAXE)
                .lightLevel((bState)-> bState.getValue(ON) ? 15 : 0));
        this.Default = Default;

        registerDefaultState(getStateDefinition().any().setValue(ON, Default));
    }

    private final boolean Default;
    public static final BooleanProperty ON = BooleanProperty.create("on");

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(ON);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(@Nonnull BlockItemUseContext context) {
        return this.defaultBlockState().setValue(ON, Default);
    }

    @Override
    public void setPlacedBy(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nullable LivingEntity placer, @Nonnull ItemStack stack) {
        super.setPlacedBy(worldIn, pos, state, placer, stack);
        boolean powered = worldIn.hasNeighborSignal(pos);
        if (powered) {
            worldIn.setBlockAndUpdate(pos,this.defaultBlockState().setValue(ON, !Default));
        }
    }

    @Override
    public void neighborChanged(@Nonnull BlockState p_220069_1_, @Nonnull World worldIn, @Nonnull BlockPos p_220069_3_, @Nonnull Block p_220069_4_, @Nonnull BlockPos p_220069_5_, boolean p_220069_6_) {
        super.neighborChanged(p_220069_1_, worldIn, p_220069_3_, p_220069_4_, p_220069_5_, p_220069_6_);
        boolean on = Default != worldIn.hasNeighborSignal(p_220069_3_);
        worldIn.setBlockAndUpdate(p_220069_3_, defaultBlockState().setValue(ON, on));
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return state.getValue(ON)?15:0;
    }

    @Override
    public boolean canConnectRedstone(BlockState state, IBlockReader world, BlockPos pos, @Nullable Direction side) {
        return true;
    }
}
