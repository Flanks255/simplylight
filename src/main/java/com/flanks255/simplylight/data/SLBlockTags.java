package com.flanks255.simplylight.data;

import com.flanks255.simplylight.SLBlockReg;
import com.flanks255.simplylight.SLBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;

public class SLBlockTags extends BlockTagsProvider {
    public SLBlockTags(DataGenerator generatorIn) {
        super(generatorIn);
    }


    @Override
    protected void addTags() {
        this.tag(BlockTags.WALL_POST_OVERRIDE).add(SLBlocks.LIGHTBULB.get());

        SLBlocks.BLOCKS.forEach(this::addPickaxe);
    }

    private void addPickaxe(SLBlockReg<?,?> block) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block.get());
    }
}
