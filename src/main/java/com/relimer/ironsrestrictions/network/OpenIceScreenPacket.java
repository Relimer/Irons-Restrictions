package com.relimer.ironsrestrictions.network;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.player.RClientSpellCastHelper;
import com.relimer.ironsrestrictions.registries.ItemRegistry;
import com.relimer.ironsrestrictions.registries.RSchoolRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class OpenIceScreenPacket implements CustomPacketPayload {
    private final InteractionHand hand;
    public static final Type<OpenIceScreenPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "open_ice_screen"));
    public static final StreamCodec<RegistryFriendlyByteBuf, OpenIceScreenPacket> STREAM_CODEC = CustomPacketPayload.codec(OpenIceScreenPacket::write, OpenIceScreenPacket::new);

    public OpenIceScreenPacket(InteractionHand pHand) {
        this.hand = pHand;
    }

    public OpenIceScreenPacket(FriendlyByteBuf buf) {
        this.hand = buf.readEnum(InteractionHand.class);
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeEnum(this.hand);
    }

    public static void handle(OpenIceScreenPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            RClientSpellCastHelper.openSchoolResearchScreen(packet.hand, RSchoolRegistry.ICE.get(), ItemRegistry.ICE_PAGE.get(),
                    ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "textures/gui/research_screen/ice_window.png"),
                    ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "textures/gui/research_screen/ice_frame.png"));
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
