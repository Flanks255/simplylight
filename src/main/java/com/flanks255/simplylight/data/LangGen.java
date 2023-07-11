package com.flanks255.simplylight.data;

import com.flanks255.simplylight.SLBlocks;
import com.flanks255.simplylight.SimplyLight;
import com.flanks255.simplylight.blocks.LampBase;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.data.DataGenerator;

public class LangGen extends FabricLanguageProvider {
    public LangGen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add("itemGroup.simplylight", "Simply Light");
        translationBuilder.add("simplylight.shift", "Press <%s> for info.");
        translationBuilder.add("simplylight.key.shift", "Shift");
        translationBuilder.add("simplylight.redstone", "Redstone");

        SLBlocks.BLOCKS.forEach(block -> ((LampBase) block.get()).addLang(translationBuilder::add));
    }
}
