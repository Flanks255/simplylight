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

import javax.annotation.Nullable;

public class LampBlock extends LampBase {
    public LampBlock(boolean Default) {
        super(Block.Properties.create(new Material(
                MaterialColor.WHITE_TERRACOTTA,
                false,
                true,
                true,
                true,
                false,
                false,
                PushReaction.NORMAL
        )).hardnessAndResistance(1.0f)
                .harvestLevel(0)
                .harvestTool(ToolType.PICKAXE)
                .setLightLevel((bState)-> bState.get(ON) ? 15 : 0));
        this.Default = Default;
    }

    private boolean Default;
    public static final BooleanProperty ON = BooleanProperty.create("on");

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(ON);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(ON, Default);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        boolean powered = worldIn.isBlockPowered(pos);
        if (powered) {
            worldIn.setBlockState(pos,this.getDefaultState().with(ON, powered != Default));
        }
    }

    @Override
    public void neighborChanged(BlockState p_220069_1_, World worldIn, BlockPos p_220069_3_, Block p_220069_4_, BlockPos p_220069_5_, boolean p_220069_6_) {
        super.neighborChanged(p_220069_1_, worldIn, p_220069_3_, p_220069_4_, p_220069_5_, p_220069_6_);
        boolean on = Default != worldIn.isBlockPowered(p_220069_3_);
        worldIn.setBlockState(p_220069_3_, getDefaultState().with(ON, on));
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return state.get(ON)?15:0;
    }

    @Override
    public boolean canConnectRedstone(BlockState state, IBlockReader world, BlockPos pos, @Nullable Direction side) {
        return true;
    }
}
