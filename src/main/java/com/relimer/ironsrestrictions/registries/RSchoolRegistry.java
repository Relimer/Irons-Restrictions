package com.relimer.ironsrestrictions.registries;

import com.relimer.ironsrestrictions.IronsRestrictions;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.util.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static io.redspace.ironsspellbooks.api.registry.SchoolRegistry.*;

public class RSchoolRegistry {
    public static final DeferredRegister<SchoolType> SCHOOLS = DeferredRegister.create(SchoolRegistry.SCHOOL_REGISTRY_KEY, IronsRestrictions.MODID);

    public static final Supplier<SchoolType> FIRE = registerSchool(new SchoolType(
            FIRE_RESOURCE,
            ModTags.FIRE_FOCUS,
            Component.translatable("school.irons_spellbooks.fire").withStyle(ChatFormatting.GOLD),
            AttributeRegistry.FIRE_SPELL_POWER,
            AttributeRegistry.FIRE_MAGIC_RESIST,
            SoundRegistry.FIRE_CAST,
            ISSDamageTypes.FIRE_MAGIC,
            true,
            false
    ));
    public static final Supplier<SchoolType> NATURE = registerSchool(new SchoolType(
            NATURE_RESOURCE,
            ModTags.NATURE_FOCUS,
            Component.translatable("school.irons_spellbooks.nature").withStyle(ChatFormatting.GREEN),
            AttributeRegistry.NATURE_SPELL_POWER,
            AttributeRegistry.NATURE_MAGIC_RESIST,
            SoundRegistry.NATURE_CAST,
            ISSDamageTypes.NATURE_MAGIC,
            true,
            false
    ));
    public static final Supplier<SchoolType> EVOCATION = registerSchool(new SchoolType(
            EVOCATION_RESOURCE,
            ModTags.EVOCATION_FOCUS,
            Component.translatable("school.irons_spellbooks.evocation").withStyle(ChatFormatting.WHITE),
            AttributeRegistry.EVOCATION_SPELL_POWER,
            AttributeRegistry.EVOCATION_MAGIC_RESIST,
            SoundRegistry.EVOCATION_CAST,
            ISSDamageTypes.EVOCATION_MAGIC,
            true,
            false
    ));
    public static final Supplier<SchoolType> ENDER = registerSchool(new SchoolType(
            ENDER_RESOURCE,
            ModTags.ENDER_FOCUS,
            Component.translatable("school.irons_spellbooks.ender").withStyle(ChatFormatting.LIGHT_PURPLE),
            AttributeRegistry.ENDER_SPELL_POWER,
            AttributeRegistry.ENDER_MAGIC_RESIST,
            SoundRegistry.ENDER_CAST,
            ISSDamageTypes.ENDER_MAGIC,
            true,
            false
    ));
    public static final Supplier<SchoolType> ICE = registerSchool(new SchoolType(
            ICE_RESOURCE,
            ModTags.ICE_FOCUS,
            Component.translatable("school.irons_spellbooks.ice").withStyle(Style.EMPTY.withColor(0xd0f9ff)),
            AttributeRegistry.ICE_SPELL_POWER,
            AttributeRegistry.ICE_MAGIC_RESIST,
            SoundRegistry.ICE_CAST,
            ISSDamageTypes.ICE_MAGIC,
            true,
            false
    ));
    public static final Supplier<SchoolType> HOLY = registerSchool(new SchoolType(
            HOLY_RESOURCE,
            ModTags.HOLY_FOCUS,
            Component.translatable("school.irons_spellbooks.holy").withStyle(Style.EMPTY.withColor(0xfff8d4)),
            AttributeRegistry.HOLY_SPELL_POWER,
            AttributeRegistry.HOLY_MAGIC_RESIST,
            SoundRegistry.HOLY_CAST,
            ISSDamageTypes.HOLY_MAGIC,
            true,
            false
    ));
    public static final Supplier<SchoolType> BLOOD = registerSchool(new SchoolType(
            BLOOD_RESOURCE,
            ModTags.BLOOD_FOCUS,
            Component.translatable("school.irons_spellbooks.blood").withStyle(ChatFormatting.DARK_RED),
            AttributeRegistry.BLOOD_SPELL_POWER,
            AttributeRegistry.BLOOD_MAGIC_RESIST,
            SoundRegistry.BLOOD_CAST,
            ISSDamageTypes.BLOOD_MAGIC,
            true,
            false
    ));
    public static final Supplier<SchoolType> LIGHTNING = registerSchool(new SchoolType(
            LIGHTNING_RESOURCE,
            ModTags.LIGHTNING_FOCUS,
            Component.translatable("school.irons_spellbooks.lightning").withStyle(ChatFormatting.AQUA),
            AttributeRegistry.LIGHTNING_SPELL_POWER,
            AttributeRegistry.LIGHTNING_MAGIC_RESIST,
            SoundRegistry.LIGHTNING_CAST,
            ISSDamageTypes.LIGHTNING_MAGIC,
            true,
            false
    ));

    public static Supplier<SchoolType> registerSchool(SchoolType schoolType) {
        return SCHOOLS.register(schoolType.getId().getPath(), () -> schoolType);
    }
}
