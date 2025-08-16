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
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static io.redspace.ironsspellbooks.api.registry.SchoolRegistry.FIRE_RESOURCE;

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

    private static Supplier<SchoolType> registerSchool(SchoolType schoolType) {
        return SCHOOLS.register(schoolType.getId().getPath(), () -> schoolType);
    }
}
