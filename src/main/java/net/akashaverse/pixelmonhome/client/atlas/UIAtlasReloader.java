package net.akashaverse.pixelmonhome.client.atlas;


import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;


public class UIAtlasReloader extends SimplePreparableReloadListener<Void> {
    @Override protected Void prepare(ResourceManager mgr, ProfilerFiller profiler) { return null; }
    @Override protected void apply(Void v, ResourceManager mgr, ProfilerFiller profiler) { UIAtlas.load(mgr); }
}