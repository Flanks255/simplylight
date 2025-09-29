package com.flanks255.simplylight.blocks;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;

import java.util.function.BiConsumer;

public class LightBulb extends RotatableLamp {
    public LightBulb(DyeColor color) {
        super(Block.Properties.of()
            .noCollission()
            .strength(1.0f)
            .lightLevel($ -> 14));
        UP = Block.box(6, 0, 6, 10, 5, 10);
        DOWN = Block.box(6, 11, 6, 10, 16, 10);
        EAST = Block.box(0, 6, 6, 5, 10, 10);
        WEST = Block.box(11, 6, 6, 16, 10, 10);
        NORTH = Block.box(6, 6, 11, 10, 10, 16);
        SOUTH = Block.box(6, 6, 0, 10, 10, 5);

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
            consumer.accept(base, "Simple Light Bulb");
        else
            consumer.accept(base, "Simple " + colorname + " Light Bulb");

        if (color != DyeColor.WHITE)
            return;
        consumer.accept(base + ".info", "Just a simple light bulb,");
        consumer.accept(base + ".info2", "place in any direction.");
    }
}
