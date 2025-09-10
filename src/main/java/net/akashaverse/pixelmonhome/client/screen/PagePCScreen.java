package net.akashaverse.pixelmonhome.client.screen;


import net.akashaverse.pixelmonhome.client.atlas.UIAtlas;
import net.akashaverse.pixelmonhome.client.widget.ImageRegionButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;


public class PagePCScreen extends Screen {
    private final Screen parent; private int left, top;
    public PagePCScreen(Screen parent) { super(Component.literal("PC")); this.parent = parent; }
    @Override protected void init(){ left=(this.width-340)/2; top=(this.height-220)/2; addRenderableWidget(new ImageRegionButton(left+8, top+8, 24,24, "icon_back", b-> Minecraft.getInstance().setScreen(parent))); }
    @Override public void render(GuiGraphics g,int mouseX,int mouseY,float p){ renderBackground(g); var bg=UIAtlas.get("panel_main"); if(bg!=null) g.blit(UIAtlas.TEXTURE,left,top,340,220,bg.u(),bg.v(),bg.w(),bg.h(),UIAtlas.texW(),UIAtlas.texH()); super.render(g,mouseX,mouseY,p); g.drawString(font,"PC page (mock)", left+40, top+16, 0xFFFFFF);}
    @Override public boolean isPauseScreen(){ return false; }
}