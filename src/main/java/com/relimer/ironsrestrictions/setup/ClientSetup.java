package com.relimer.ironsrestrictions.setup;

import com.relimer.ironsrestrictions.Config;
import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.registries.ComponentRegistry;
import com.relimer.ironsrestrictions.registries.ItemRegistry;
import com.relimer.ironsrestrictions.util.SchoolContainer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;


@EventBusSubscriber(value = Dist.CLIENT, modid = IronsRestrictions.MODID)
public class ClientSetup {
    @SubscribeEvent
    public static void onRegisterItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> {
            if (Config.TintBasePage.get() && tintIndex == 0 || Config.TintOverlayPage.get() && tintIndex == 1) {
                SchoolContainer schoolContainer = stack.get(ComponentRegistry.SCHOOL_COMPONENT);
                if (schoolContainer != null) {
                    return schoolContainer.getSchoolColour();
                }
            }
            return 0xFFFFFFFF; // No tint
        }, ItemRegistry.MANUSCRIPT.get());
    }
}
