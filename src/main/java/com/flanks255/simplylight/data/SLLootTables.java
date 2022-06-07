package com.flanks255.simplylight.data;

import com.flanks255.simplylight.SLBlocks;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class SLLootTables extends LootTableProvider {
    public SLLootTables(DataGenerator gen) {
        super(gen);
    }

    @Nonnull
    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return ImmutableList.of(Pair.of(Blocks::new, LootContextParamSets.BLOCK));
    }

    private static class Blocks extends BlockLoot {
        @Override
        protected void addTables() {
            for(RegistryObject<Block> block : SLBlocks.BLOCKS.getEntries()) {
                this.dropSelf(block.get());
            }
        }

        @Nonnull
        @Override
        protected Iterable<Block> getKnownBlocks() {
            return SLBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).collect(Collectors.toList());
        }
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, @Nonnull ValidationContext validationTracker) {
        map.forEach((name, table) -> LootTables.validate(validationTracker, name, table));
    }
}
