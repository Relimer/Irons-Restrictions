package com.relimer.ironsrestrictions.network;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.player.RClientSpellCastHelper;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class OpenSchoolScreenPacket implements CustomPacketPayload {
    private final InteractionHand hand;
    private static SchoolType schoolType = null;

    public static final Type<OpenSchoolScreenPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "open_school_screen"));
    public static final StreamCodec<RegistryFriendlyByteBuf, OpenSchoolScreenPacket> STREAM_CODEC = CustomPacketPayload.codec(OpenSchoolScreenPacket::write, OpenSchoolScreenPacket::new);

    public OpenSchoolScreenPacket(FriendlyByteBuf buf) {
        this.hand = buf.readEnum(InteractionHand.class);
    }

    public OpenSchoolScreenPacket(InteractionHand pHand, SchoolType schoolType) {
        this.hand = pHand;
        this.schoolType = schoolType;
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeEnum(this.hand);
    }

    public static void handle(OpenSchoolScreenPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            RClientSpellCastHelper.openSchoolResearchScreen(packet.hand, schoolType);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
