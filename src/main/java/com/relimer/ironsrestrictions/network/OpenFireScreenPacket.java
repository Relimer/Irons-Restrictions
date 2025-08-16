package com.relimer.ironsrestrictions.network;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.player.ClientSpellCastHelperMixin;
import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class OpenFireScreenPacket implements CustomPacketPayload {
    private final InteractionHand hand;
    public static final CustomPacketPayload.Type<OpenFireScreenPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "open_fire_screen"));
    public static final StreamCodec<RegistryFriendlyByteBuf, OpenFireScreenPacket> STREAM_CODEC = CustomPacketPayload.codec(OpenFireScreenPacket::write, OpenFireScreenPacket::new);

    public OpenFireScreenPacket(InteractionHand pHand) {
        this.hand = pHand;
    }

    public OpenFireScreenPacket(FriendlyByteBuf buf) {
        this.hand = buf.readEnum(InteractionHand.class);
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeEnum(this.hand);
    }

    public static void handle(OpenFireScreenPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            ClientSpellCastHelperMixin.openFireResearchScreen(packet.hand);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
