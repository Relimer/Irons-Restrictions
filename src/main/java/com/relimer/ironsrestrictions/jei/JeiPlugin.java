package com.relimer.ironsrestrictions.jei;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.registries.ItemRegistry;
import io.redspace.ironsspellbooks.jei.*;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.registration.*;
import net.minecraft.resources.ResourceLocation;


@mezz.jei.api.JeiPlugin
public class JeiPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "jei_plugin");
    }
    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.registerSubtypeInterpreter(ItemRegistry.MANUSCRIPT.get(), new ManuscriptSubtypeInterpreter());
    }
}
