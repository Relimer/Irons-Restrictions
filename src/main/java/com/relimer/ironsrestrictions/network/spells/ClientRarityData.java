package com.relimer.ironsrestrictions.network.spells;

import com.relimer.ironsrestrictions.network.RarityData;
import io.redspace.ironsspellbooks.api.entity.IMagicEntity;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.capabilities.magic.SyncedSpellData;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;

public class ClientRarityData {
    /**
     * Current Player's Synced Data
     */
    private static final RarityData playerRarityData = new RarityData();

    /**
     * Other Player's Synced Data
     */
    private static final HashMap<Integer, SyncedRarityData> playerSyncedDataLookup = new HashMap<>();
    private static final SyncedRarityData emptySyncedData = new SyncedRarityData(-999);


    public static SpellRarity getRarity() {
        return playerRarityData.getSyncedData().getRarity();
    }


    public static SyncedRarityData getSyncedRarityData(LivingEntity livingEntity) {
        if (livingEntity instanceof Player) {
            return playerSyncedDataLookup.getOrDefault(livingEntity.getId(), emptySyncedData);
        }

        return new SyncedRarityData(null);

    }

    public static void handlePlayerSyncedData(SyncedRarityData playerSyncedData) {
        playerSyncedDataLookup.put(playerSyncedData.getServerPlayerId(), playerSyncedData);
    }
}
