package com.flanks255.simplylight.data;

import com.flanks255.simplylight.SimplyLightForge;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

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
        for(RegistryObject<Block> block : SimplyLightForge.BLOCKS.getEntries()) {
            dropSelf(block.get());
        }
    }

    @NotNull
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return SimplyLightForge.BLOCKS.getEntries().stream().map(RegistryObject::get).collect(Collectors.toList());
    }
}
