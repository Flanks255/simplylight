package com.flanks255.simplylight.data;

import com.flanks255.simplylight.SimplyLight;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class SLBlockTags extends BlockTagsProvider {
    public SLBlockTags(DataGenerator generatorIn, @Nullable ExistingFileHelper existingFileHelper) {
        super(generatorIn, SimplyLight.MODID, existingFileHelper);
    }


    @Override
    protected void addTags() {
        this.tag(BlockTags.WALL_POST_OVERRIDE).add(SimplyLight.LIGHTBULB.get());

        SimplyLight.BLOCKS.getEntries().forEach((blockRegistryObject -> addPickaxe(blockRegistryObject.get())));
    }

    private void addPickaxe(Block block) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block);
    }
}
