package net.akashaverse.pixelmonhome.net.payload;


import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;


public record WithdrawRequest(String homeEntryId, boolean toParty) implements CustomPacketPayload {
    public static final Type<WithdrawRequest> TYPE = new Type<>(new ResourceLocation("pixelmonhome", "withdraw_req"));
    public static final StreamCodec<FriendlyByteBuf, WithdrawRequest> STREAM_CODEC = new StreamCodec<>() {
        @Override public WithdrawRequest decode(FriendlyByteBuf buf) { return new WithdrawRequest(buf.readUtf(), buf.readBoolean()); }
        @Override public void encode(FriendlyByteBuf buf, WithdrawRequest value) { buf.writeUtf(value.homeEntryId); buf.writeBoolean(value.toParty); }
    };
    @Override public Type<WithdrawRequest> type() { return TYPE; }


    public static void handle(WithdrawRequest msg, IPayloadContext ctx) {
        net.akashaverse.pixelmonhome.service.HomeOps.handleWithdraw(ctx.player(), msg.homeEntryId, msg.toParty);
    }
}