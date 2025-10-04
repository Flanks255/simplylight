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
        SimplyLightFabric.BLOCKS.add(new Pair<>(name, blockSupplier.get()));
        return blockSupplier;
    }

    @Override
    public <I extends Item> Supplier<I> registerItem(String name, Supplier<I> itemSupplier) {
        SimplyLightFabric.ITEMS.add(new Pair<>(name, itemSupplier.get()));
        return itemSupplier;
    }


}
