package com.flanks255.simplylight.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import javax.annotation.Nonnull;
import java.util.function.BiConsumer;

public class LightBulb extends RotatableLamp {
    public LightBulb() {
        super(Properties.of(Material.DECORATION)
            .noCollission()
            .strength(1.0f)
            .lightLevel((bState) -> 14));
        UP = Block.box(6, 0, 6, 10, 5, 10);
        DOWN = Block.box(6, 11, 6, 10, 16, 10);
        EAST = Block.box(0, 6, 6, 5, 10, 10);
        WEST = Block.box(11, 6, 6, 16, 10, 10);
        NORTH = Block.box(6, 6, 11, 10, 10, 16);
        SOUTH = Block.box(6, 6, 0, 10, 10, 5);
    }

    @Override
    public int getLightBlock(@Nonnull BlockState state, BlockGetter world, BlockPos pos) {
        return 14;
    }

    @Override
    public void addLang(BiConsumer<String, String> consumer) {
        String base = getDescriptionId();

        consumer.accept(base, "Simple Light Bulb");
        consumer.accept(base + ".info", "Just a simple light bulb,");
        consumer.accept(base + ".info2", "place in any direction.");
    }
}
