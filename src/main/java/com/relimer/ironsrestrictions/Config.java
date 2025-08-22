package com.relimer.ironsrestrictions;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;


public class Config {

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec.BooleanValue TintBasePage = BUILDER
            .comment("For untextured items, should the generated texture tint the base layer")
            .define("tintBasePage", true);
    public static final ModConfigSpec.BooleanValue TintOverlayPage = BUILDER
            .comment("For untextured items, should the generated texture tint the overlay layer")
            .define("tintOverlayPage", true);
    public static final ModConfigSpec.ConfigValue<List<? extends String>> DefaultLearntSpells = BUILDER
            .comment("A list of spells that the player will already know")
            .defineList("defaultLearntSpells", List.of(
                    "irons_spellbooks:firebolt",
                    "irons_spellbooks:icicle"), value -> value instanceof String);

    public static final ModConfigSpec.ConfigValue<List<? extends String>> ExcludeRandomLearntSpells = BUILDER
            .comment("A list of spells that will not be able to be learnt through the Unfinished Manuscript")
            .defineList("excludeRandomLearntSpells", List.of(
                    "irons_spellbooks:abyssal_shroud",
                    "irons_spellbooks:sculk_tentacles",
                    "irons_spellbooks:sonic_boom",
                    "irons_spellbooks:planar_sight",
                    "irons_spellbooks:telekinesis",
                    "irons_spellbooks:eldritch_blast",
                    "irons_spellbooks:pocket_dimension"), value -> value instanceof String);

    public static final ModConfigSpec SPEC = BUILDER.build();
}
