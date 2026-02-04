package com.relimer.ironsrestrictions.util;

import com.relimer.ironsrestrictions.Config;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class SpellUtils {
    public static List<AbstractSpell> getIgnoredSpells() {
        List<? extends String> spellIds = Config.ExcludeLearntSpells.get();

        List<AbstractSpell> ignoredSpells = new ArrayList<>();
        for (String spellId : spellIds) {
            String namespace = spellId.split(":")[0];
            String path = spellId.split(":")[1];
            try {
                ResourceLocation id = ResourceLocation.fromNamespaceAndPath(namespace, path);
                AbstractSpell spell = SpellRegistry.getSpell(id);
                if (spell != null) {
                    ignoredSpells.add(spell);
                }
            } catch (Exception ignore) {
            }
        }
        return ignoredSpells;
    }
}
