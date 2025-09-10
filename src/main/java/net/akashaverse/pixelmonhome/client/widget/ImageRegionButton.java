package net.akashaverse.pixelmonhome.client.widget;


import net.akashaverse.pixelmonhome.client.atlas.UIAtlas;
import net.akashaverse.pixelmonhome.client.atlas.UIRegion;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;


public class ImageRegionButton extends Button {
    private final String regionName;
    public ImageRegionButton(int x, int y, int w, int h, String regionName, OnPress onPress) {
        super(x, y, w, h, Component.empty(), onPress, DEFAULT_NARRATION);
        this.regionName = regionName;
    }
    @Override public void renderWidget(GuiGraphics g, int mouseX, int mouseY, float partial) {
        UIRegion r = UIAtlas.get(regionName);
        if (r != null) {
            g.blit(UIAtlas.TEXTURE, getX(), getY(), getWidth(), getHeight(), r.u(), r.v(), r.w(), r.h(), UIAtlas.texW(), UIAtlas.texH());
        }
        super.renderWidget(g, mouseX, mouseY, partial);
    }
}