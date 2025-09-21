package com.relimer.ironsrestrictions.player;

import com.relimer.ironsrestrictions.network.spells.SyncedRarityData;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerRarityProvider implements IAttachmentSerializer<CompoundTag, SyncedRarityData> {

    @Override
    public @NotNull SyncedRarityData read(IAttachmentHolder holder, CompoundTag tag, HolderLookup.Provider provider) {
        SyncedRarityData data = new SyncedRarityData((ServerPlayer) holder);
        data.loadNBTData(tag, provider);
        return data;
    }

    @Override
    public @Nullable CompoundTag write(SyncedRarityData attachment, HolderLookup.Provider provider) {
        CompoundTag compound = new CompoundTag();
        attachment.saveNBTData(compound, provider);
        return compound;
    }
}
