package com.relimer.ironsrestrictions.registries;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.setup.CommonCompatSetup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = IronsRestrictions.MODID, bus = EventBusSubscriber.Bus.MOD)
public class CreativeTabRegistry {
    private static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, IronsRestrictions.MODID);
    public static void register(IEventBus eventBus) {
        TABS.register(eventBus);
    }

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> RESTRICTIONS_TAB = TABS.register("restrictions_items_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + IronsRestrictions.MODID + ".restrictions_items_tab"))
            .icon(() -> new ItemStack(ItemRegistry.FIRE_PAGE))
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .build());
    @SubscribeEvent
    public static void fillCreativeTabs(final BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == RESTRICTIONS_TAB.get()) {
            event.accept(ItemRegistry.FIRE_PAGE.get());
            event.accept(ItemRegistry.FIRE_KNOWLEDGE_FRAGMENT.get());
            event.accept(ItemRegistry.NATURE_PAGE.get());
            event.accept(ItemRegistry.NATURE_KNOWLEDGE_FRAGMENT.get());
            event.accept(ItemRegistry.EVOCATION_PAGE.get());
            event.accept(ItemRegistry.EVOCATION_KNOWLEDGE_FRAGMENT.get());
            event.accept(ItemRegistry.ENDER_PAGE.get());
            event.accept(ItemRegistry.ENDER_KNOWLEDGE_FRAGMENT.get());
            event.accept(ItemRegistry.ICE_PAGE.get());
            event.accept(ItemRegistry.ICE_KNOWLEDGE_FRAGMENT.get());
            event.accept(ItemRegistry.HOLY_PAGE.get());
            event.accept(ItemRegistry.HOLY_KNOWLEDGE_FRAGMENT.get());
            event.accept(ItemRegistry.BLOOD_PAGE.get());
            event.accept(ItemRegistry.BLOOD_KNOWLEDGE_FRAGMENT.get());
            event.accept(ItemRegistry.LIGHTNING_PAGE.get());
            event.accept(ItemRegistry.LIGHTNING_KNOWLEDGE_FRAGMENT.get());

            CommonCompatSetup.fillCreativeTab(event);
        }
    }

    public static void acceptIfPresent(BuildCreativeModeTabContentsEvent event, net.neoforged.neoforge.registries.DeferredHolder<Item, ?> holder) {
        if (holder != null) {
            event.accept(holder.get());
        }
    }
}
