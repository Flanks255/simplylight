package com.flanks255.simplylight;

import com.flanks255.simplylight.blocks.*;
import com.google.common.collect.ImmutableSet;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Set;

public class SLBlocks {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SimplyLight.MODID);
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(SimplyLight.MODID);

    private static final Item.Properties ITEMPROPERTIES = new Item.Properties();

    public static SLBlockReg<LampBlock, BaseBlockItem> addLamp(DyeColor color, boolean state) {
        return new SLBlockReg<>("illuminant_" + color.getName() + "_block" + (state?"_on":""), () -> new LampBlock(state, color), b -> new BaseBlockItem(b, ITEMPROPERTIES));
    }

    public static void init(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS.register(bus);
    }

    public static final SLBlockReg<EdgeLight, BaseBlockItem> EDGELAMP_TOP = new SLBlockReg<>("edge_light_top", () -> new EdgeLight(true), b -> new BaseBlockItem(b, ITEMPROPERTIES));
    public static final SLBlockReg<EdgeLight, BaseBlockItem> EDGELAMP = new SLBlockReg<>("edge_light", () -> new EdgeLight(false), b -> new BaseBlockItem(b, ITEMPROPERTIES));
    public static final SLBlockReg<RodLamp, BaseBlockItem> RODLAMP = new SLBlockReg<>("rodlamp", RodLamp::new, b -> new BaseBlockItem(b, ITEMPROPERTIES));
    public static final SLBlockReg<LightBulb, BaseBlockItem> LIGHTBULB = new SLBlockReg<>("lightbulb", LightBulb::new, b -> new BaseBlockItem(b, ITEMPROPERTIES));
    public static final SLBlockReg<WallLamp, BaseBlockItem> WALL_LAMP = new SLBlockReg<>("wall_lamp", WallLamp::new, b -> new BaseBlockItem(b, ITEMPROPERTIES));
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANTPANEL = new SLBlockReg<>("illuminant_panel",() -> new ThinLamp(4), b -> new BaseBlockItem(b, ITEMPROPERTIES));
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANTSLAB = new SLBlockReg<>("illuminant_slab",() -> new ThinLamp(8), b -> new BaseBlockItem(b, ITEMPROPERTIES));

    public static final SLBlockReg<LampPost, LampPostItem> LAMP_POST = new SLBlockReg<>("lamp_post", LampPost::new, b -> new LampPostItem(b, ITEMPROPERTIES));


    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANTBLOCK_ON = new SLBlockReg<>("illuminant_block_on", () -> new LampBlock(true, DyeColor.WHITE), b -> new BaseBlockItem(b, ITEMPROPERTIES));
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANTBLOCK = new SLBlockReg<>("illuminant_block", () -> new LampBlock(false, DyeColor.WHITE), b -> new BaseBlockItem(b, ITEMPROPERTIES));

    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_ORANGE = addLamp(DyeColor.ORANGE, false);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_ORANGE_ON = addLamp(DyeColor.ORANGE, true);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_MAGENTA = addLamp(DyeColor.MAGENTA, false);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_MAGENTA_ON = addLamp(DyeColor.MAGENTA, true);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_LIGHT_BLUE = addLamp(DyeColor.LIGHT_BLUE, false);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_LIGHT_BLUE_ON = addLamp(DyeColor.LIGHT_BLUE, true);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_YELLOW = addLamp(DyeColor.YELLOW, false);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_YELLOW_ON = addLamp(DyeColor.YELLOW, true);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_LIME = addLamp(DyeColor.LIME, false);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_LIME_ON = addLamp(DyeColor.LIME, true);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_PINK = addLamp(DyeColor.PINK, false);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_PINK_ON = addLamp(DyeColor.PINK, true);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_GRAY = addLamp(DyeColor.GRAY, false);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_GRAY_ON = addLamp(DyeColor.GRAY, true);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_LIGHT_GRAY = addLamp(DyeColor.LIGHT_GRAY, false);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_LIGHT_GRAY_ON = addLamp(DyeColor.LIGHT_GRAY, true);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_CYAN = addLamp(DyeColor.CYAN, false);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_CYAN_ON = addLamp(DyeColor.CYAN, true);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_PURPLE = addLamp(DyeColor.PURPLE, false);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_PURPLE_ON = addLamp(DyeColor.PURPLE, true);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_BLUE = addLamp(DyeColor.BLUE, false);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_BLUE_ON = addLamp(DyeColor.BLUE, true);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_BROWN = addLamp(DyeColor.BROWN, false);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_BROWN_ON = addLamp(DyeColor.BROWN, true);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_GREEN = addLamp(DyeColor.GREEN, false);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_GREEN_ON = addLamp(DyeColor.GREEN, true);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_RED = addLamp(DyeColor.RED, false);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_RED_ON = addLamp(DyeColor.RED, true);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_BLACK = addLamp(DyeColor.BLACK, false);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_BLACK_ON = addLamp(DyeColor.BLACK, true);


    public static final Set<SLBlockReg<LampBlock, BaseBlockItem>> LAMPBLOCKS_OFF = ImmutableSet.of(
        ILLUMINANTBLOCK, ILLUMINANT_BLOCK_ORANGE, ILLUMINANT_BLOCK_MAGENTA, ILLUMINANT_BLOCK_LIGHT_BLUE,
        ILLUMINANT_BLOCK_YELLOW, ILLUMINANT_BLOCK_LIME, ILLUMINANT_BLOCK_PINK, ILLUMINANT_BLOCK_GRAY,
        ILLUMINANT_BLOCK_LIGHT_GRAY, ILLUMINANT_BLOCK_CYAN, ILLUMINANT_BLOCK_PURPLE, ILLUMINANT_BLOCK_BLUE,
        ILLUMINANT_BLOCK_BROWN, ILLUMINANT_BLOCK_GREEN, ILLUMINANT_BLOCK_RED, ILLUMINANT_BLOCK_BLACK
    );
    public static final Set<SLBlockReg<LampBlock, BaseBlockItem>> LAMPBLOCKS_ON = ImmutableSet.of(
        ILLUMINANTBLOCK_ON, ILLUMINANT_BLOCK_ORANGE_ON, ILLUMINANT_BLOCK_MAGENTA_ON, ILLUMINANT_BLOCK_LIGHT_BLUE_ON,
        ILLUMINANT_BLOCK_YELLOW_ON, ILLUMINANT_BLOCK_LIME_ON, ILLUMINANT_BLOCK_PINK_ON, ILLUMINANT_BLOCK_GRAY_ON,
        ILLUMINANT_BLOCK_LIGHT_GRAY_ON, ILLUMINANT_BLOCK_CYAN_ON, ILLUMINANT_BLOCK_PURPLE_ON, ILLUMINANT_BLOCK_BLUE_ON,
        ILLUMINANT_BLOCK_BROWN_ON, ILLUMINANT_BLOCK_GREEN_ON, ILLUMINANT_BLOCK_RED_ON, ILLUMINANT_BLOCK_BLACK_ON
    );

    public static final Set<SLBlockReg<?,?>> TAB_ORDER = ImmutableSet.of(
            ILLUMINANTSLAB, ILLUMINANTPANEL, WALL_LAMP, RODLAMP, LIGHTBULB, EDGELAMP, EDGELAMP_TOP, LAMP_POST,
            ILLUMINANTBLOCK_ON,
            ILLUMINANT_BLOCK_LIGHT_GRAY_ON,
            ILLUMINANT_BLOCK_GRAY_ON,
            ILLUMINANT_BLOCK_BLACK_ON,
            ILLUMINANT_BLOCK_BROWN_ON,
            ILLUMINANT_BLOCK_RED_ON,
            ILLUMINANT_BLOCK_ORANGE_ON,
            ILLUMINANT_BLOCK_YELLOW_ON,
            ILLUMINANT_BLOCK_LIME_ON,
            ILLUMINANT_BLOCK_GREEN_ON,
            ILLUMINANT_BLOCK_CYAN_ON,
            ILLUMINANT_BLOCK_LIGHT_BLUE_ON,
            ILLUMINANT_BLOCK_BLUE_ON,
            ILLUMINANT_BLOCK_PURPLE_ON,
            ILLUMINANT_BLOCK_MAGENTA_ON,
            ILLUMINANT_BLOCK_PINK_ON,

            ILLUMINANTBLOCK,
            ILLUMINANT_BLOCK_LIGHT_GRAY,
            ILLUMINANT_BLOCK_GRAY,
            ILLUMINANT_BLOCK_BLACK,
            ILLUMINANT_BLOCK_BROWN,
            ILLUMINANT_BLOCK_RED,
            ILLUMINANT_BLOCK_ORANGE,
            ILLUMINANT_BLOCK_YELLOW,
            ILLUMINANT_BLOCK_LIME,
            ILLUMINANT_BLOCK_GREEN,
            ILLUMINANT_BLOCK_CYAN,
            ILLUMINANT_BLOCK_LIGHT_BLUE,
            ILLUMINANT_BLOCK_BLUE,
            ILLUMINANT_BLOCK_PURPLE,
            ILLUMINANT_BLOCK_MAGENTA,
            ILLUMINANT_BLOCK_PINK
    );
}
