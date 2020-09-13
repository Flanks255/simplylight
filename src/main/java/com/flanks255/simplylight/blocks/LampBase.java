package com.flanks255.simplylight.blocks;

import com.flanks255.simplylight.SimplyLight;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collections;
import java.util.List;

public class LampBase extends Block {
    public LampBase(Properties props) {
        super(props);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        ResourceLocation res = getRegistryName();
        if (!ForgeRegistries.ITEMS.containsKey(res))
            return super.getDrops(state, builder);

        return Collections.singletonList(new ItemStack(ForgeRegistries.ITEMS.getValue(res)));
    }
}
