package net.akashaverse.pixelmonhome.service;


import com.pixelmonmod.pixelmon.api.storage.PCStorage;
import com.pixelmonmod.pixelmon.api.storage.PlayerPartyStorage;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import net.akashaverse.pixelmonhome.util.PHLogger;
import net.minecraft.server.level.ServerPlayer;


import java.util.Optional;


public final class HomeOps {
    private HomeOps() {}


    public static void handleDeposit(ServerPlayer sp, int slotIndex, boolean fromParty) {
        var storageMgr = StorageProxy.getStorageManager();
        Optional<PlayerPartyStorage> party = Optional.ofNullable(storageMgr.getPartyNow(sp.getUUID()));
        Optional<PCStorage> pc = Optional.ofNullable(storageMgr.getPCForPlayerNow(sp.getUUID()));
        Pokemon mon = null;
        if (fromParty && party.isPresent()) mon = party.get().get(slotIndex);
        if (!fromParty && pc.isPresent()) mon = pc.get().getBox(pc.get().getBoxNumber()).get(slotIndex); // placeholder index logic
        if (mon == null) { PHLogger.LOG.warn("No Pokémon at requested slot"); return; }


        var nbt = HomeSerializer.toNbt(mon);
        var encoded = HomeStorageSaveAdapter.encodeNbt(nbt);
        var res = HomeApiClient.deposit(sp.getUUID().toString(), net.akashaverse.pixelmonhome.config.PHConfig.SERVER_ID.get(), encoded);
        if (res != null) {
// Remove from local storage to prevent duplication (atomicity TODO: transaction/rollback)
            if (fromParty) party.get().set(slotIndex, null);
            else pc.get().getBox(pc.get().getBoxNumber()).set(slotIndex, null);
        }
    }


    public static void handleWithdraw(ServerPlayer sp, String entryId, boolean toParty) {
        var payload = HomeApiClient.withdraw(sp.getUUID().toString(), entryId);
        if (payload == null) return;
// TODO: parse payload -> CompoundTag -> Pokemon instance and insert into party/pc
    }
}