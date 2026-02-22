package com.flanks255.simplylight.datagen;

import com.flanks255.simplylight.SimplyLightFabric;
import com.flanks255.simplylight.blocks.LampBase;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class SLLang extends FabricLanguageProvider {
    public SLLang(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder builder) {
        CommonLang.buildLang(builder::add);
        SimplyLightFabric.BLOCKS.forEach(block -> ((LampBase) block.getB()).addLang(builder::add)); //TODO abstract this
    }
}
