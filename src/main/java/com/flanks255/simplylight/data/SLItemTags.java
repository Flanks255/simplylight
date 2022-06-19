package com.flanks255.simplylight.data;

import com.flanks255.simplylight.SLBlocks;
import com.flanks255.simplylight.SimplyLight;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;

public class SLItemTags extends ItemTagsProvider {
    public SLItemTags(DataGenerator generator, BlockTagsProvider blockTagsProvider) {
        super(generator, blockTagsProvider);
    }

    @Override
    protected void addTags() {
        SLBlocks.LAMPBLOCKS_ON.forEach( lamp -> tag(SimplyLight.ANY_ON_LAMP).add(lamp.getItem()));
        SLBlocks.LAMPBLOCKS_OFF.forEach( lamp -> tag(SimplyLight.ANY_OFF_LAMP).add(lamp.getItem()));

        tag(SimplyLight.ANY_STONE).add(Items.STONE);
        tag(SimplyLight.ANY_STONE).add(Items.ANDESITE);
        tag(SimplyLight.ANY_STONE).add(Items.GRANITE);
        tag(SimplyLight.ANY_STONE).add(Items.DIORITE);
    }
}
