package com.relimer.ironsrestrictions.player;

import com.relimer.ironsrestrictions.gui.*;
import com.relimer.ironsrestrictions.registries.ItemRegistry;
import com.relimer.ironsrestrictions.registries.RSchoolRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;

public class RClientSpellCastHelper {
    public static void openFireResearchScreen(InteractionHand hand) {
        Minecraft.getInstance().setScreen(new SchoolResearchScreen(Component.empty(), hand, RSchoolRegistry.FIRE.get(), ItemRegistry.FIRE_PAGE.get()));
    }
    public static void openNatureResearchScreen(InteractionHand hand) {
        Minecraft.getInstance().setScreen(new SchoolResearchScreen(Component.empty(), hand, RSchoolRegistry.NATURE.get(), ItemRegistry.NATURE_PAGE.get()));
    }
    public static void openEvocationResearchScreen(InteractionHand hand) {
        Minecraft.getInstance().setScreen(new SchoolResearchScreen(Component.empty(), hand, RSchoolRegistry.EVOCATION.get(), ItemRegistry.EVOCATION_PAGE.get()));
    }
    public static void openEnderResearchScreen(InteractionHand hand) {
        Minecraft.getInstance().setScreen(new SchoolResearchScreen(Component.empty(), hand, RSchoolRegistry.ENDER.get(), ItemRegistry.ENDER_PAGE.get()));
    }
    public static void openIceResearchScreen(InteractionHand hand) {
        Minecraft.getInstance().setScreen(new SchoolResearchScreen(Component.empty(), hand, RSchoolRegistry.ICE.get(), ItemRegistry.ICE_PAGE.get()));
    }
    public static void openHolyResearchScreen(InteractionHand hand) {
        Minecraft.getInstance().setScreen(new SchoolResearchScreen(Component.empty(), hand, RSchoolRegistry.HOLY.get(), ItemRegistry.HOLY_PAGE.get()));
    }
    public static void openBloodResearchScreen(InteractionHand hand) {
        Minecraft.getInstance().setScreen(new SchoolResearchScreen(Component.empty(), hand, RSchoolRegistry.BLOOD.get(), ItemRegistry.BLOOD_PAGE.get()));
    }
    public static void openLightningResearchScreen(InteractionHand hand) {
        Minecraft.getInstance().setScreen(new SchoolResearchScreen(Component.empty(), hand, RSchoolRegistry.LIGHTNING.get(), ItemRegistry.LIGHTNING_PAGE.get()));
    }
}
