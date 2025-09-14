package com.relimer.ironsrestrictions.setup;

import com.relimer.ironsrestrictions.Config;
import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.anim.Animations;
import com.relimer.ironsrestrictions.registries.ComponentRegistry;
import com.relimer.ironsrestrictions.registries.ItemRegistry;
import com.relimer.ironsrestrictions.render.ManuscriptModel;
import com.relimer.ironsrestrictions.util.SchoolContainer;
import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.api.layered.modifier.AdjustmentModifier;
import dev.kosmx.playerAnim.api.layered.modifier.MirrorModifier;
import dev.kosmx.playerAnim.core.util.Vec3f;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationFactory;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import io.redspace.ironsspellbooks.api.magic.SpellSelectionManager;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.api.spells.SpellAnimations;
import io.redspace.ironsspellbooks.player.ClientMagicData;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

import java.util.Optional;


@EventBusSubscriber(value = Dist.CLIENT, modid = IronsRestrictions.MODID)
public class ClientSetup {

    @SubscribeEvent
    public static void clintSetup(final FMLClientSetupEvent event) {
        PlayerAnimationFactory.ANIMATION_DATA_FACTORY.registerFactory(
                Animations.ANIMATION_RESOURCE,
                42,
                ClientSetup::registerPlayerAnimation);
    }
    private static IAnimation registerPlayerAnimation(AbstractClientPlayer player) {
        //This will be invoked for every new player
        return new ModifierLayer<>();
    }
    @SubscribeEvent
    public static void onRegisterItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> {
            if (Config.TintBasePage.get() && tintIndex == 1 || Config.TintOverlayPage.get() && tintIndex == 2) {
                SchoolContainer schoolContainer = stack.get(ComponentRegistry.SCHOOL_COMPONENT);
                if (schoolContainer != null) {
                    return schoolContainer.getSchoolColour();
                }
            }
            return 0xFFFFFFFF; // No tint
        }, ItemRegistry.MANUSCRIPT.get());
    }
    @SubscribeEvent
    public static void registerSpecialModels(ModelEvent.RegisterAdditional event) {
        for (SchoolType schoolType : SchoolRegistry.REGISTRY) {
            event.register(ModelResourceLocation.standalone(ManuscriptModel.getManuscriptModelLocation(schoolType)));
        }
    }

    @SubscribeEvent
    public static void replaceItemModels(ModelEvent.ModifyBakingResult event) {
        var key = new ModelResourceLocation(IronsRestrictions.id("manuscript"), "inventory");
        event.getModels().computeIfPresent(key, (k, oldModel) -> new ManuscriptModel(oldModel, event.getModelBakery()) {});
    }
}
