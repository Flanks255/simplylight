package com.flanks255.simplylight.data;

import com.flanks255.simplylight.SLBlocks;
import com.flanks255.simplylight.SimplyLight;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class SLItemTags extends ItemTagsProvider {
    public SLItemTags(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> thingIDontUse, FabricTagProvider.BlockTagProvider blockTagsProvider) {
        super(output, thingIDontUse, blockTagsProvider.contentsGetter());
    }

    @Override
    protected void addTags(HolderLookup.Provider somethingSomethingDarkside) {
        SLBlocks.LAMPBLOCKS_ON.forEach( lamp -> tag(SimplyLight.ANY_ON_LAMP).add(lamp.getItem()));
        SLBlocks.LAMPBLOCKS_OFF.forEach( lamp -> tag(SimplyLight.ANY_OFF_LAMP).add(lamp.getItem()));

        tag(SimplyLight.ANY_STONE).add(Items.STONE);
        tag(SimplyLight.ANY_STONE).add(Items.ANDESITE);
        tag(SimplyLight.ANY_STONE).add(Items.GRANITE);
        tag(SimplyLight.ANY_STONE).add(Items.DIORITE);
    }
}
