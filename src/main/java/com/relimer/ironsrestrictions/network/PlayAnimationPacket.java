package com.relimer.ironsrestrictions.network;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.anim.Animations;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class PlayAnimationPacket implements CustomPacketPayload{
    private static ResourceLocation animation;
    public static final CustomPacketPayload.Type<PlayAnimationPacket> TYPE = new PlayAnimationPacket.Type<>(ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "play_animation"));
    public static final StreamCodec<RegistryFriendlyByteBuf, PlayAnimationPacket> STREAM_CODEC = CustomPacketPayload.codec(PlayAnimationPacket::write, PlayAnimationPacket::new);

    public PlayAnimationPacket(FriendlyByteBuf buf) {
        this.animation = buf.readResourceLocation();
    }
    public PlayAnimationPacket(ResourceLocation animation) {
        this.animation = animation;
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeResourceLocation(this.animation);
    }
    public static void handle(PlayAnimationPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            AbstractClientPlayer clientPlayer = Minecraft.getInstance().player;
            if (clientPlayer != null) {
                Animations.play(clientPlayer, packet.animation);
            }
        });
    }

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
