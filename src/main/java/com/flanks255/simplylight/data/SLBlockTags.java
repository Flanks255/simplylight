package com.flanks255.simplylight.data;

import com.flanks255.simplylight.SLBlocks;
import com.flanks255.simplylight.SimplyLight;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class SLBlockTags extends BlockTagsProvider {
    public SLBlockTags(DataGenerator generatorIn, @Nullable ExistingFileHelper existingFileHelper) {
        super(generatorIn, SimplyLight.MODID, existingFileHelper);
    }


    @Override
    protected void addTags() {
        this.tag(BlockTags.WALL_POST_OVERRIDE).add(SLBlocks.LIGHTBULB.get());
    }
}
