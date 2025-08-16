package com.relimer.ironsrestrictions.network;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.player.ClientSpellCastHelperMixin;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class OpenHolyScreenPacket implements CustomPacketPayload {
    private final InteractionHand hand;
    public static final Type<OpenHolyScreenPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "open_holy_screen"));
    public static final StreamCodec<RegistryFriendlyByteBuf, OpenHolyScreenPacket> STREAM_CODEC = CustomPacketPayload.codec(OpenHolyScreenPacket::write, OpenHolyScreenPacket::new);

    public OpenHolyScreenPacket(InteractionHand pHand) {
        this.hand = pHand;
    }

    public OpenHolyScreenPacket(FriendlyByteBuf buf) {
        this.hand = buf.readEnum(InteractionHand.class);
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeEnum(this.hand);
    }

    public static void handle(OpenHolyScreenPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            ClientSpellCastHelperMixin.openHolyResearchScreen(packet.hand);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
