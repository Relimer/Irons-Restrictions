package com.relimer.ironsrestrictions.compat;

import net.kayn.fallen_gems_affixes.adventure.affix.SpellCastAffix;
import net.minecraft.world.entity.player.Player;

public class FallenGemsAffixSpellCastTrigger {
    public static boolean getter(Player player) {
        return SpellCastAffix.isCurrentlyTriggering(player);
    }
}
