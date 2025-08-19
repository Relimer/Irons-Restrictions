package com.relimer.ironsrestrictions.player;

import com.relimer.ironsrestrictions.gui.*;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;


public class RClientSpellCastHelper {
    public static void openSchoolResearchScreen(InteractionHand hand, SchoolType schoolType, Item item, ResourceLocation window, ResourceLocation frame) {
        Minecraft.getInstance().setScreen(new SchoolResearchScreen(Component.empty(), hand, schoolType, item, window, frame));
    }
}
