package com.relimer.ironsrestrictions.player;

import com.relimer.ironsrestrictions.Config;
import com.relimer.ironsrestrictions.IronsRestrictions;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.List;

@EventBusSubscriber(modid = IronsRestrictions.MODID)
public class PlayerJoinHandler {
    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        if (player.level().isClientSide()) return;
        MagicData magicData = MagicData.getPlayerMagicData(player);
        List<? extends String> spellIds = Config.DefaultLearntSpells.get();
        for (String spellId : spellIds) {
            String namespace = spellId.split(":")[0];
            String path = spellId.split(":")[1];
            try {
                ResourceLocation id = ResourceLocation.fromNamespaceAndPath(namespace, path);
                AbstractSpell spell = SpellRegistry.getSpell(id);
                if (spell != null && !magicData.getSyncedData().isSpellLearned(spell)) {
                    magicData.getSyncedData().learnSpell(spell);
                }
            } catch (Exception ignore) {
            }
        }
    }
}
