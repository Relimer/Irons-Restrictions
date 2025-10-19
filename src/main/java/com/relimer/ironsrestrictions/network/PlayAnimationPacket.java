package com.relimer.ironsrestrictions.network;


import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PlayAnimationPacket {
    public final ResourceLocation animation;
    public PlayAnimationPacket(FriendlyByteBuf buf) {
        this.animation = buf.readResourceLocation();
    }
    public PlayAnimationPacket(ResourceLocation animation) {
        this.animation = animation;
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeResourceLocation(this.animation);
    }

    public static boolean handle(PlayAnimationPacket packet, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();

        context.enqueueWork(() -> {
            ClientPacketHandlers.handlePlayAnimation(packet);
        });

        return true;
    }
}
