package com.flanks255.simplylight;

import com.flanks255.simplylight.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimplyLightCommon {
    public static final String MODID = "simplylight";
    public static final Logger LOGGER = LoggerFactory.getLogger("Simply Light");

    public static void init() {

        LOGGER.info("Hello from Common init on {}! we are currently in a {} environment!", Services.PLATFORM.getPlatformName(), Services.PLATFORM.getEnvironmentName());
        LOGGER.info("The ID for diamonds is {}", BuiltInRegistries.ITEM.getKey(Items.DIAMOND));

    }
}