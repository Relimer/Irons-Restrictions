package com.relimer.ironsrestrictions.jei;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.item.ManuscriptData;
import com.relimer.ironsrestrictions.registries.ItemRegistry;
import com.relimer.ironsrestrictions.util.SchoolContainer;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import io.redspace.ironsspellbooks.gui.arcane_anvil.ArcaneAnvilMenu;
import io.redspace.ironsspellbooks.gui.arcane_anvil.ArcaneAnvilScreen;
import io.redspace.ironsspellbooks.gui.scroll_forge.ScrollForgeScreen;
import io.redspace.ironsspellbooks.jei.*;
import io.redspace.ironsspellbooks.registries.BlockRegistry;
import io.redspace.ironsspellbooks.registries.MenuRegistry;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.ingredients.subtypes.IIngredientSubtypeInterpreter;
import mezz.jei.api.recipe.vanilla.IVanillaRecipeFactory;
import mezz.jei.api.registration.*;
import mezz.jei.api.runtime.IIngredientManager;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.stream.IntStream;

@mezz.jei.api.JeiPlugin
public class JeiPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "jei_plugin");
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.registerSubtypeInterpreter(ItemRegistry.MANUSCRIPT.get(), MANUSCRIPT_INTERPRETER);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        SchoolRegistry.REGISTRY.get().getValues().stream().forEach(holder -> {
                var list = new ArrayList<ItemStack>();
                var scrollStack = new ItemStack(ItemRegistry.MANUSCRIPT.get());
                ManuscriptData.setSchoolContainer(scrollStack, new SchoolContainer(holder));
                list.add(scrollStack);
        });
    }

    final IIngredientSubtypeInterpreter<ItemStack> MANUSCRIPT_INTERPRETER = (stack, context) -> {
        if (stack.hasTag()) {
            var ss = ManuscriptData.getSchoolContainer(stack).getSchoolId();
            return String.format("%s_manuscript", ss.getPath());
        }

        return IIngredientSubtypeInterpreter.NONE;
    };
}
