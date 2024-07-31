package com.flanks255.simplylight.data;

import com.flanks255.simplylight.SLBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;


public class SLBlockLoot extends BlockLootSubProvider {
    public static LootTableProvider getProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> thingIDontUse) {
        return new LootTableProvider(output, Set.of(), List.of(new LootTableProvider.SubProviderEntry(SLBlockLoot::new, LootContextParamSets.BLOCK)));
    }
    protected SLBlockLoot() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    final Map<ResourceLocation, LootTable.Builder> map = new HashMap();

    @Override
    public void generate() {
        SLBlocks.BLOCKS.forEach(block -> {
            map.put(block.get().getLootTable(), createSingleItemTable(block.get()));
        });
    }

    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> biConsumer) {
        generate();

        map.forEach(biConsumer);
    }
}