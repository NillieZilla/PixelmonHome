package net.akashaverse.pixelmonhome.client;


import net.akashaverse.pixelmonhome.client.atlas.UIAtlasReloader;
import net.akashaverse.pixelmonhome.client.key.Keybinds;
import net.akashaverse.pixelmonhome.client.screen.HomeRootScreen;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.TickEvent;


public final class ClientInit {
    private ClientInit() {}


    public static void attach(IEventBus modBus) {
        modBus.addListener(ClientInit::onRegisterKeyMappings);
        modBus.addListener(ClientInit::onRegisterReloaders);
        NeoForge.EVENT_BUS.addListener(ClientInit::onClientTick);
    }


    private static void onRegisterKeyMappings(RegisterKeyMappingsEvent e) { Keybinds.register(e); }
    private static void onRegisterReloaders(RegisterClientReloadListenersEvent e) { e.registerReloadListener(new UIAtlasReloader()); }
    private static void onClientTick(TickEvent.ClientTickEvent e) {
        if (e.phase != TickEvent.Phase.END) return;
        if (Minecraft.getInstance().player == null) return;
        if (Keybinds.OPEN_HOME != null && Keybinds.OPEN_HOME.consumeClick()) {
            Minecraft.getInstance().setScreen(new HomeRootScreen());
        }
    }
}