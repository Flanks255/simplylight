package com.flanks255.simplylight;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Function;
import java.util.function.Supplier;

public class SLBlockReg<B extends Block, I extends Item> implements Supplier<B> {
    private String name;

    private RegistryObject<B> block;
    private RegistryObject<I> item;

    private Supplier<B> blockSupplier;
    private Function<B, I> itemSupplier;

    @Override
    public B get() {
        return block.get();
    }

    public SLBlockReg(String name, Supplier<B> blockSupplier, Function<B, I> itemSupplier) {
        this.name = name;
        this.blockSupplier = blockSupplier;
        this.itemSupplier = itemSupplier;

        block = SimplyLight.BLOCKS.register(name, blockSupplier);
        item = SimplyLight.ITEMS.register(name, () -> itemSupplier.apply(block.get()));
    }

    public B getBlock() {
        return block.get();
    }

    public I getItem() {
        return item.get();
    }
}
