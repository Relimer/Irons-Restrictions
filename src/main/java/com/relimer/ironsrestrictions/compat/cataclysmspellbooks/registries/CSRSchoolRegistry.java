package com.relimer.ironsrestrictions.compat.cataclysmspellbooks.registries;

import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.acetheeldritchking.cataclysm_spellbooks.registries.CSAttributeRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.acetheeldritchking.cataclysm_spellbooks.util.CSTags;
import net.minecraft.world.damagesource.DamageTypes;

import java.util.function.Supplier;

import static com.relimer.ironsrestrictions.registries.RSchoolRegistry.registerSchool;
import static net.acetheeldritchking.cataclysm_spellbooks.registries.CSSchoolRegistry.ABYSSAL_RESOURCE;


public class CSRSchoolRegistry {
    public static Supplier<SchoolType> ABYSSAL;

    public static void register() {
        ABYSSAL = registerSchool(new SchoolType(
                ABYSSAL_RESOURCE,
                CSTags.ABYSSAL_FOCUS,
                Component.translatable("school.cataclysm_spellbooks.abyssal").withStyle(Style.EMPTY.withColor(0x36156c)),
                CSAttributeRegistry.ABYSSAL_MAGIC_POWER,
                CSAttributeRegistry.ABYSSAL_MAGIC_RESIST,
                SoundRegistry.EVOCATION_CAST,
                DamageTypes.DROWN,
                true,
                false
        ));
    }
}
