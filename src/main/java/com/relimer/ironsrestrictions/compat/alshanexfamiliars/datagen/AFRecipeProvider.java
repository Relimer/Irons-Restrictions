package com.relimer.ironsrestrictions.compat.alshanexfamiliars.datagen;


import com.relimer.ironsrestrictions.compat.alshanexfamiliars.registries.AFItemRegistry;
import com.relimer.ironsrestrictions.registries.ItemRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class AFRecipeProvider extends RecipeProvider {
    public AFRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    public static void add(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AFItemRegistry.SOUND_PAGE.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', AFItemRegistry.SOUND_KNOWLEDGE_FRAGMENT.get())
                .define('B', Items.NOTE_BLOCK)
                .unlockedBy("has_sound_fragment", has(AFItemRegistry.SOUND_KNOWLEDGE_FRAGMENT.get()))
                .save(recipeOutput);
    }
}
