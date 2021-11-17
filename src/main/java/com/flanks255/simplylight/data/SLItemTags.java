package com.flanks255.simplylight.data;

import com.flanks255.simplylight.SLBlocks;
import com.flanks255.simplylight.SimplyLight;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class SLItemTags extends ItemTagsProvider {
    public SLItemTags(DataGenerator generator, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, blockTagsProvider, SimplyLight.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        SLBlocks.LAMPBLOCKS_ON.forEach( lamp -> tag(SimplyLight.ANY_ON_LAMP).add(lamp.getItem()));
        SLBlocks.LAMPBLOCKS_OFF.forEach( lamp -> tag(SimplyLight.ANY_OFF_LAMP).add(lamp.getItem()));
    }
}
