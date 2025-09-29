package com.flanks255.simplylight;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(SimplyLightCommon.MOD_ID)
public class SimplyLightNeoforge {

    public SimplyLightNeoforge(IEventBus eventBus) {

        // This method is invoked by the NeoForge mod loader when it is ready
        // to load your mod. You can access NeoForge and Common code in this
        // project.

        // Use NeoForge to bootstrap the Common mod.
        SimplyLightCommon.init();
        SimplyLightCommon.LOGGER.info("Hello NeoForge world!");


    }
}