package com.relimer.ironsrestrictions.network.spells;

import com.relimer.ironsrestrictions.IronsRestrictions;
import io.redspace.ironsspellbooks.player.ClientMagicData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class SyncPlayerRarityDataPacket implements CustomPacketPayload {
    SyncedRarityData syncedSpellData;
    public static final CustomPacketPayload.Type<SyncPlayerRarityDataPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "sync_player_data"));
    public static final StreamCodec<RegistryFriendlyByteBuf, SyncPlayerRarityDataPacket> STREAM_CODEC = CustomPacketPayload.codec(SyncPlayerRarityDataPacket::write, SyncPlayerRarityDataPacket::new);

    public SyncPlayerRarityDataPacket(SyncedRarityData playerSyncedData) {
        this.syncedSpellData = playerSyncedData;
    }

    public SyncPlayerRarityDataPacket(FriendlyByteBuf buf) {
        syncedSpellData = SyncedRarityData.read(buf);
    }

    public void write(FriendlyByteBuf buf) {
        SyncedRarityData.write(buf, syncedSpellData);
    }

    public static void handle(SyncPlayerRarityDataPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            ClientRarityData.handlePlayerSyncedData(packet.syncedSpellData);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}