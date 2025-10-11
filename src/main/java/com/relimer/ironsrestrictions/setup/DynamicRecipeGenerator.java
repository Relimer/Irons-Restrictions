package com.relimer.ironsrestrictions.setup;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.registries.ComponentRegistry;
import com.relimer.ironsrestrictions.registries.ItemRegistry;
import com.relimer.ironsrestrictions.util.SchoolContainer;
import com.relimer.ironsrestrictions.util.SchoolUtils;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerStartedEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@EventBusSubscriber(modid = IronsRestrictions.MODID)
public class DynamicRecipeGenerator {
    @SubscribeEvent
    public static void onServerStarted(ServerStartedEvent event) {
        MinecraftServer server = event.getServer();
        RecipeManager recipeManager = server.getRecipeManager();

        Collection<RecipeHolder<?>> existing = recipeManager.getRecipes();
        List<RecipeHolder<?>> newRecipes = new ArrayList<>(existing);
        SchoolUtils.getLoopSchools().forEach(holder -> {
            SchoolType school = holder.value();
            ResourceLocation recipeId = ResourceLocation.fromNamespaceAndPath(
                    IronsRestrictions.MODID, school.getId().getPath() + "_manuscript"
            );

            List<String> pattern = List.of(
                    " A ",
                    "ABA",
                    " A "
            );
            Map<Character, Ingredient> key = Map.of(
                    'A', Ingredient.of(ItemRegistry.FRAGMENT.get()),
                    'B', Ingredient.of(school.getFocus()) // assuming this returns an Ingredient
            );
            ItemStack output = new ItemStack(ItemRegistry.MANUSCRIPT);
            output.set(ComponentRegistry.SCHOOL_COMPONENT, new SchoolContainer(school));
            ShapedRecipePattern shapedPattern = ShapedRecipePattern.of(key, pattern);
            ShapedRecipe recipe = new ShapedRecipe(
                    "manuscripts",                   // Group (used in recipe book)
                    CraftingBookCategory.MISC,       // Category
                    shapedPattern,                   // The pattern
                    output,                          // The resulting ItemStack
                    false                            // Show notification on unlock
            );
            Recipe<?> myRecipe = recipe;
            RecipeHolder<?> newHolder = new RecipeHolder<>(recipeId, myRecipe);
            newRecipes.add(newHolder);
        });
        recipeManager.replaceRecipes(newRecipes);
    }
}
