package com.flanks255.simplylight.datagen;

import com.flanks255.simplylight.SimplyLightFabric;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class SLBlockLoot extends BlockLootSubProvider {
    protected SLBlockLoot(HolderLookup.Provider thing) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), thing);
    }
    public static LootTableProvider getProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> thingIDontUse) {
        return new LootTableProvider(output, Set.of(), List.of(new LootTableProvider.SubProviderEntry(SLBlockLoot::new, LootContextParamSets.BLOCK)), thingIDontUse);
    }

    @Override
    public void generate() {
        SimplyLightFabric.BLOCKS.forEach(block -> dropSelf(block.getB()));
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> biConsumer) {
        generate();

        map.forEach(biConsumer);
    }
}
