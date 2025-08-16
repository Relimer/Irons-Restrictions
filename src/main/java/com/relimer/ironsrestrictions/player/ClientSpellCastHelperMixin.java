package com.relimer.ironsrestrictions.player;

import com.relimer.ironsrestrictions.gui.FireResearchScreen;
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
}
