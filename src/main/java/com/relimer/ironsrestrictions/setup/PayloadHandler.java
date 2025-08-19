package com.relimer.ironsrestrictions.setup;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.network.*;
import com.relimer.ironsrestrictions.network.spells.RLearnSpellPacket;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = IronsRestrictions.MODID)
public class PayloadHandler {

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar payloadRegistrar = event.registrar(IronsRestrictions.MODID).versioned("1.0.0").optional();
        payloadRegistrar.playToClient(OpenFireScreenPacket.TYPE, OpenFireScreenPacket.STREAM_CODEC, OpenFireScreenPacket::handle);
        payloadRegistrar.playToClient(OpenNatureScreenPacket.TYPE, OpenNatureScreenPacket.STREAM_CODEC, OpenNatureScreenPacket::handle);
        payloadRegistrar.playToClient(OpenEvocationScreenPacket.TYPE, OpenEvocationScreenPacket.STREAM_CODEC, OpenEvocationScreenPacket::handle);
        payloadRegistrar.playToClient(OpenEnderScreenPacket.TYPE, OpenEnderScreenPacket.STREAM_CODEC, OpenEnderScreenPacket::handle);
        payloadRegistrar.playToClient(OpenIceScreenPacket.TYPE, OpenIceScreenPacket.STREAM_CODEC, OpenIceScreenPacket::handle);
        payloadRegistrar.playToClient(OpenHolyScreenPacket.TYPE, OpenHolyScreenPacket.STREAM_CODEC, OpenHolyScreenPacket::handle);
        payloadRegistrar.playToClient(OpenBloodScreenPacket.TYPE, OpenBloodScreenPacket.STREAM_CODEC, OpenBloodScreenPacket::handle);
        payloadRegistrar.playToClient(OpenLightningScreenPacket.TYPE, OpenLightningScreenPacket.STREAM_CODEC, OpenLightningScreenPacket::handle);

        CommonCompatSetup.payloadHandler(payloadRegistrar);

        payloadRegistrar.playToServer(RLearnSpellPacket.TYPE, RLearnSpellPacket.STREAM_CODEC, RLearnSpellPacket::handle);
    }
}
