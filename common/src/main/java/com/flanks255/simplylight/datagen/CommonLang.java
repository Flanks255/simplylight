package com.flanks255.simplylight.datagen;

import java.util.function.BiConsumer;

public class CommonLang {
    public static void buildLang(BiConsumer<String, String> consumer) {
        consumer.accept("itemGroup.simplylight", "Simply Light");
        consumer.accept("simplylight.shift", "Press <%s> for info.");
        consumer.accept("simplylight.key.shift", "Shift");
        consumer.accept("simplylight.redstone", "Redstone");
        consumer.accept("simplylight.pack.fullblock_ctm", "Simply Light Full block CTM");

        consumer.accept("simplylight.gui.facing", "Facing");
        consumer.accept("simplylight.gui.exit", "Exit");
        consumer.accept("simplylight.gui.north", "North");
        consumer.accept("simplylight.gui.east", "East");
        consumer.accept("simplylight.gui.south", "South");
        consumer.accept("simplylight.gui.west", "West");
    }
}
