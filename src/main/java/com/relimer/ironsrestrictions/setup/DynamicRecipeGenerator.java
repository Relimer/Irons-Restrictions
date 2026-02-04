package com.relimer.ironsrestrictions.setup;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.item.ManuscriptData;
import com.relimer.ironsrestrictions.registries.ItemRegistry;
import com.relimer.ironsrestrictions.util.SchoolContainer;
import com.relimer.ironsrestrictions.util.SchoolUtils;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Mod.EventBusSubscriber(modid = IronsRestrictions.MODID)
public class DynamicRecipeGenerator {
    @SubscribeEvent
    public static void onServerStarted(ServerStartedEvent event) {
        MinecraftServer server = event.getServer();
        RecipeManager recipeManager = server.getRecipeManager();

        Collection<Recipe<?>> existing = recipeManager.getRecipes();
        List<Recipe<?>> newRecipes = new ArrayList<>(existing);
        SchoolUtils.getLoopSchools().forEach(holder -> {
            ResourceLocation recipeId = ResourceLocation.fromNamespaceAndPath(
                    IronsRestrictions.MODID, holder.getId().getPath() + "_manuscript"
            );
            IronsRestrictions.LOGGER.info("IRONS RESTRICTIONS RECIPE: " + recipeId);
            ItemStack output = new ItemStack(ItemRegistry.MANUSCRIPT.get());
            ManuscriptData.setSchoolContainer(output, new SchoolContainer(holder));
            NonNullList<Ingredient> ingredients = NonNullList.withSize(9, Ingredient.EMPTY);
            Ingredient A = Ingredient.of(ItemRegistry.FRAGMENT.get());
            Ingredient B = Ingredient.of(holder.getFocus());

            ingredients.set(1, A);
            ingredients.set(3, A);
            ingredients.set(4, B);
            ingredients.set(5, A);
            ingredients.set(7, A);
            ShapedRecipe recipe = new ShapedRecipe(
                    recipeId,
                    "manuscripts",
                    CraftingBookCategory.MISC,
                    3,
                    3,
                    ingredients,
                    output,
                    false
            );
            if (!newRecipes.contains(recipe)) {
                newRecipes.add(recipe);
            }
        });
        recipeManager.replaceRecipes(newRecipes);
    }
}
