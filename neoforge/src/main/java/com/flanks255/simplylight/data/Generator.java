package com.flanks255.simplylight.data;

import net.minecraft.data.DataGenerator;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class Generator {
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        
        generator.addProvider(true, SLBlockLoot.getProvider(event.getGenerator().getPackOutput(), event.getLookupProvider()));
        generator.addProvider(true, new Recipes(generator, event.getLookupProvider()));
        generator.addProvider(true, new BlockStates(generator, event.getExistingFileHelper()));
        generator.addProvider(true, new ItemModels(generator, event.getExistingFileHelper()));
        var slBlockTags = new SLBlockTags(generator, event.getLookupProvider(), event.getExistingFileHelper());
        generator.addProvider(true, slBlockTags);
        generator.addProvider(true, new SLItemTags(generator,event.getLookupProvider(), slBlockTags, event.getExistingFileHelper()));
        generator.addProvider(true, new LangGen(generator));
    }
}
