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

public class OpenLightningScreenPacket implements CustomPacketPayload {
    private final InteractionHand hand;
    public static final Type<OpenLightningScreenPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "open_lightning_screen"));
    public static final StreamCodec<RegistryFriendlyByteBuf, OpenLightningScreenPacket> STREAM_CODEC = CustomPacketPayload.codec(OpenLightningScreenPacket::write, OpenLightningScreenPacket::new);

    public OpenLightningScreenPacket(InteractionHand pHand) {
        this.hand = pHand;
    }

    public OpenLightningScreenPacket(FriendlyByteBuf buf) {
        this.hand = buf.readEnum(InteractionHand.class);
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeEnum(this.hand);
    }

    public static void handle(OpenLightningScreenPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            RClientSpellCastHelper.openSchoolResearchScreen(packet.hand, RSchoolRegistry.LIGHTNING.get(), ItemRegistry.LIGHTNING_PAGE.get(),
                    ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "textures/gui/research_screen/lightning_window.png"),
                    ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "textures/gui/research_screen/lightning_frame.png"));
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
