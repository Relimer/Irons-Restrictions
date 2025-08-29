package com.relimer.ironsrestrictions.setup;

import com.relimer.ironsrestrictions.Config;
import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.item.ManuscriptData;
import com.relimer.ironsrestrictions.registries.ItemRegistry;
import com.relimer.ironsrestrictions.render.ManuscriptModel;
import com.relimer.ironsrestrictions.util.SchoolContainer;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = IronsRestrictions.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
    @SubscribeEvent
    public static void onRegisterItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> {
            if (Config.TintBasePage.get() && tintIndex == 1 || Config.TintOverlayPage.get() && tintIndex == 2) {
                SchoolContainer schoolContainer = ManuscriptData.getSchoolContainer(stack);
                if (schoolContainer != null) {
                    return schoolContainer.getSchoolColour();
                }
            }
            return 0xFFFFFFFF; // No tint
        }, ItemRegistry.MANUSCRIPT.get());
    }
    @SubscribeEvent
    public static void registerSpecialModels(ModelEvent.RegisterAdditional event) {
        for (SchoolType schoolType : SchoolRegistry.REGISTRY.get()) {
            ResourceLocation location = ManuscriptModel.getManuscriptModelLocation(schoolType);
            event.register(location);
        }
    }

    @SubscribeEvent
    public static void replaceItemModels(ModelEvent.ModifyBakingResult event) {
        var key = new ModelResourceLocation(IronsRestrictions.id("manuscript"), "inventory");
        event.getModels().computeIfPresent(key, (k, oldModel) -> new ManuscriptModel(oldModel, event.getModelBakery()) {});
    }
}
