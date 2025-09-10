package net.akashaverse.pixelmonhome.client.key;


import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;


public final class Keybinds {
    private Keybinds() {}
    public static KeyMapping OPEN_HOME;
    public static void register(RegisterKeyMappingsEvent e) {
        OPEN_HOME = new KeyMapping("key.pixelmonhome.open", GLFW.GLFW_KEY_H, "key.categories.pixelmonhome");
        e.register(OPEN_HOME);
    }
}