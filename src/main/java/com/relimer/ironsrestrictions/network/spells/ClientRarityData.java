package com.relimer.ironsrestrictions.network.spells;

import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import net.minecraft.client.Minecraft;

public class ClientRarityData {

    // The synced rarity for the local player
    private static SpellRarity currentRarity = SpellRarity.COMMON; // Default

    public static SpellRarity getCurrentRarity() {
        return currentRarity;
    }

    public static void setCurrentRarity(SpellRarity rarity) {
        currentRarity = rarity;
    }

    // Called from your packet handler
    public static void handlePlayerSyncedData(SyncedRarityData data) {
        if (Minecraft.getInstance().player == null) {
            return;
        }

        if (Minecraft.getInstance().player.getId() == data.getServerPlayerId()) {
            setCurrentRarity(data.getRarity());
        }
    }
}
