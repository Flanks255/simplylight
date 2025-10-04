package com.flanks255.simplylight.platform.services;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public interface IPlatformHelper {
    String getPlatformName();
    boolean isModLoaded(String modId);
    boolean isDevelopmentEnvironment();
    default String getEnvironmentName() {
        return isDevelopmentEnvironment() ? "development" : "production";
    }

    void sendEdgeEditorPacket(ServerPlayer player, BlockPos pos, byte initialState);
    void sendEdgeUpdatePacket(BlockPos targetPos, byte newState);
}