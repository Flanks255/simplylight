package com.flanks255.simplylight.network;

import com.flanks255.simplylight.SimplyLightCommon;
import com.flanks255.simplylight.blocks.EdgeLight;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

public class SLNetwork {
    public static void register(final RegisterPayloadHandlersEvent event) {
        event.registrar(SimplyLightCommon.MODID)
                .playToClient(OpenEdgeEditorPacket.TYPE, OpenEdgeEditorPacket.STREAM_CODEC, (packet, ctx) -> ctx.enqueueWork(() -> ClientPacketStuff.OpenEdgeEditorPacket(packet)))
                .playToServer(UpdateEdgeLightPacket.TYPE, UpdateEdgeLightPacket.STREAM_CODEC, (packet, ctx) -> ctx.enqueueWork(() -> EdgeLight.updateShape(ctx.player().level(), packet.pos(), packet.state())));
    }
}
