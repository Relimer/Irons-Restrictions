package com.relimer.ironsrestrictions.network;

import com.relimer.ironsrestrictions.anim.Animations;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClientPacketHandlers {
    public static void handlePlayAnimation(PlayAnimationPacket packet) {
        AbstractClientPlayer clientPlayer = Minecraft.getInstance().player;
        if (clientPlayer != null) {
            Animations.play(clientPlayer, packet.animation);
        }
    }
}
