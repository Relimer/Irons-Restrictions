package com.relimer.ironsrestrictions.compat.alshanexfamiliars.network;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.compat.alshanexfamiliars.registries.AFItemRegistry;
import com.relimer.ironsrestrictions.compat.alshanexfamiliars.registries.AFRSchoolRegistry;
import com.relimer.ironsrestrictions.player.RClientSpellCastHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class OpenSoundScreenPacket implements CustomPacketPayload {
    private final InteractionHand hand;
    public static final Type<OpenSoundScreenPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "open_sound_screen"));
    public static final StreamCodec<RegistryFriendlyByteBuf, OpenSoundScreenPacket> STREAM_CODEC = CustomPacketPayload.codec(OpenSoundScreenPacket::write, OpenSoundScreenPacket::new);

    public OpenSoundScreenPacket(InteractionHand pHand) {
        this.hand = pHand;
    }

    public OpenSoundScreenPacket(FriendlyByteBuf buf) {
        this.hand = buf.readEnum(InteractionHand.class);
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeEnum(this.hand);
    }

    public static void handle(OpenSoundScreenPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            RClientSpellCastHelper.openSchoolResearchScreen(packet.hand, AFRSchoolRegistry.SOUND.get(), AFItemRegistry.SOUND_PAGE.get(),
                    ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "textures/gui/research_screen/sound_window.png"),
                    ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "textures/gui/research_screen/sound_frame.png"));
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
