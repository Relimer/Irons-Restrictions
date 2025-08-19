package com.relimer.ironsrestrictions.compat.alshanexfamiliars.registries;

import com.relimer.ironsrestrictions.registries.CreativeTabRegistry;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import static com.relimer.ironsrestrictions.registries.CreativeTabRegistry.acceptIfPresent;

public class AFFillCreativeTabs {
    public static void addCompatItems(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == CreativeTabRegistry.RESTRICTIONS_TAB.get()) {
            acceptIfPresent(event, AFItemRegistry.SOUND_PAGE);
            acceptIfPresent(event, AFItemRegistry.SOUND_KNOWLEDGE_FRAGMENT);
        }
    }
}
