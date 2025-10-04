package com.flanks255.simplylight.datagen;

import com.flanks255.simplylight.SLBlocks;
import com.flanks255.simplylight.SimplyLightFabric;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class SLBlockTags extends FabricTagProvider.BlockTagProvider {
    public SLBlockTags(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        var WALL_POST_OVERRIDE = getOrCreateTagBuilder(net.minecraft.tags.BlockTags.WALL_POST_OVERRIDE);
        var MINABLE_WITH_PICKAXE = getOrCreateTagBuilder(net.minecraft.tags.BlockTags.MINEABLE_WITH_PICKAXE);

        SLBlocks.BULBS.forEach((B) -> WALL_POST_OVERRIDE.add(B.getBlock()));
        SimplyLightFabric.BLOCKS.forEach((B) -> MINABLE_WITH_PICKAXE.add(B.getB()));
    }
}
