package com.flanks255.simplylight.network;

import com.flanks255.simplylight.SimplyLight;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

public class SLNetwork {
    public static void register(final RegisterPayloadHandlersEvent event) {
        event.registrar(SimplyLight.MODID)
                .playToClient(OpenEdgeEditorPacket.TYPE, OpenEdgeEditorPacket.STREAM_CODEC, OpenEdgeEditorPacket::handle)
                .playToServer(UpdateEdgeLightPacket.TYPE, UpdateEdgeLightPacket.STREAM_CODEC, UpdateEdgeLightPacket::handle);
    }
}
