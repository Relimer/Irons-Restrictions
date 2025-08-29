package com.relimer.ironsrestrictions.player;

import com.relimer.ironsrestrictions.gui.*;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;


public class RClientSpellCastHelper {
    public static void openSchoolResearchScreen(InteractionHand hand, SchoolType schoolType) {
        Minecraft.getInstance().setScreen(new SchoolResearchScreen(Component.empty(), hand, schoolType));
    }
}
