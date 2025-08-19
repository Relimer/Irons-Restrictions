package com.relimer.ironsrestrictions.compat.cataclysmspellbooks.registries;

import com.relimer.ironsrestrictions.registries.CreativeTabRegistry;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import static com.relimer.ironsrestrictions.registries.CreativeTabRegistry.acceptIfPresent;

public class CSFillCreativeTabs {
    public static void addCompatItems(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == CreativeTabRegistry.RESTRICTIONS_TAB.get()) {
            acceptIfPresent(event, CSItemRegistry.ABYSSAL_PAGE);
            acceptIfPresent(event, CSItemRegistry.ABYSSAL_KNOWLEDGE_FRAGMENT);
        }
    }
}
