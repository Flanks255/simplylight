package com.flanks255.simplylight.data;

import com.flanks255.simplylight.SLBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;


public class SLLootTables extends FabricBlockLootTableProvider {
    public SLLootTables(FabricDataGenerator gen) {
        super(gen);
    }
    @Override
    protected void generateBlockLootTables() {
        SLBlocks.BLOCKS.forEach(block -> {
            this.dropSelf(block.get());
        });
    }
}
