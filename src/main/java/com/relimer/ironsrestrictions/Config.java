package com.relimer.ironsrestrictions;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec.BooleanValue TintBasePage = BUILDER
            .comment("For untextured items, should the generated texture tint the base layer")
            .define("tintBasePage", true);
    public static final ModConfigSpec.BooleanValue TintOverlayPage = BUILDER
            .comment("For untextured items, should the generated texture tint the overlay layer")
            .define("tintOverlayPage", true);


    public static final ModConfigSpec SPEC = BUILDER.build();
}
