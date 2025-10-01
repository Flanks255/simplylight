package com.flanks255.simplylight.blocks;

import com.google.common.base.Suppliers;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class LampBase extends Block {
    public LampBase(Properties props) {
        super(props);
    }

    private final Supplier<ResourceLocation> lazyRes = Suppliers.memoize(() -> BuiltInRegistries.BLOCK.getKey(this));

    public ResourceLocation getRegistryName() {
        return lazyRes.get();
    }


    public void addLang(BiConsumer<String, String> consumer) {
    }
}
