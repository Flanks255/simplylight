package com.flanks255.simplylight.network;

import com.flanks255.simplylight.SimplyLight;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import javax.annotation.Nonnull;

public record OpenEdgeEditorPacket(BlockPos target, byte initialState) implements CustomPacketPayload {
    public static final Type<OpenEdgeEditorPacket> TYPE = new CustomPacketPayload.Type<>(SimplyLight.SLRes("open_edge_editor"));
    public static final StreamCodec<FriendlyByteBuf, OpenEdgeEditorPacket> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC, $ -> $.target,
            ByteBufCodecs.BYTE, $ -> $.initialState,
            OpenEdgeEditorPacket::new
    );

    @Nonnull
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final OpenEdgeEditorPacket packet, IPayloadContext ctx) {
        ctx.enqueueWork(() -> ClientPacketStuff.OpenEdgeEditorPacket(packet));
    }
}
