package com.flanks255.simplylight.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public class Generator {
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();

        generator.addProvider(new LootTables(generator));
        generator.addProvider(new Recipes(generator));
        generator.addProvider(new BlockStates(generator, event.getExistingFileHelper()));
        generator.addProvider(new ItemModels(generator, event.getExistingFileHelper()));
        SLBlockTags slBlockTags = new SLBlockTags(generator, event.getExistingFileHelper());
        generator.addProvider(slBlockTags);
        generator.addProvider(new SLItemTags(generator, slBlockTags, event.getExistingFileHelper()));
        generator.addProvider(new LangGen(generator));
    }
}
