package com.relimer.ironsrestrictions;


import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;


public class Config {

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.BooleanValue TintBasePage = BUILDER
            .comment("For untextured items, should the generated texture tint the base layer")
            .define("tintBasePage", true);
    public static final ForgeConfigSpec.BooleanValue TintOverlayPage = BUILDER
            .comment("For untextured items, should the generated texture tint the overlay layer")
            .define("tintOverlayPage", true);
    public static final ForgeConfigSpec.BooleanValue ImbuedItemsRequireLearning = BUILDER
            .comment("If true, to use a spell on an imbued item, the spell must be learnt")
            .define("imbuedItemsRequireLearning", false);
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> DefaultLearntSpells = BUILDER
            .comment("A list of spells that the player will already know")
            .defineList("defaultLearntSpells", List.of(
                    "irons_spellbooks:firebolt",
                    "irons_spellbooks:icicle"), value -> value instanceof String);

    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> ExcludeRandomLearntSpells = BUILDER
            .comment("A list of spells that will not be able to be learnt through the Unfinished Manuscript")
            .defineList("excludeRandomLearntSpells", List.of(
                    "irons_spellbooks:abyssal_shroud",
                    "irons_spellbooks:sculk_tentacles",
                    "irons_spellbooks:sonic_boom",
                    "irons_spellbooks:planar_sight",
                    "irons_spellbooks:telekinesis",
                    "irons_spellbooks:eldritch_blast",
                    "irons_spellbooks:pocket_dimension"), value -> value instanceof String);
    public static final ForgeConfigSpec.DoubleValue FailChance
            = BUILDER
            .comment("The chance for an Unfinished Manuscript to fail")
            .defineInRange("failChance", 0.3, 0.0, 1.0);

    public static final ForgeConfigSpec SPEC = BUILDER.build();
}
