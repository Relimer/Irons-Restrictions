package com.relimer.ironsrestrictions.network.spells;

import io.redspace.ironsspellbooks.capabilities.magic.SyncedSpellData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncPlayerRarityDataPacket {
    private final SyncedRarityData syncedRarityData;
    public SyncPlayerRarityDataPacket(SyncedRarityData playerSyncedData) {
        this.syncedRarityData = playerSyncedData;
    }

    public SyncPlayerRarityDataPacket(FriendlyByteBuf buf) {
        syncedRarityData = SyncedRarityData.SYNCED_RARITY_DATA.read(buf);
    }

    public void toBytes(FriendlyByteBuf buf) {
        SyncedRarityData.SYNCED_RARITY_DATA.write(buf, syncedRarityData);
    }


    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientRarityData.handlePlayerSyncedData(syncedRarityData);
        });
        context.setPacketHandled(true);
        return true;
    }
}