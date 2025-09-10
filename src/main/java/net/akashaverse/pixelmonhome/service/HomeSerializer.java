package net.akashaverse.pixelmonhome.service;


import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import net.minecraft.nbt.CompoundTag;


public final class HomeSerializer {
    private HomeSerializer() {}


    public static CompoundTag toNbt(Pokemon p) {
// 1.21 API retains writeToNBT on Pokemon; if not, adapt to current API.
        return p.writeToNBT(new CompoundTag());
    }


    public static void applyTo(Pokemon target, CompoundTag nbt) {
        target.readFromNBT(nbt);
    }
}