package com.flanks255.simplylight.blocks;

import net.minecraft.world.level.block.Block;

import java.util.function.BiConsumer;

public class LampBase extends Block {
    public LampBase(Properties props) {
        super(props);
    }


    public void addLang(BiConsumer<String, String> consumer) {
    }

    protected String getLangBase() {
        return "block." + this.getRegistryName().getNamespace() + "." + this.getRegistryName().getPath();
    }
}
