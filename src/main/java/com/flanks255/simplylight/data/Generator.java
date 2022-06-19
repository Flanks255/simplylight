package com.flanks255.simplylight.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class Generator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        generator.addProvider(true, new SLLootTables(generator));
        generator.addProvider(true, new Recipes(generator));
        //generator.addProvider(true, new BlockStates(generator, event.getExistingFileHelper()));
        //generator.addProvider(true, new ItemModels(generator, event.getExistingFileHelper()));
        var slBlockTags = new SLBlockTags(generator);
        generator.addProvider(true, slBlockTags);
        generator.addProvider(true, new SLItemTags(generator, slBlockTags));
        //generator.addProvider(true, new LangGen(generator));
    }
}
