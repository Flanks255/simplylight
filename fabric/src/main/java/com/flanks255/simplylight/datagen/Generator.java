package com.flanks255.simplylight.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class Generator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        var pack = fabricDataGenerator.createPack();

        pack.addProvider(SLLang::new);
        var blockTags = pack.addProvider(SLBlockTags::new);
        pack.addProvider((output, lookup) -> new SLItemTags(output, lookup, blockTags.contentsGetter()));
    }
}
