package net.akashaverse.pixelmonhome.net.payload;


import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;


public record WithdrawAck(boolean success, String message) implements CustomPacketPayload {
    public static final Type<WithdrawAck> TYPE = new Type<>(new ResourceLocation("pixelmonhome", "withdraw_ack"));
    public static final StreamCodec<FriendlyByteBuf, WithdrawAck> STREAM_CODEC = new StreamCodec<>() {
        @Override public WithdrawAck decode(FriendlyByteBuf buf) { return new WithdrawAck(buf.readBoolean(), buf.readUtf()); }
        @Override public void encode(FriendlyByteBuf buf, WithdrawAck value) { buf.writeBoolean(value.success); buf.writeUtf(value.message); }
    };
    @Override public Type<WithdrawAck> type() { return TYPE; }
}