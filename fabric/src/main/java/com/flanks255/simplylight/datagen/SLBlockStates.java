package com.flanks255.simplylight.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;


public class SLBlockStates extends FabricModelProvider {
    public SLBlockStates(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generator) {
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {

    }
}
