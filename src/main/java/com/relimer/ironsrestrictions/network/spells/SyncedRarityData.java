package com.relimer.ironsrestrictions.network.spells;

import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;

public class SyncedRarityData {
    private final int serverPlayerId;
    private @Nullable LivingEntity livingEntity;

    private SpellRarity rarity;

    public SyncedRarityData(int serverPlayerId) {
        this.serverPlayerId = serverPlayerId;
        this.livingEntity = null;
        this.rarity = null;
    }
    public static void write(FriendlyByteBuf buffer, SyncedRarityData data) {
        buffer.writeInt(data.serverPlayerId);
        buffer.writeEnum(data.rarity);
    }
    public static SyncedRarityData read(FriendlyByteBuf buffer) {
        var data = new SyncedRarityData(buffer.readInt());
        data.rarity = buffer.readEnum(SpellRarity.class);
        return data;
    }
    public SyncedRarityData(LivingEntity livingEntity) {
        this(livingEntity == null ? -1 : livingEntity.getId());
        this.livingEntity = livingEntity;
    }
    public void saveNBTData(CompoundTag compound, HolderLookup.Provider provider) {
        compound.putString("spellRarity", rarity.name());

    }
    public void loadNBTData(CompoundTag compound, HolderLookup.Provider provider) {
        this.rarity = SpellRarity.valueOf(compound.getString("spellRarity"));
    }

    public SpellRarity getRarity() {
        return rarity;
    }

    public void setRarity(SpellRarity newRarity) {
        rarity = newRarity;
        doSync();
    }
    public void doSync() {
        if (livingEntity instanceof ServerPlayer serverPlayer) {
            PacketDistributor.sendToPlayersTrackingEntityAndSelf(serverPlayer, new SyncPlayerRarityDataPacket(this));
        }
    }
    public void syncToPlayer(ServerPlayer serverPlayer) {
        PacketDistributor.sendToPlayer(serverPlayer, new SyncPlayerRarityDataPacket(this));
    }

    public SyncedRarityData getPersistentData(ServerPlayer serverPlayer) {
        SyncedRarityData persistentData = new SyncedRarityData(livingEntity);
        persistentData.livingEntity = serverPlayer;
        persistentData.rarity = this.rarity;
        return persistentData;
    }

    public int getServerPlayerId() {
        return serverPlayerId;
    }
}
