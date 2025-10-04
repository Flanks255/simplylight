package com.flanks255.simplylight;


import com.flanks255.simplylight.platform.Services;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Function;
import java.util.function.Supplier;

public class SLBlockReg<B extends Block, I extends Item> implements Supplier<B> {
    private final Supplier<B> block;
    private final Supplier<I> item;

    private final ResourceLocation registryName;

    @Override
    public B get() {
        return block.get();
    }

    public SLBlockReg(String name, Supplier<B> blockSupplier, Function<B, I> itemSupplier) {
        registryName = SimplyLightCommon.SLRes(name);

        block = Services.REGISTRATION.registerBlock(name, blockSupplier);
        item = Services.REGISTRATION.registerItem(name, () -> itemSupplier.apply(block.get()));
    }

    public B getBlock() {
        return block.get();
    }

    public I getItem() {
        return item.get();
    }

    public ResourceLocation getRegistryName() {
        return registryName;
    }
}
