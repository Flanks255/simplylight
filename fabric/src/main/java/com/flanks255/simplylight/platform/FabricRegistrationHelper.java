package com.flanks255.simplylight.platform;

import com.flanks255.simplylight.SimplyLightFabric;
import com.flanks255.simplylight.platform.services.IRegistrationHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import oshi.util.tuples.Pair;

import java.util.function.Supplier;

public class FabricRegistrationHelper implements IRegistrationHelper {

    @Override
    public <B extends Block> Supplier<B> registerBlock(String name, Supplier<B> blockSupplier) {
        B block = blockSupplier.get();
        SimplyLightFabric.BLOCKS.add(new Pair<>(name, block));
        return () -> block;
    }

    @Override
    public <I extends Item> Supplier<I> registerItem(String name, Supplier<I> itemSupplier) {
        I item = itemSupplier.get();
        SimplyLightFabric.ITEMS.add(new Pair<>(name, item));
        return () -> item;
    }


}
