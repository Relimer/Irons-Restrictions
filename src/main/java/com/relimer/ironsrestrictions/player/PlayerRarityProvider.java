package com.relimer.ironsrestrictions.player;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.network.spells.SyncedRarityData;
import com.relimer.ironsrestrictions.registries.DataAttachmentRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@EventBusSubscriber(modid = IronsRestrictions.MODID)
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
    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (!event.isWasDeath()) return;

        if (event.getEntity() instanceof ServerPlayer newPlayer) {
            var oldRarity = event.getOriginal().getData(DataAttachmentRegistry.RARITY_DATA);
            var newRarity = newPlayer.getData(DataAttachmentRegistry.RARITY_DATA);

            newRarity.copyFrom(oldRarity);
        }
    }
}
