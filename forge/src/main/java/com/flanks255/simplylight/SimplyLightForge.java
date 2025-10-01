package com.flanks255.simplylight;

import net.minecraftforge.fml.common.Mod;

@Mod(SimplyLightCommon.MODID)
public class SimplyLightForge {

    public SimplyLightForge() {

        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.

        // Use Forge to bootstrap the Common mod.
        SimplyLightCommon.LOGGER.info("Hello Forge world!");
        SimplyLightCommon.init();

    }
}