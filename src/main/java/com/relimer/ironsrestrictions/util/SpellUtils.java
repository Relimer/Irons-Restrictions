package com.relimer.ironsrestrictions.util;

import com.relimer.ironsrestrictions.Config;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class SpellUtils {
    public static List<AbstractSpell> getLearnableSpells() {
        var spells = new ArrayList<>(SpellRegistry.getEnabledSpells().stream().toList());
        List<? extends String> spellIds = Config.ExcludeLearntSpells.get();
        for (String spellId : spellIds) {
            String namespace = spellId.split(":")[0];
            String path = spellId.split(":")[1];
            try {
                ResourceLocation id = ResourceLocation.fromNamespaceAndPath(namespace, path);
                AbstractSpell spell = SpellRegistry.getSpell(id);
                if (spell != null) {
                    spells.remove(spell);
                }
            } catch (Exception ignore) {
            }
        }
        return spells;
    }
}
