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
        builder.add("itemGroup.simplylight", "Simply Light");
        builder.add("simplylight.shift", "Press <%s> for info.");
        builder.add("simplylight.key.shift", "Shift");
        builder.add("simplylight.redstone", "Redstone");
        builder.add("simplylight.pack.fullblock_ctm", "Simply Light Full block CTM");
        builder.add("simplylight.gui.facing", "Facing");
        builder.add("simplylight.gui.exit", "Exit");
        builder.add("simplylight.gui.north", "North");
        builder.add("simplylight.gui.east", "East");
        builder.add("simplylight.gui.south", "South");
        builder.add("simplylight.gui.west", "West");

        SimplyLightFabric.BLOCKS.forEach(block -> ((LampBase) block.getB()).addLang(builder::add));
    }
}
