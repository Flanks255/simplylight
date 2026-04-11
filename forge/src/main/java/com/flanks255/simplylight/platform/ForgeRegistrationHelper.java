package com.flanks255.simplylight.platform;

import com.flanks255.simplylight.SimplyLightForge;
import com.flanks255.simplylight.platform.services.IRegistrationHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class ForgeRegistrationHelper implements IRegistrationHelper {

    @Override
    public <B extends Block> Supplier<B> registerBlock(String name, Supplier<B> blockSupplier) {
        return SimplyLightForge.BLOCKS.register(name, blockSupplier);
    }

    @Override
    public <I extends Item> Supplier<I> registerItem(String name, Supplier<I> itemSupplier) {
        return SimplyLightForge.ITEMS.register(name, itemSupplier);
    }


}
