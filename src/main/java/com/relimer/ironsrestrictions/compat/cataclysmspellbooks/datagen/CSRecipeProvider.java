package com.relimer.ironsrestrictions.compat.cataclysmspellbooks.datagen;


import com.relimer.ironsrestrictions.compat.alshanexfamiliars.registries.AFItemRegistry;
import com.relimer.ironsrestrictions.compat.cataclysmspellbooks.registries.CSItemRegistry;
import net.acetheeldritchking.cataclysm_spellbooks.util.CSTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class CSRecipeProvider extends RecipeProvider {
    public CSRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    public static void add(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CSItemRegistry.ABYSSAL_PAGE.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', CSItemRegistry.ABYSSAL_KNOWLEDGE_FRAGMENT.get())
                .define('B', CSTags.ABYSSAL_FOCUS)
                .unlockedBy("has_sound_fragment", has(CSItemRegistry.ABYSSAL_KNOWLEDGE_FRAGMENT.get()))
                .save(recipeOutput);
    }
}
