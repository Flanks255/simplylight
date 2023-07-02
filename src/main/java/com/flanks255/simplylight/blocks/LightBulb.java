package com.flanks255.simplylight.blocks;

import net.minecraft.world.level.block.Block;

import java.util.function.BiConsumer;

public class LightBulb extends RotatableLamp {
    public LightBulb() {
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
    }
    @Override
    public void addLang(BiConsumer<String, String> consumer) {
        String base = getDescriptionId();

        consumer.accept(base, "Simple Light Bulb");
        consumer.accept(base + ".info", "Just a simple light bulb,");
        consumer.accept(base + ".info2", "place in any direction.");
    }
}
