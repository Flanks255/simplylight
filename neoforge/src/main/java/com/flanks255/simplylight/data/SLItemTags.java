package com.flanks255.simplylight.data;

import com.flanks255.simplylight.SLBlocks;
import com.flanks255.simplylight.SimplyLightCommon;
import com.flanks255.simplylight.SimplyLightNeoForge;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.ItemTagsProvider;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class SLItemTags extends ItemTagsProvider {
    public SLItemTags(DataGenerator generator, CompletableFuture<HolderLookup.Provider> thingIDontUse, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(), thingIDontUse, blockTagsProvider.contentsGetter(), SimplyLightCommon.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(@Nonnull HolderLookup.Provider useless) {
        SLBlocks.LAMPBLOCKS_ON.forEach( lamp -> tag(SimplyLightNeoForge.ANY_ON_LAMP).add(lamp.getItem()));
        SLBlocks.LAMPBLOCKS_OFF.forEach( lamp -> tag(SimplyLightNeoForge.ANY_OFF_LAMP).add(lamp.getItem()));
        SLBlocks.SLABS.forEach( slab -> tag(SimplyLightNeoForge.ANY_SLAB).add(slab.getItem()));
        SLBlocks.PANELS.forEach( panel -> tag(SimplyLightNeoForge.ANY_PANEL).add(panel.getItem()));
        SLBlocks.RODS.forEach( rod -> tag(SimplyLightNeoForge.ANY_ROD).add(rod.getItem()));
        SLBlocks.BULBS.forEach( bulb -> tag(SimplyLightNeoForge.ANY_BULB).add(bulb.getItem()));
        SLBlocks.FIXTURES.forEach( fixture -> tag(SimplyLightNeoForge.ANY_FIXTURE).add(fixture.getItem()));
        SLBlocks.POSTS.forEach( post -> tag(SimplyLightNeoForge.ANY_POST).add(post.getItem()));
        SLBlocks.EDGE_LIGHTS.forEach( edge -> tag(SimplyLightNeoForge.ANY_EDGE_LIGHT).add(edge.getItem()));
        SLBlocks.EDGE_LIGHTS_TOP.forEach( edge -> tag(SimplyLightNeoForge.ANY_EDGE_LIGHT_TOP).add(edge.getItem()));
    }
}
