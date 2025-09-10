package net.akashaverse.pixelmonhome.net.payload;


import com.mojang.serialization.Codec;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;


public record DepositRequest(int slotIndex, boolean fromParty) implements CustomPacketPayload {
    public static final Type<DepositRequest> TYPE = new Type<>(new ResourceLocation("pixelmonhome", "deposit_req"));
    public static final StreamCodec<FriendlyByteBuf, DepositRequest> STREAM_CODEC = new StreamCodec<>() {
        @Override public DepositRequest decode(FriendlyByteBuf buf) { return new DepositRequest(buf.readVarInt(), buf.readBoolean()); }
        @Override public void encode(FriendlyByteBuf buf, DepositRequest value) { buf.writeVarInt(value.slotIndex); buf.writeBoolean(value.fromParty); }
    };
    @Override public Type<DepositRequest> type() { return TYPE; }


    public static void handle(DepositRequest msg, IPayloadContext ctx) {
// Server thread: validate, move Pokémon into Home backend.
        net.akashaverse.pixelmonhome.service.HomeOps.handleDeposit(ctx.player(), msg.slotIndex, msg.fromParty);
    }
}