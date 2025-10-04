package com.flanks255.simplylight.datagen;

import com.flanks255.simplylight.SLBlocks;
import com.flanks255.simplylight.SimplyLightCommon;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class SLItemTags extends ItemTagsProvider {

    public SLItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        SLBlocks.LAMPBLOCKS_ON.forEach(lamp -> tag(SimplyLightCommon.ANY_ON_LAMP).add(lamp.getItem()));
        SLBlocks.LAMPBLOCKS_OFF.forEach( lamp -> tag(SimplyLightCommon.ANY_OFF_LAMP).add(lamp.getItem()));
        SLBlocks.SLABS.forEach( slab -> tag(SimplyLightCommon.ANY_SLAB).add(slab.getItem()));
        SLBlocks.PANELS.forEach( panel -> tag(SimplyLightCommon.ANY_PANEL).add(panel.getItem()));
        SLBlocks.RODS.forEach( rod -> tag(SimplyLightCommon.ANY_ROD).add(rod.getItem()));
        SLBlocks.BULBS.forEach( bulb -> tag(SimplyLightCommon.ANY_BULB).add(bulb.getItem()));
        SLBlocks.FIXTURES.forEach( fixture -> tag(SimplyLightCommon.ANY_FIXTURE).add(fixture.getItem()));
        SLBlocks.POSTS.forEach( post -> tag(SimplyLightCommon.ANY_POST).add(post.getItem()));
        SLBlocks.EDGE_LIGHTS.forEach( edge -> tag(SimplyLightCommon.ANY_EDGE_LIGHT).add(edge.getItem()));
        SLBlocks.EDGE_LIGHTS_TOP.forEach( edge -> tag(SimplyLightCommon.ANY_EDGE_LIGHT_TOP).add(edge.getItem()));
    }
}
