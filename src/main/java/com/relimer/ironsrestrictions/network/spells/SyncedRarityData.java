package com.relimer.ironsrestrictions.network.spells;

import com.relimer.ironsrestrictions.Config;
import com.relimer.ironsrestrictions.util.ConfigurableRarity;
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
        this.rarity = Config.StartingRarity.get().getSpellRarity();
    }

    public SyncedRarityData() {
        this.serverPlayerId = -1;
        this.livingEntity = null;
        this.rarity = Config.StartingRarity.get().getSpellRarity();
    }

    public static void write(FriendlyByteBuf buffer, SyncedRarityData data) {
        buffer.writeInt(data.serverPlayerId);
        buffer.writeBoolean(data.rarity != null);
        if(data.rarity != null){
            buffer.writeEnum(data.rarity);
        }
    }
    public static SyncedRarityData read(FriendlyByteBuf buffer) {
        var data = new SyncedRarityData(buffer.readInt());
        if(buffer.readBoolean()) {
            data.rarity = buffer.readEnum(SpellRarity.class);
        }
        else {
            data.rarity = Config.StartingRarity.get().getSpellRarity();
        }
        return data;
    }
    public SyncedRarityData(LivingEntity livingEntity) {
        this(livingEntity == null ? -1 : livingEntity.getId());
        this.livingEntity = livingEntity;
    }
    public void saveNBTData(CompoundTag compound, HolderLookup.Provider provider) {
        compound.putString("spellRarity", this.rarity != null ? this.rarity.name() : "NONE");

    }
    public void loadNBTData(CompoundTag compound, HolderLookup.Provider provider) {
        this.rarity = ConfigurableRarity.valueOf(compound.getString("spellRarity")).getSpellRarity();
    }

    public SpellRarity getRarity() {
        return rarity;
    }

    public void setRarity(SpellRarity newRarity) {
        if (this.rarity != newRarity) {
            rarity = newRarity;
            doSync();
        }
    }
    public void doSync() {
        if (livingEntity instanceof ServerPlayer serverPlayer) {
            PacketDistributor.sendToPlayersTrackingEntityAndSelf(serverPlayer, new SyncPlayerRarityDataPacket(this));
        }
    }
    public void copyFrom(SyncedRarityData other) {
        setRarity(other.getRarity());
    }

    public int getServerPlayerId() {
        return serverPlayerId;
    }
}
