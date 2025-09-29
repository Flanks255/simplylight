package com.flanks255.simplylight.data;

import com.flanks255.simplylight.SLBlocks;
import com.flanks255.simplylight.SimplyLight;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class SLBlockTags extends BlockTagsProvider {
    public SLBlockTags(DataGenerator generator, CompletableFuture<HolderLookup.Provider> thingIDontUse, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(), thingIDontUse, SimplyLight.MODID, existingFileHelper);
    }
    @Override
    protected void addTags(@Nonnull HolderLookup.Provider something) {
        this.tag(BlockTags.WALL_POST_OVERRIDE).add(SLBlocks.LIGHTBULB.get());

        SLBlocks.BLOCKS.getEntries().forEach(this::addPickaxe);
    }

    private void addPickaxe(DeferredHolder<Block, ? extends Block> block) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block.get());
    }
}
