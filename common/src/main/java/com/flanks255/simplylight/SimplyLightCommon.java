package com.flanks255.simplylight;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimplyLightCommon {
    public static final String MODID = "simplylight";
    public static final Logger LOGGER = LoggerFactory.getLogger("Simply Light");

    public static final TagKey<Item> ANY_ON_LAMP = TagKey.create(Registries.ITEM, SLRes("any_lamp_on"));
    public static final TagKey<Item> ANY_OFF_LAMP = TagKey.create(Registries.ITEM, SLRes("any_lamp_off"));
    public static final TagKey<Item> ANY_SLAB = TagKey.create(Registries.ITEM, SLRes("any_slab"));
    public static final TagKey<Item> ANY_PANEL = TagKey.create(Registries.ITEM, SLRes("any_panel"));
    public static final TagKey<Item> ANY_ROD = TagKey.create(Registries.ITEM, SLRes("any_rod"));
    public static final TagKey<Item> ANY_BULB = TagKey.create(Registries.ITEM, SLRes("any_bulb"));
    public static final TagKey<Item> ANY_FIXTURE = TagKey.create(Registries.ITEM, SLRes("any_fixture"));
    public static final TagKey<Item> ANY_POST = TagKey.create(Registries.ITEM, SLRes("any_post"));
    public static final TagKey<Item> ANY_EDGE_LIGHT = TagKey.create(Registries.ITEM, SLRes("any_edge_light"));
    public static final TagKey<Item> ANY_EDGE_LIGHT_TOP = TagKey.create(Registries.ITEM, SLRes("any_edge_light_top"));
    static final CreativeModeTab.DisplayItemsGenerator TAB_ITEMS = (params, output) -> {
        SLBlocks.LAMPBLOCKS_ON.forEach(block -> output.accept(block.getItem()));
        SLBlocks.LAMPBLOCKS_OFF.forEach(block -> output.accept(block.getItem()));
        SLBlocks.SLABS.forEach(block -> output.accept(block.getItem()));
        SLBlocks.PANELS.forEach(block -> output.accept(block.getItem()));
        SLBlocks.RODS.forEach(block -> output.accept(block.getItem()));
        SLBlocks.BULBS.forEach(block -> output.accept(block.getItem()));
        SLBlocks.FIXTURES.forEach(block -> output.accept(block.getItem()));
        SLBlocks.POSTS.forEach(block -> output.accept(block.getItem()));
        SLBlocks.EDGE_LIGHTS.forEach(block -> output.accept(block.getItem()));
        SLBlocks.EDGE_LIGHTS_TOP.forEach(block -> output.accept(block.getItem()));
    };

    public static void init() {

/*        LOGGER.info("Hello from Common init on {}! we are currently in a {} environment!", Services.PLATFORM.getPlatformName(), Services.PLATFORM.getEnvironmentName());
        LOGGER.info("The ID for diamonds is {}", BuiltInRegistries.ITEM.getKey(Items.DIAMOND));*/

    }

    public static ResourceLocation SLRes(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}