package net.akashaverse.pixelmonhome.client.atlas;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;


import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class UIAtlas {
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("pixelmonhome", "textures/gui/home/home_atlas.png");
    public static final ResourceLocation JSON = ResourceLocation.fromNamespaceAndPath("pixelmonhome", "ui/home_atlas.json");


    private static final Gson GSON = new Gson();
    private static final Type LIST_TYPE = new TypeToken<List<UIRegion>>(){}.getType();


    private static Map<String, UIRegion> regions = new HashMap<>();
    private static int texW = 1024, texH = 1024; // known from generation


    public static void load(ResourceManager mgr) {
        regions.clear();
        for (Resource res : mgr.getResourceStack(JSON)) {
            try (var r = new InputStreamReader(res.open())) {
                List<UIRegion> list = GSON.fromJson(r, LIST_TYPE);
                for (UIRegion reg : list) regions.put(reg.name(), reg);
                break;
            } catch (Exception ignored) {}
        }
    }


    public static UIRegion get(String name) { return regions.get(name); }
    public static int texW() { return texW; }
    public static int texH() { return texH; }
}