package com.flanks255.simplylight.data;

import com.flanks255.simplylight.SLBlockReg;
import com.flanks255.simplylight.SLBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class SLBlockTags extends FabricTagProvider.BlockTagProvider {
    public SLBlockTags(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> thingIDontUse) {
        super(output, thingIDontUse);
    }


    @Override
    protected void addTags(HolderLookup.Provider arg) {
        getOrCreateTagBuilder(BlockTags.WALL_POST_OVERRIDE).add(SLBlocks.LIGHTBULB.get());

        SLBlocks.BLOCKS.forEach(this::addPickaxe);
    }

    private void addPickaxe(SLBlockReg<?,?> block) {
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(block.get());
    }
}
