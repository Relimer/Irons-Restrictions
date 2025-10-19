package com.relimer.ironsrestrictions.player;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.network.RarityData;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = IronsRestrictions.MODID)
public class PlayerRarityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<RarityData> SYNCED_RARITY = CapabilityManager.get(new CapabilityToken<>() {
    });

    private RarityData playerRarityData = null;
    private LazyOptional<RarityData> optional = LazyOptional.of(this::createPlayerMagicData);
    private ServerPlayer serverPlayer;

    public PlayerRarityProvider(ServerPlayer serverPlayer) {
        this.serverPlayer = serverPlayer;
    }

    @Nonnull
    private RarityData createPlayerMagicData() {
        if (playerRarityData == null) {
            playerRarityData = new RarityData(serverPlayer);
        }
        return playerRarityData;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == SYNCED_RARITY) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        return getCapability(cap);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerMagicData().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerMagicData().loadNBTData(nbt);
    }
}
