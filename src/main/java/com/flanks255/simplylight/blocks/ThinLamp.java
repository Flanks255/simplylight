package com.flanks255.simplylight.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;

import javax.annotation.Nullable;
import java.util.function.BiConsumer;

public class ThinLamp extends RotatableLamp {

    private final double thickness;
    public final DyeColor color;

    public ThinLamp(double thickness, DyeColor colorIn) {
        super(Block.Properties.of()
            .pushReaction(PushReaction.NORMAL)
            .lightLevel($ -> 15)
            .strength(1.0f));

        this.thickness = thickness;
        this.color = colorIn;
        UP = Block.box(0,0,0, 16, thickness,16);
        DOWN = Block.box(0,16 - thickness,0, 16,16,16);
        EAST = Block.box(0,0,0, thickness,16,16);
        WEST = Block.box(16 - thickness,0,0, 16,16,16);
        NORTH = Block.box(0,0,16 - thickness, 16,16,16);
        SOUTH = Block.box(0,0,0, 16,16, thickness);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext useContext) {
        BlockPos pos = useContext.getClickedPos().relative(useContext.getClickedFace().getOpposite());
        boolean waterlogged = useContext.getLevel().getFluidState(useContext.getClickedPos()).getType() == Fluids.WATER;
        if (useContext.getPlayer() != null && useContext.getLevel().getBlockState(pos).getBlock() instanceof ThinLamp && !useContext.getPlayer().isCrouching())
            return defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, waterlogged).setValue(BlockStateProperties.FACING, useContext.getLevel().getBlockState(pos).getValue(BlockStateProperties.FACING));
        else
            return defaultBlockState().setValue(BlockStateProperties.FACING, useContext.getClickedFace()).setValue(BlockStateProperties.WATERLOGGED, waterlogged);
    }

    @Override
    public void addLang(BiConsumer<String, String> consumer) {
        String base = getDescriptionId();

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

        String type = thickness == 8 ? "Slab" : "Panel";
        String type2 = thickness == 8 ? "half-slab" : "LED panel";

        if (color == DyeColor.WHITE)
            consumer.accept(base, "Illuminant " + type);
        else
            consumer.accept(base, "Illuminant " + colorname + " " + type);

        if (color != DyeColor.WHITE)
            return;
        consumer.accept(base + ".info", "Simple " + type2 + " light,");
        consumer.accept(base + ".info2", "Place in any direction.");
    }
}
