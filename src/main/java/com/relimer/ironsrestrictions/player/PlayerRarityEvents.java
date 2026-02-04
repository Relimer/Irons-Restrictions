package com.relimer.ironsrestrictions.player;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.network.RarityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = IronsRestrictions.MODID)
public class PlayerRarityEvents {
    private static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "spell_rarity");

    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof ServerPlayer serverPlayer && !event.getCapabilities().containsKey(ID)) {
            event.addCapability(ID, new PlayerRarityProvider(serverPlayer));
        }
    }

    @SubscribeEvent
    public static void clone(PlayerEvent.Clone event) {
        if (event.getEntity() instanceof ServerPlayer newServerPlayer) {
            event.getOriginal().reviveCaps();
            RarityData oldMagicData = RarityData.getPlayerRarityData(event.getOriginal());
            RarityData newMagicData = RarityData.getPlayerRarityData(event.getEntity());
            newMagicData.setSyncedData(oldMagicData.getSyncedData().getPersistentData());
            newMagicData.getSyncedData().doSync();
            event.getOriginal().invalidateCaps();
        }
    }
}
