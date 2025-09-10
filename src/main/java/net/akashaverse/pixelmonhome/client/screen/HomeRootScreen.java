package net.akashaverse.pixelmonhome.client.screen;


import net.akashaverse.pixelmonhome.client.atlas.UIAtlas;
import net.akashaverse.pixelmonhome.client.widget.ImageRegionButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;


public class HomeRootScreen extends Screen {
    private int left, top, widthPx = 340, heightPx = 220;
    public HomeRootScreen() { super(Component.translatable("screen.pixelmonhome.title")); }


    @Override protected void init() {
        left = (this.width - widthPx) / 2; top = (this.height - heightPx) / 2;
        addRenderableWidget(new ImageRegionButton(left + 8, top + 8, 32, 32, "icon_home", b -> {}));
        addRenderableWidget(new ImageRegionButton(left + 48, top + 8, 32, 32, "icon_party", b -> Minecraft.getInstance().setScreen(new PagePartyScreen(this))));
        addRenderableWidget(new ImageRegionButton(left + 88, top + 8, 32, 32, "icon_pc", b -> Minecraft.getInstance().setScreen(new PagePCScreen(this))));
        addRenderableWidget(new ImageRegionButton(left + 128, top + 8, 32, 32, "icon_cloud", b -> Minecraft.getInstance().setScreen(new PageCloudScreen(this))));
        addRenderableWidget(Button.builder(Component.translatable("screen.pixelmonhome.deposit"), b -> {})
                .bounds(left + 8, top + heightPx - 24, 110, 20).build());
        addRenderableWidget(Button.builder(Component.translatable("screen.pixelmonhome.withdraw"), b -> {})
                .bounds(left + 130, top + heightPx - 24, 110, 20).build());
        addRenderableWidget(Button.builder(Component.literal("Close"), b -> onClose())
                .bounds(left + 252, top + heightPx - 24, 80, 20).build());
    }


    @Override public void render(GuiGraphics g, int mouseX, int mouseY, float partial) {
        renderBackground(g);
        var bg = net.akashaverse.pixelmonhome.client.atlas.UIAtlas.get("panel_main");
        if (bg != null) {
            g.blit(UIAtlas.TEXTURE, left, top, widthPx, heightPx, bg.u(), bg.v(), bg.w(), bg.h(), UIAtlas.texW(), UIAtlas.texH());
        }
        super.render(g, mouseX, mouseY, partial);
        g.drawString(font, Component.literal("Pixelmon Home (UI mock)").getString(), left + 8, top + 50, 0xFFFFFF);
    }
    @Override public boolean isPauseScreen() { return false; }
}