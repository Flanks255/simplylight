package com.flanks255.simplylight.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.material.PushReaction;

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
        ))
                .strength(1.0f)
                .lightLevel((bState)-> bState.getValue(ON) ? 15 : 0));
        this.Default = Default;

        registerDefaultState(getStateDefinition().any().setValue(ON, Default));
    }

    private final boolean Default;
    public static final BooleanProperty ON = BooleanProperty.create("on");

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(ON);
    }


    @Nullable
    @Override
    public BlockState getStateForPlacement(@Nonnull BlockPlaceContext context) {
        return this.defaultBlockState().setValue(ON, Default);
    }

    @Override
    public void setPlacedBy(@Nonnull Level worldIn, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nullable LivingEntity placer, @Nonnull ItemStack stack) {
        super.setPlacedBy(worldIn, pos, state, placer, stack);
        boolean powered = worldIn.hasNeighborSignal(pos);
        if (powered) {
            worldIn.setBlockAndUpdate(pos,this.defaultBlockState().setValue(ON, !Default));
        }
    }

    @Override
    public void neighborChanged(@Nonnull BlockState p_220069_1_, @Nonnull Level worldIn, @Nonnull BlockPos p_220069_3_, @Nonnull Block p_220069_4_, @Nonnull BlockPos p_220069_5_, boolean p_220069_6_) {
        super.neighborChanged(p_220069_1_, worldIn, p_220069_3_, p_220069_4_, p_220069_5_, p_220069_6_);
        boolean on = Default != worldIn.hasNeighborSignal(p_220069_3_);
        worldIn.setBlockAndUpdate(p_220069_3_, defaultBlockState().setValue(ON, on));
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter world, BlockPos pos) {
        return state.getValue(ON)?15:0;
    }

    @Override
    public boolean isSignalSource(BlockState p_60571_) {
        return true;
    }
}
