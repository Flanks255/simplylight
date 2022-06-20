package com.flanks255.simplylight;


import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Function;
import java.util.function.Supplier;

public class SLBlockReg<B extends Block, I extends Item> implements Supplier<B> {
    private final B block;
    private final I item;

    private final ResourceLocation registryName;

    @Override
    public B get() {
        return block;
    }

    public SLBlockReg(String name, Supplier<B> blockSupplier, Function<B, I> itemSupplier) {
        registryName = new ResourceLocation(SimplyLight.MODID, name);
        block = blockSupplier.get();

        item = itemSupplier.apply(block);

        SLBlocks.BLOCKS.add(this);
    }

    public ResourceLocation getRegistryName() {
        return registryName;
    }

    public B getBlock() {
        return block;
    }

    public I getItem() {
        return item;
    }
}
