package com.relimer.ironsrestrictions.network;

import com.relimer.ironsrestrictions.network.spells.SyncedRarityData;
import com.relimer.ironsrestrictions.player.PlayerRarityProvider;
import com.relimer.ironsrestrictions.util.ConfigurableRarity;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;

public class RarityData {
    private ServerPlayer serverPlayer = null;
    private SpellRarity rarity;

    public RarityData(ServerPlayer serverPlayer) {
        this.serverPlayer = serverPlayer;
    }
    public RarityData() {
    }

    public void setServerPlayer(ServerPlayer serverPlayer) {
        if (this.serverPlayer == null && serverPlayer != null) {
            this.serverPlayer = serverPlayer;
        }
    }

    private SyncedRarityData syncedRarityData;

    public SyncedRarityData getSyncedData() {
        if (syncedRarityData == null) {
            syncedRarityData = new SyncedRarityData(serverPlayer);
        }

        return syncedRarityData;
    }

    public void setSyncedData(SyncedRarityData syncedRarityData) {
        this.syncedRarityData = syncedRarityData;
    }

    public static RarityData getPlayerRarityData(LivingEntity livingEntity) {
       if (livingEntity instanceof ServerPlayer serverPlayer) {
            var capContainer = serverPlayer.getCapability(PlayerRarityProvider.SYNCED_RARITY);
            if (capContainer.isPresent()) {
                var opt = capContainer.resolve();
                if (opt.isEmpty()) {
                    return new RarityData(serverPlayer);
                }

                var pmd = opt.get();
                pmd.setServerPlayer(serverPlayer);
                return pmd;
            }
            return new RarityData(serverPlayer);
        }
        throw new IllegalArgumentException("Invalid Entity");
    }

    public void saveNBTData(CompoundTag compound) {
        compound.putString("spell_rarity", this.rarity != null ? this.rarity.name() : "NONE");

        getSyncedData().saveNBTData(compound);
    }

    public void loadNBTData(CompoundTag compound) {
        this.rarity = ConfigurableRarity.valueOf(compound.getString("spell_rarity")).getSpellRarity();

        getSyncedData().loadNBTData(compound);
    }
}
