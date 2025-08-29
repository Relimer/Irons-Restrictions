package com.relimer.ironsrestrictions.registries;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.item.ManuscriptData;
import com.relimer.ironsrestrictions.util.SchoolContainer;
import com.relimer.ironsrestrictions.util.SchoolUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = IronsRestrictions.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreativeTabRegistry {
    private static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, IronsRestrictions.MODID);
    public static void register(IEventBus eventBus) {
        TABS.register(eventBus);
    }

    public static final RegistryObject<CreativeModeTab> RESTRICTIONS_TAB = TABS.register("restrictions_items_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + IronsRestrictions.MODID + ".restrictions_items_tab"))
            .icon(() -> new ItemStack(ItemRegistry.MANUSCRIPT.get()))
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .build());
    @SubscribeEvent
    public static void fillCreativeTabs(final BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == RESTRICTIONS_TAB.get()) {
            SchoolUtils.getLoopSchools().forEach(holder -> {
                ItemStack manuscript = new ItemStack(ItemRegistry.MANUSCRIPT.get());
                ManuscriptData.setSchoolContainer(manuscript, new SchoolContainer(holder));
                event.accept(manuscript);
            });
            event.accept(ItemRegistry.FRAGMENT.get());
            event.accept(ItemRegistry.UNFINISHED_MANUSCRIPT.get());
        }
    }
}
