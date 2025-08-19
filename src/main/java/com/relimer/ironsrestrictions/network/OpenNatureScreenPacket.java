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

public class OpenNatureScreenPacket implements CustomPacketPayload {
    private final InteractionHand hand;
    public static final Type<OpenNatureScreenPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "open_nature_screen"));
    public static final StreamCodec<RegistryFriendlyByteBuf, OpenNatureScreenPacket> STREAM_CODEC = CustomPacketPayload.codec(OpenNatureScreenPacket::write, OpenNatureScreenPacket::new);

    public OpenNatureScreenPacket(InteractionHand pHand) {
        this.hand = pHand;
    }

    public OpenNatureScreenPacket(FriendlyByteBuf buf) {
        this.hand = buf.readEnum(InteractionHand.class);
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeEnum(this.hand);
    }

    public static void handle(OpenNatureScreenPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            RClientSpellCastHelper.openSchoolResearchScreen(packet.hand, RSchoolRegistry.NATURE.get(), ItemRegistry.NATURE_PAGE.get(),
                    ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "textures/gui/research_screen/nature_window.png"),
                    ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "textures/gui/research_screen/nature_frame.png"));
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
