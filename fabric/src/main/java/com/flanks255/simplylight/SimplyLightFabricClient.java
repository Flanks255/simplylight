package com.flanks255.simplylight;

import com.flanks255.simplylight.network.ClientPacketStuff;
import com.flanks255.simplylight.network.OpenEdgeEditorPacket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class SimplyLightFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(OpenEdgeEditorPacket.TYPE, ((payload, context) -> ClientPacketStuff.OpenEdgeEditorPacket(payload)));
    }
}
