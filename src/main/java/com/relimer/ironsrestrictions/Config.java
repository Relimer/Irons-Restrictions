package com.relimer.ironsrestrictions;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec.BooleanValue REPLACE_VANILLA_SCHOOLS = BUILDER
            .comment("If true, replaces Iron's Spellbooks default school assignments with Irons Restrictions equivalents (e.g., irons_spellbooks:fire → irons_restrictions:fire)")
            .define("replaceVanillaSchools", true);


    public static final ModConfigSpec SPEC = BUILDER.build();
}
