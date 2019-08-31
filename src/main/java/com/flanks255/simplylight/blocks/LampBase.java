package com.flanks255.simplylight.blocks;

import com.flanks255.simplylight.SimplyLight;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class LampBase extends Block {
    public LampBase(String name, Properties props) {
        super(props);

        setRegistryName(new ResourceLocation(SimplyLight.MODID, name));
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        if (!ForgeRegistries.ITEMS.containsKey(getRegistryName()))
            return super.getDrops(state, builder);

        List<ItemStack> list = new ArrayList<>();
        list.add( new ItemStack(ForgeRegistries.ITEMS.getValue(getRegistryName())));

        return list;
    }

}
