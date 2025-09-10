package net.akashaverse.pixelmonhome.config;


import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.fml.loading.FMLPaths;


public final class PHConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();


    public static final ModConfigSpec.BooleanValue ENABLE_CLOUD = BUILDER
            .comment("If true, use remote Home API; if false, use portable bundles only.")
            .define("cloud.enabled", true);


    public static final ModConfigSpec.ConfigValue<String> API_BASE = BUILDER
            .comment("Base URL for the Pixelmon Home backend (https://host:port)")
            .define("cloud.baseUrl", "http://localhost:8081");


    public static final ModConfigSpec.ConfigValue<String> SERVER_ID = BUILDER
            .comment("Unique ID for this server (used for anti-duplication and audit)")
            .define("cloud.serverId", "dev-local");


    public static final ModConfigSpec SPEC = BUILDER.build();


    private PHConfig() {}


    public static void register() {
// Loads config/pixelmonhome-common.toml
        net.neoforged.fml.config.ModConfigProvider.register("pixelmonhome", SPEC,
                FMLPaths.CONFIGDIR.get().resolve("pixelmonhome-common.toml"));
    }
}