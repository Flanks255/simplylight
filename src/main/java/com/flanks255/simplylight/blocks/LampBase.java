package com.flanks255.simplylight.blocks;

import com.flanks255.simplylight.SimplyLight;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

public class LampBase extends Block {
    public LampBase(String name, Properties props) {
        super(props);

        setRegistryName(new ResourceLocation(SimplyLight.MODID, name));
    }
}
