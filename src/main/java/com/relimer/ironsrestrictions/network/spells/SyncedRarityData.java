package com.relimer.ironsrestrictions.network.spells;

import com.relimer.ironsrestrictions.Config;
import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.setup.Messages;
import com.relimer.ironsrestrictions.util.ConfigurableRarity;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.capabilities.magic.SyncedSpellData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

public class SyncedRarityData {
    private final int serverPlayerId;
    private @Nullable LivingEntity livingEntity;


    private SpellRarity rarity;

    private SyncedRarityData syncedRarityData;

    //client
    public SyncedRarityData(int serverPlayerId) {
        this.serverPlayerId = serverPlayerId;
        this.livingEntity = null;
        this.rarity = Config.StartingRarity.get().getSpellRarity();
    }

    //server
    public SyncedRarityData(LivingEntity livingEntity) {
        this(livingEntity == null ? -1 : livingEntity.getId());
        this.livingEntity = livingEntity;
    }

    public static final EntityDataSerializer<SyncedRarityData> SYNCED_RARITY_DATA = new EntityDataSerializer.ForValueType<SyncedRarityData>() {
        public void write(FriendlyByteBuf buffer, SyncedRarityData data) {
            buffer.writeInt(data.serverPlayerId);
            buffer.writeBoolean(data.rarity != null);
            if (data.rarity != null) {
                buffer.writeEnum(data.rarity);
            }
        }

        public SyncedRarityData read(FriendlyByteBuf buffer) {
            var data = new SyncedRarityData(buffer.readInt());
            if (buffer.readBoolean()) {
                data.rarity = buffer.readEnum(SpellRarity.class);
            } else {
                data.rarity = Config.StartingRarity.get().getSpellRarity();
            }
            return data;
        }
    };

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
            Messages.sendToPlayer(new SyncPlayerRarityDataPacket(this), serverPlayer);
            Messages.sendToPlayersTrackingEntity(new SyncPlayerRarityDataPacket(this), serverPlayer);
        }
    }
    public void copyFrom(SyncedRarityData other) {
        setRarity(other.getRarity());
    }

    public int getServerPlayerId() {
        return serverPlayerId;
    }

    public SyncedRarityData getPersistentData() {
        SyncedRarityData persistentData = new SyncedRarityData(this.livingEntity);
        persistentData.rarity = this.rarity;
        return persistentData;
    }
    public void saveNBTData(CompoundTag compound) {
        compound.putString("spell_rarity", this.rarity.toString());
    }
    public void loadNBTData(CompoundTag compound) {
        this.rarity = SpellRarity.valueOf(compound.getString("spell_rarity"));
    }

}
