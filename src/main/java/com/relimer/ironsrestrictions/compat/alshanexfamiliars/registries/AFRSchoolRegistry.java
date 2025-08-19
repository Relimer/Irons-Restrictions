package com.relimer.ironsrestrictions.compat.alshanexfamiliars.registries;

import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.util.ModTags;
import net.alshanex.alshanex_familiars.datagen.AFDamageTypes;
import net.alshanex.alshanex_familiars.datagen.ItemTagProvider;
import net.alshanex.alshanex_familiars.registry.AttributeRegistry;
import net.minecraft.network.chat.Component;

import java.util.function.Supplier;

import static com.relimer.ironsrestrictions.registries.RSchoolRegistry.registerSchool;
import static net.alshanex.alshanex_familiars.registry.AFSchoolRegistry.SOUND_RESOURCE;


public class AFRSchoolRegistry {
    public static Supplier<SchoolType> SOUND;

    public static void register() {
        SOUND = registerSchool(new SchoolType(
                SOUND_RESOURCE,
                ItemTagProvider.SOUND_FOCUS,
                Component.translatable("school.alshanex_familiars.sound").withColor(0xCFFFD2),
                AttributeRegistry.SOUND_SPELL_POWER,
                AttributeRegistry.SOUND_MAGIC_RESIST,
                SoundRegistry.GUST_CAST,
                AFDamageTypes.SOUND_MAGIC,
                true,
                false
        ));
    }
}
