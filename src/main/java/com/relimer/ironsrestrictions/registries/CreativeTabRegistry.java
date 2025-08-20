package com.relimer.ironsrestrictions.registries;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.util.SchoolContainer;
import com.relimer.ironsrestrictions.util.SchoolUtils;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = IronsRestrictions.MODID)
public class CreativeTabRegistry {
    private static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, IronsRestrictions.MODID);
    public static void register(IEventBus eventBus) {
        TABS.register(eventBus);
    }

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> RESTRICTIONS_TAB = TABS.register("restrictions_items_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + IronsRestrictions.MODID + ".restrictions_items_tab"))
            .icon(() -> new ItemStack(ItemRegistry.MANUSCRIPT.get()))
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .build());
    @SubscribeEvent
    public static void fillCreativeTabs(final BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == RESTRICTIONS_TAB.get()) {
            SchoolUtils.getLoopSchools().forEach(holder -> {
                SchoolType schoolType = holder.value();
                ItemStack manuscript = new ItemStack(ItemRegistry.MANUSCRIPT);
                manuscript.set(ComponentRegistry.SCHOOL_COMPONENT.get(), new SchoolContainer(schoolType));
                event.accept(manuscript);
            });
        }
    }
}
