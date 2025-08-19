package com.relimer.ironsrestrictions.compat.cataclysmspellbooks.network;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.compat.alshanexfamiliars.registries.AFItemRegistry;
import com.relimer.ironsrestrictions.compat.alshanexfamiliars.registries.AFRSchoolRegistry;
import com.relimer.ironsrestrictions.compat.cataclysmspellbooks.registries.CSItemRegistry;
import com.relimer.ironsrestrictions.compat.cataclysmspellbooks.registries.CSRSchoolRegistry;
import com.relimer.ironsrestrictions.player.RClientSpellCastHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class OpenAbyssalScreenPacket implements CustomPacketPayload {
    private final InteractionHand hand;
    public static final Type<OpenAbyssalScreenPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "open_abyssal_screen"));
    public static final StreamCodec<RegistryFriendlyByteBuf, OpenAbyssalScreenPacket> STREAM_CODEC = CustomPacketPayload.codec(OpenAbyssalScreenPacket::write, OpenAbyssalScreenPacket::new);

    public OpenAbyssalScreenPacket(InteractionHand pHand) {
        this.hand = pHand;
    }

    public OpenAbyssalScreenPacket(FriendlyByteBuf buf) {
        this.hand = buf.readEnum(InteractionHand.class);
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeEnum(this.hand);
    }

    public static void handle(OpenAbyssalScreenPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            RClientSpellCastHelper.openSchoolResearchScreen(packet.hand, CSRSchoolRegistry.ABYSSAL.get(), CSItemRegistry.ABYSSAL_PAGE.get(),
                    ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "textures/gui/research_screen/abyssal_window.png"),
                    ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "textures/gui/research_screen/abyssal_frame.png"));
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
