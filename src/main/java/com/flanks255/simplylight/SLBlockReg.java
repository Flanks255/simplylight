package com.flanks255.simplylight;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Function;
import java.util.function.Supplier;

public class SLBlockReg<B extends Block, I extends Item> implements Supplier<B> {
    private final RegistryObject<B> block;
    private final RegistryObject<I> item;

    @Override
    public B get() {
        return block.get();
    }

    public SLBlockReg(String name, Supplier<B> blockSupplier, Function<B, I> itemSupplier) {

        block = SLBlocks.BLOCKS.register(name, blockSupplier);
        item = SLBlocks.ITEMS.register(name, () -> itemSupplier.apply(block.get()));
    }

    public B getBlock() {
        return block.get();
    }

    public I getItem() {
        return item.get();
    }
}
