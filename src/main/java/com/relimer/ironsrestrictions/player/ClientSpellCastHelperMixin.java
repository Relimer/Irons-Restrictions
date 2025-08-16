package com.relimer.ironsrestrictions.player;

import com.relimer.ironsrestrictions.gui.*;
import io.redspace.ironsspellbooks.player.ClientSpellCastHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ClientSpellCastHelper.class)
public class ClientSpellCastHelperMixin {
    public static void openFireResearchScreen(InteractionHand hand) {
        Minecraft.getInstance().setScreen(new FireResearchScreen(Component.empty(), hand));
    }
    public static void openNatureResearchScreen(InteractionHand hand) {
        Minecraft.getInstance().setScreen(new NatureResearchScreen(Component.empty(), hand));
    }
    public static void openEvocationResearchScreen(InteractionHand hand) {
        Minecraft.getInstance().setScreen(new EvocationResearchScreen(Component.empty(), hand));
    }
    public static void openEnderResearchScreen(InteractionHand hand) {
        Minecraft.getInstance().setScreen(new EnderResearchScreen(Component.empty(), hand));
    }
    public static void openIceResearchScreen(InteractionHand hand) {
        Minecraft.getInstance().setScreen(new IceResearchScreen(Component.empty(), hand));
    }
    public static void openHolyResearchScreen(InteractionHand hand) {
        Minecraft.getInstance().setScreen(new HolyResearchScreen(Component.empty(), hand));
    }
    public static void openBloodResearchScreen(InteractionHand hand) {
        Minecraft.getInstance().setScreen(new BloodResearchScreen(Component.empty(), hand));
    }
    public static void openLightningResearchScreen(InteractionHand hand) {
        Minecraft.getInstance().setScreen(new LightningResearchScreen(Component.empty(), hand));
    }
}
