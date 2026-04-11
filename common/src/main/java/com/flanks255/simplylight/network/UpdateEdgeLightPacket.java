package com.flanks255.simplylight.network;

import com.flanks255.simplylight.SimplyLightCommon;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import org.jetbrains.annotations.NotNull;

public record UpdateEdgeLightPacket(BlockPos pos, byte state) implements CustomPacketPayload {
    public static final Type<UpdateEdgeLightPacket> TYPE = new CustomPacketPayload.Type<>(SimplyLightCommon.SLRes("update_edge_light"));
    public static final StreamCodec<FriendlyByteBuf, UpdateEdgeLightPacket> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC, $ -> $.pos,
            ByteBufCodecs.BYTE, $ -> $.state,
            UpdateEdgeLightPacket::new
    );
    @NotNull
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public byte state() {
        return state;
    }

    @Override
    public BlockPos pos() {
        return pos;
    }
}
