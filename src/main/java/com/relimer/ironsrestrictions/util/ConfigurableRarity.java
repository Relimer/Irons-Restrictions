package com.relimer.ironsrestrictions.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import org.jetbrains.annotations.Nullable;

public enum ConfigurableRarity {
    NONE(null),
    COMMON(SpellRarity.COMMON),
    UNCOMMON(SpellRarity.UNCOMMON),
    RARE(SpellRarity.RARE),
    EPIC(SpellRarity.EPIC),
    LEGENDARY(SpellRarity.LEGENDARY);

    private final SpellRarity spellRarity;

    ConfigurableRarity(SpellRarity spellRarity) {
        this.spellRarity = spellRarity;
    }

    @Nullable
    public SpellRarity getSpellRarity() {
        return spellRarity;
    }
}
