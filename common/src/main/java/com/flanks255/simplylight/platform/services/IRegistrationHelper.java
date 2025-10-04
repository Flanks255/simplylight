package com.flanks255.simplylight.platform.services;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public interface IRegistrationHelper {
    public <B extends Block> Supplier<B> registerBlock(String name, Supplier<B> blockSupplier);
    public <I extends Item> Supplier<I> registerItem(String name, Supplier<I> itemSupplier);
}
