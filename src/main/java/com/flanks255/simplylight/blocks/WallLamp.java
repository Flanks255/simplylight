package com.flanks255.simplylight.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.Shapes;

import java.util.function.BiConsumer;

public class WallLamp extends RotatableLamp{
    public WallLamp() {
        super(Block.Properties.of(Material.DECORATION).noCollission().strength(1.0f).lightLevel($ -> 15));

        UP = Shapes.box(0.375,0.0, 0.375,0.625, 0.125,0.625);
        DOWN = Shapes.box(0.375, 1.0 - 0.125, 0.375, 0.625, 1.0, 0.625);
        EAST = Shapes.box(0.0, 0.375, 0.25, 0.1875, 0.625, 0.75);
        WEST = Shapes.box(1.0 - 0.1875, 0.375, 0.25, 1.0, 0.625, 0.75);
        NORTH = Shapes.box(0.25, 0.375, 1.0 - 0.1875, 0.75, 0.625, 1.0);
        SOUTH = Shapes.box(0.25, 0.375, 0.0, 0.75, 0.625, 0.1875);
    }

    @Override
    public void addLang(BiConsumer<String, String> consumer) {
        String base = getDescriptionId();

        consumer.accept(base, "Illuminant Fixture");
        consumer.accept(base + ".info", "Hangs from walls, or sticks to ceilings and floors.");
    }
}
