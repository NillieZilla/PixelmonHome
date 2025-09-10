package net.akashaverse.pixelmonhome.service;


import com.pixelmonmod.pixelmon.api.storage.*;
import net.akashaverse.pixelmonhome.config.PHConfig;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;


import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;


/**
 * Prototype adapter that mirrors saves to a remote backend. We DO NOT replace normal party/pc saves;
 * instead this demonstrates how a Home storage could be persisted if you register a custom storage.
 */
public class HomeStorageSaveAdapter implements StorageSaveAdapter {
    @Override
    public void save(PokemonStorage storage, HolderLookup.Provider registryAccess) {
        if (!PHConfig.ENABLE_CLOUD.get()) return;
// Example: serialize a single Pokémon if this were a PC/Party; here just a no-op.
    }


    @Override
    public <T extends PokemonStorage> CompletableFuture<T> load(UUID uuid, Class<T> clazz, HolderLookup.Provider registryAccess) {
// For Home, we typically don't override PC/Party. Return CompletableFuture.completedFuture(null) is illegal.
        return CompletableFuture.failedFuture(new UnsupportedOperationException("Pixelmon Home does not override default storages"));
    }


    public static String encodeNbt(CompoundTag tag) {
        return Base64.getEncoder().encodeToString(tag.toString().getBytes(StandardCharsets.UTF_8));
    }
}