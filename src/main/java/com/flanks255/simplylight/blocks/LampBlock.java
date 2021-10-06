package com.flanks255.simplylight.blocks;

import net.minecraft.core.BlockPos;
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

@SuppressWarnings("deprecation")
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
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockStateBuilder) {
        blockStateBuilder.add(ON);
    }


    @Nullable
    @Override
    public BlockState getStateForPlacement(@Nonnull BlockPlaceContext context) {
        return this.defaultBlockState().setValue(ON, Default);
    }

    @Override
    public void setPlacedBy(@Nonnull Level pLevel, @Nonnull BlockPos pPos, @Nonnull BlockState pState, @Nullable LivingEntity pPlacer, @Nonnull ItemStack pStack) {
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
        boolean powered = pLevel.hasNeighborSignal(pPos);
        if (powered) {
            pLevel.setBlockAndUpdate(pPos,this.defaultBlockState().setValue(ON, !Default));
        }
    }

    @Override
    public void neighborChanged(@Nonnull BlockState pState, @Nonnull Level pLevel, @Nonnull BlockPos pPos, @Nonnull Block pBlock, @Nonnull BlockPos pFromPos, boolean pIsMoving) {
        super.neighborChanged(pState, pLevel, pPos, pBlock, pFromPos, pIsMoving);
        boolean on = Default != pLevel.hasNeighborSignal(pPos);
        pLevel.setBlockAndUpdate(pPos, defaultBlockState().setValue(ON, on));
    }

    @Override
    public int getLightBlock(BlockState pState, @Nonnull BlockGetter pLevel, @Nonnull BlockPos pPos) {
        return pState.getValue(ON)?15:0;
    }

    @Override
    public boolean isSignalSource(@Nonnull BlockState pState) {
        return true;
    }
}
