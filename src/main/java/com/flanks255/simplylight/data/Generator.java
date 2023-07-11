package com.flanks255.simplylight.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class Generator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        var pack = generator.createPack();
        pack.addProvider(SLBlockLoot::getProvider);
        pack.addProvider(Recipes::new);
        //generator.addProvider(true, new BlockStates(generator, event.getExistingFileHelper()));
        //generator.addProvider(true, new ItemModels(generator, event.getExistingFileHelper()));
        var slBlockTags = pack.addProvider(SLBlockTags::new);
        pack.addProvider((output, lookup) -> new SLItemTags(output, lookup, slBlockTags));
        pack.addProvider((output, lookup) -> new LangGen(output));
    }
}
