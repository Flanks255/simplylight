package com.flanks255.simplylight.network;

import com.flanks255.simplylight.SimplyLightCommon;
import com.flanks255.simplylight.blocks.EdgeLight;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.SimpleChannel;

public class SLNetwork {
    public static final ResourceLocation name = SimplyLightCommon.SLRes("network");
    public static SimpleChannel register() {
        final SimpleChannel network = ChannelBuilder.named(name).networkProtocolVersion(1).simpleChannel();

        network.messageBuilder(OpenEdgeEditorPacket.class).codec(OpenEdgeEditorPacket.STREAM_CODEC).consumer((packet, ctx) -> ctx.enqueueWork(() -> ClientPacketStuff.OpenEdgeEditorPacket(packet))).add();
        network.messageBuilder(UpdateEdgeLightPacket.class).codec(UpdateEdgeLightPacket.STREAM_CODEC).consumer((packet, ctx) -> ctx.enqueueWork(() -> EdgeLight.updateShape(ctx.getSender().level(), packet.pos(), packet.state()))).add();
        network.build();

        return network;
    }

}
