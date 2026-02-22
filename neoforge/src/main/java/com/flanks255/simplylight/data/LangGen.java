package com.flanks255.simplylight.data;

import com.flanks255.simplylight.SimplyLightCommon;
import com.flanks255.simplylight.SimplyLightNeoForge;
import com.flanks255.simplylight.blocks.LampBase;
import com.flanks255.simplylight.datagen.CommonLang;
import net.minecraft.data.DataGenerator;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class LangGen extends LanguageProvider {
    public LangGen(DataGenerator gen) {
        super(gen.getPackOutput(), SimplyLightCommon.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        CommonLang.buildLang(this::add);

        SimplyLightNeoForge.BLOCKS.getEntries().forEach(block -> ((LampBase) block.get()).addLang(this::add)); //TODO
    }
}
