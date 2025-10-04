package com.flanks255.simplylight.platform;

import com.flanks255.simplylight.network.OpenEdgeEditorPacket;
import com.flanks255.simplylight.platform.services.IPlatformHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.network.PacketDistributor;

public class NeoForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "NeoForge";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return !FMLLoader.isProduction();
    }

    @Override
    public void sendEdgeEditorPacket(ServerPlayer player, BlockPos pos, byte initialState) {
        PacketDistributor.sendToPlayer(player, new OpenEdgeEditorPacket(pos, initialState));
    }
}