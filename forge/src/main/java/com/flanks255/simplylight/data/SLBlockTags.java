package com.flanks255.simplylight.data;

import com.flanks255.simplylight.SLBlocks;
import com.flanks255.simplylight.SimplyLightCommon;
import com.flanks255.simplylight.SimplyLightForge;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class SLBlockTags extends BlockTagsProvider {
    public SLBlockTags(DataGenerator generator, CompletableFuture<HolderLookup.Provider> thingIDontUse, ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(), thingIDontUse, SimplyLightCommon.MODID, existingFileHelper);
    }
    @Override
    protected void addTags(@NotNull HolderLookup.Provider something) {
        this.tag(BlockTags.WALL_POST_OVERRIDE).add(SLBlocks.LIGHTBULB.get());

        SimplyLightForge.BLOCKS.getEntries().forEach(this::addPickaxe);
    }

    private void addPickaxe(RegistryObject<Block> block) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block.get());
    }
}
