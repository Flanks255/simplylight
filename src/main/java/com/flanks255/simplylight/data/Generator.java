package com.flanks255.simplylight.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

public class Generator {
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();

        generator.addProvider(true, new SLLootTables(generator));
        generator.addProvider(true, new Recipes(generator));
        generator.addProvider(true, new BlockStates(generator, event.getExistingFileHelper()));
        generator.addProvider(true, new ItemModels(generator, event.getExistingFileHelper()));
        var slBlockTags = new SLBlockTags(generator, event.getExistingFileHelper());
        generator.addProvider(true, slBlockTags);
        generator.addProvider(true, new SLItemTags(generator, slBlockTags, event.getExistingFileHelper()));
        generator.addProvider(true, new LangGen(generator));
    }
}
