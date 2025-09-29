package com.flanks255.simplylight.network;

import com.flanks255.simplylight.SimplyLight;
import com.flanks255.simplylight.blocks.EdgeLight;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import javax.annotation.Nonnull;

public record UpdateEdgeLightPacket(BlockPos pos, byte state) implements CustomPacketPayload {
    public static final Type<UpdateEdgeLightPacket> TYPE = new CustomPacketPayload.Type<>(SimplyLight.SLRes("update_edge_light"));
    public static final StreamCodec<FriendlyByteBuf, UpdateEdgeLightPacket> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC, $ -> $.pos,
            ByteBufCodecs.BYTE, $ -> $.state,
            UpdateEdgeLightPacket::new
    );
    @Nonnull
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final UpdateEdgeLightPacket packet, IPayloadContext ctx) {
        ctx.enqueueWork(() -> EdgeLight.updateShape(ctx.player().level(), packet.pos, packet.state));
    }
}
