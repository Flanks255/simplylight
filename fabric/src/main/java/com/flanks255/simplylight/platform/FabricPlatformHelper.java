package com.flanks255.simplylight.platform;

import com.flanks255.simplylight.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public void sendEdgeEditorPacket(ServerPlayer player, BlockPos pos, byte initialState) {
        //TODO implement
    }

    @Override
    public void sendEdgeUpdatePacket(BlockPos targetPos, byte newState) {
        //TODO implement
    }
}
