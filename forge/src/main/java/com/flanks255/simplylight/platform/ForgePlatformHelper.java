package com.flanks255.simplylight.platform;

import com.flanks255.simplylight.platform.services.IPlatformHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;

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
        //TODO implement
    }

    @Override
    public void sendEdgeUpdatePacket(BlockPos targetPos, byte newState) {
        //TODO implement
    }
}