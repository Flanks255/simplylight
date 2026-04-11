package com.flanks255.simplylight.platform;

import com.flanks255.simplylight.network.OpenEdgeEditorPacket;
import com.flanks255.simplylight.network.UpdateEdgeLightPacket;
import com.flanks255.simplylight.platform.services.IPlatformHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
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
        ServerPlayNetworking.send(player, new OpenEdgeEditorPacket(pos, initialState));
    }

    @Override
    public void sendEdgeUpdatePacket(BlockPos targetPos, byte newState) {
        ClientPlayNetworking.send(new UpdateEdgeLightPacket(targetPos, newState));
    }
}
