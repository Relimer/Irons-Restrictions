package com.relimer.ironsrestrictions.setup;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.network.*;
import com.relimer.ironsrestrictions.network.spells.RLearnSpellPacket;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = IronsRestrictions.MODID)
public class PayloadHandler {

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar payloadRegistrar = event.registrar(IronsRestrictions.MODID).versioned("1.0.0").optional();
        payloadRegistrar.playToClient(OpenSchoolScreenPacket.TYPE, OpenSchoolScreenPacket.STREAM_CODEC, OpenSchoolScreenPacket::handle);

        payloadRegistrar.playToServer(RLearnSpellPacket.TYPE, RLearnSpellPacket.STREAM_CODEC, RLearnSpellPacket::handle);
    }
}
