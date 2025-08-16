package com.relimer.ironsrestrictions.network;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.player.RClientSpellCastHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class OpenEvocationScreenPacket implements CustomPacketPayload {
    private final InteractionHand hand;
    public static final Type<OpenEvocationScreenPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "open_evocatione_screen"));
    public static final StreamCodec<RegistryFriendlyByteBuf, OpenEvocationScreenPacket> STREAM_CODEC = CustomPacketPayload.codec(OpenEvocationScreenPacket::write, OpenEvocationScreenPacket::new);

    public OpenEvocationScreenPacket(InteractionHand pHand) {
        this.hand = pHand;
    }

    public OpenEvocationScreenPacket(FriendlyByteBuf buf) {
        this.hand = buf.readEnum(InteractionHand.class);
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeEnum(this.hand);
    }

    public static void handle(OpenEvocationScreenPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            RClientSpellCastHelper.openEvocationResearchScreen(packet.hand);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
