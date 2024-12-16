package com.flanks255.simplylight.blocks;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;

import java.util.function.BiConsumer;

public class Fixture extends RotatableLamp{
    public Fixture(DyeColor color) {
        super(Block.Properties.of().noCollission().strength(1.0f).lightLevel($ -> 15));

        UP = Shapes.box(0.375,0.0, 0.375,0.625, 0.125,0.625);
        DOWN = Shapes.box(0.375, 1.0 - 0.125, 0.375, 0.625, 1.0, 0.625);
        EAST = Shapes.box(0.0, 0.375, 0.25, 0.1875, 0.625, 0.75);
        WEST = Shapes.box(1.0 - 0.1875, 0.375, 0.25, 1.0, 0.625, 0.75);
        NORTH = Shapes.box(0.25, 0.375, 1.0 - 0.1875, 0.75, 0.625, 1.0);
        SOUTH = Shapes.box(0.25, 0.375, 0.0, 0.75, 0.625, 0.1875);

        this.color = color;
    }
    public final DyeColor color;
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

        if (color == DyeColor.WHITE)
            consumer.accept(base, "Illuminant Fixture");
        else
            consumer.accept(base, "Illuminant " + colorname + " Fixture");

        if (color != DyeColor.WHITE)
            return;
        consumer.accept(base + ".info", "Hangs from walls, or sticks to ceilings and floors.");
    }
}
