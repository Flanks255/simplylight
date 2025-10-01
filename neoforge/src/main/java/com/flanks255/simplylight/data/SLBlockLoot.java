package com.flanks255.simplylight.data;

import com.flanks255.simplylight.SLBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.neoforge.registries.DeferredHolder;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class SLBlockLoot extends BlockLootSubProvider {
    protected SLBlockLoot(HolderLookup.Provider thing) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), thing);
    }
    public static LootTableProvider getProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> thingIDontUse) {
        return new LootTableProvider(output, Set.of(), List.of(new LootTableProvider.SubProviderEntry(SLBlockLoot::new, LootContextParamSets.BLOCK)), thingIDontUse);
    }

    @Override
    protected void generate() {
        for(DeferredHolder<Block, ? extends Block> block : SLBlocks.BLOCKS.getEntries()) {
            this.dropSelf(block.get());
        }
    }

    @Nonnull
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return SLBlocks.BLOCKS.getEntries().stream().map(DeferredHolder::get).collect(Collectors.toList());
    }
}
