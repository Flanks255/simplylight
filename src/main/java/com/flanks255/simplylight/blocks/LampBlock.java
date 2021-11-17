package com.flanks255.simplylight.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeColor;
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
import java.util.function.BiConsumer;

public class LampBlock extends LampBase {
    public final boolean Default;
    public static final BooleanProperty ON = BooleanProperty.create("on");
    public final DyeColor color;

    public LampBlock(boolean Default, DyeColor colorIn) {
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
            .lightLevel((bState)-> bState.getValue(ON) ? 15 : 0));
        this.Default = Default;
        this.color = colorIn;

        registerDefaultState(getStateDefinition().any().setValue(ON, Default));
    }


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

    @Override
    public void addLang(BiConsumer<String, String> consumer) {
        String base = getLangBase();

        if (color == DyeColor.WHITE)
            consumer.accept(base, "Illuminant Block" + (Default?"(Inverted)":""));
        else {
            String colorname = "";
            switch (color) {
                case RED:
                    colorname = "Red";
                    break;
                case BLUE:
                    colorname = "Blue";
                    break;
                case CYAN:
                    colorname = "Cyan";
                    break;
                case GRAY:
                    colorname = "Gray";
                    break;
                case LIME:
                    colorname = "Lime";
                    break;
                case MAGENTA:
                    colorname = "Magenta";
                    break;
                case PINK:
                    colorname = "Pink";
                    break;
                case BLACK:
                    colorname = "Black";
                    break;
                case BROWN:
                    colorname = "Brown";
                    break;
                case GREEN:
                    colorname = "Green";
                    break;
                case ORANGE:
                    colorname = "Orange";
                    break;
                case PURPLE:
                    colorname = "Purple";
                    break;
                case YELLOW:
                    colorname = "Yellow";
                    break;
                case LIGHT_BLUE:
                    colorname = "Light Blue";
                    break;
                case LIGHT_GRAY:
                    colorname = "Light Gray";
                    break;
            }

            consumer.accept(base, "Illuminant " + colorname + " Block" + (Default ? " (Inverted)" : ""));
        }
        consumer.accept(base + ".info", "Simple light block,");
        if (Default)
            consumer.accept(base + ".info2", "Deactivates by %s.");
        else
            consumer.accept(base + ".info2", "Activates by %s.");
    }
}
