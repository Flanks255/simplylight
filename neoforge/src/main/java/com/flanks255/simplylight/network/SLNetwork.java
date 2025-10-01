package com.flanks255.simplylight.network;

import com.flanks255.simplylight.SimplyLightCommon;
import com.flanks255.simplylight.SimplyLightNeoForge;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

public class SLNetwork {
    public static void register(final RegisterPayloadHandlersEvent event) {
        event.registrar(SimplyLightCommon.MODID)
                .playToClient(OpenEdgeEditorPacket.TYPE, OpenEdgeEditorPacket.STREAM_CODEC, OpenEdgeEditorPacket::handle)
                .playToServer(UpdateEdgeLightPacket.TYPE, UpdateEdgeLightPacket.STREAM_CODEC, UpdateEdgeLightPacket::handle);
    }
}
