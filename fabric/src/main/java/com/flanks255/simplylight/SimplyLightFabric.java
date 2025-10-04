package com.flanks255.simplylight;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import oshi.util.tuples.Pair;

import java.util.ArrayList;
import java.util.List;

public class SimplyLightFabric implements ModInitializer {
    public static final List<Pair<String, Block>> BLOCKS = new ArrayList<>();
    public static final List<Pair<String, Item>> ITEMS = new ArrayList<>();

    public SimplyLightFabric() {
        SLBlocks.load();
    }

    @Override
    public void onInitialize() {
        SimplyLightCommon.init();
    }

    public static void registerBlocksAndItems() {
        BLOCKS.forEach(reg -> {
            Registry.register(BuiltInRegistries.BLOCK, reg.getA(), reg.getB());
        });
        ITEMS.forEach(reg -> {
            Registry.register(BuiltInRegistries.ITEM, reg.getA(), reg.getB());
        });
    }
}
