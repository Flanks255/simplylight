package com.flanks255.simplylight.platform;

import com.flanks255.simplylight.SimplyLightForge;
import com.flanks255.simplylight.network.OpenEdgeEditorPacket;
import com.flanks255.simplylight.network.UpdateEdgeLightPacket;
import com.flanks255.simplylight.platform.services.IPlatformHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.network.PacketDistributor;

public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "Forge";
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
        SimplyLightForge.NETWORK_CHANNEL.send(new OpenEdgeEditorPacket(pos, initialState), PacketDistributor.PLAYER.with(player));
    }

    @Override
    public void sendEdgeUpdatePacket(BlockPos targetPos, byte newState) {
        SimplyLightForge.NETWORK_CHANNEL.send(new UpdateEdgeLightPacket(targetPos, newState), PacketDistributor.SERVER.noArg());
    }
}