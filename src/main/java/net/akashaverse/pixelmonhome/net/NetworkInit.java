package net.akashaverse.pixelmonhome.net;


import net.akashaverse.pixelmonhome.PixelmonHome;
import net.akashaverse.pixelmonhome.net.payload.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.network.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;


public final class NetworkInit {
    private NetworkInit() {}


    @SubscribeEvent
    public static void register(final RegisterPayloadHandlerEvent e) {
        final PayloadRegistrar reg = e.registrar(PixelmonHome.MOD_ID).versioned("1");
        IPayloadHandler<DepositRequest> depHandler = (pkt, ctx) -> ctx.workHandler().execute(() -> DepositRequest.handle(pkt, ctx));
        IPayloadHandler<WithdrawRequest> witHandler = (pkt, ctx) -> ctx.workHandler().execute(() -> WithdrawRequest.handle(pkt, ctx));
        IPayloadHandler<DepositAck> depAckHandler = (pkt, ctx) -> {};
        IPayloadHandler<WithdrawAck> witAckHandler = (pkt, ctx) -> {};


        reg.playToServer(DepositRequest.TYPE, DepositRequest.STREAM_CODEC, depHandler);
        reg.playToServer(WithdrawRequest.TYPE, WithdrawRequest.STREAM_CODEC, witHandler);
        reg.playToClient(DepositAck.TYPE, DepositAck.STREAM_CODEC, depAckHandler);
        reg.playToClient(WithdrawAck.TYPE, WithdrawAck.STREAM_CODEC, witAckHandler);
    }
}