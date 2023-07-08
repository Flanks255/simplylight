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
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.BiConsumer;

public class LampBlock extends LampBase {
    public final boolean Default;
    public static final BooleanProperty ON = BooleanProperty.create("on");
    public final DyeColor color;

    public LampBlock(boolean Default, DyeColor colorIn) {
        super(Block.Properties.of()
                .strength(1.0f)
                .pushReaction(PushReaction.NORMAL)
                .mapColor(state -> state.getValue(ON) ? MapColor.COLOR_LIGHT_GRAY : MapColor.COLOR_BLACK)
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
        String base = getDescriptionId();

        if (color == DyeColor.WHITE)
            consumer.accept(base, "Illuminant Block" + (Default?"(Inverted)":""));
        else {
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
            consumer.accept(base, "Illuminant " + colorname + " Block" + (Default ? " (Inverted)" : ""));
        }
        consumer.accept(base + ".info", "Simple light block,");
        if (Default)
            consumer.accept(base + ".info2", "Deactivates by %s.");
        else
            consumer.accept(base + ".info2", "Activates by %s.");
    }
}
