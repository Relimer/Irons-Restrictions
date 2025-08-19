package com.relimer.ironsrestrictions.setup;

import com.relimer.ironsrestrictions.Config;
import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.registries.RSchoolRegistry;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.ironsrestrictionsmixinhelper.AbstractSpellMixinHelper;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.Map;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = IronsRestrictions.MODID)
public class CommonConfigSetup {
    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            if(!Config.REPLACE_VANILLA_SCHOOLS.get()){
                return;
            }
            Map<SchoolType, SchoolType> schoolRemap = Map.of(
                    SchoolRegistry.FIRE.get(), RSchoolRegistry.FIRE.get(),
                    SchoolRegistry.NATURE.get(), RSchoolRegistry.NATURE.get(),
                    SchoolRegistry.EVOCATION.get(), RSchoolRegistry.EVOCATION.get(),
                    SchoolRegistry.ENDER.get(), RSchoolRegistry.ENDER.get(),
                    SchoolRegistry.ICE.get(), RSchoolRegistry.ICE.get(),
                    SchoolRegistry.HOLY.get(), RSchoolRegistry.HOLY.get(),
                    SchoolRegistry.BLOOD.get(), RSchoolRegistry.BLOOD.get(),
                    SchoolRegistry.LIGHTNING.get(), RSchoolRegistry.LIGHTNING.get()
            );
            schoolRemap = CommonCompatSetup.putSchoolRemap(schoolRemap);
            AbstractSpellMixinHelper.setSchoolRemap(schoolRemap);
        });
    }
}
