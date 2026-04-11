package com.flanks255.simplylight.network;

import com.flanks255.simplylight.SimplyLightCommon;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import org.jetbrains.annotations.NotNull;

public record OpenEdgeEditorPacket(BlockPos target, byte initialState) implements CustomPacketPayload {
    public static final Type<OpenEdgeEditorPacket> TYPE = new CustomPacketPayload.Type<>(SimplyLightCommon.SLRes("open_edge_editor"));
    public static final StreamCodec<FriendlyByteBuf, OpenEdgeEditorPacket> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC, $ -> $.target,
            ByteBufCodecs.BYTE, $ -> $.initialState,
            OpenEdgeEditorPacket::new
    );

    @NotNull
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
