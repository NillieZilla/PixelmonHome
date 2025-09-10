package net.akashaverse.pixelmonhome.net.payload;


import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;


public record DepositAck(boolean success, String message) implements CustomPacketPayload {
    public static final Type<DepositAck> TYPE = new Type<>(new ResourceLocation("pixelmonhome", "deposit_ack"));
    public static final StreamCodec<FriendlyByteBuf, DepositAck> STREAM_CODEC = new StreamCodec<>() {
        @Override public DepositAck decode(FriendlyByteBuf buf) { return new DepositAck(buf.readBoolean(), buf.readUtf()); }
        @Override public void encode(FriendlyByteBuf buf, DepositAck value) { buf.writeBoolean(value.success); buf.writeUtf(value.message); }
    };
    @Override public Type<DepositAck> type() { return TYPE; }
}