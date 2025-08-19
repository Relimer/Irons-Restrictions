package com.relimer.ironsrestrictions.datagen;

import com.relimer.ironsrestrictions.registries.ItemRegistry;
import com.relimer.ironsrestrictions.setup.CommonCompatSetup;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }
    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.FIRE_PAGE.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ItemRegistry.FIRE_KNOWLEDGE_FRAGMENT.get())
                .define('B', Items.BLAZE_ROD)
                .unlockedBy("has_fire_fragment", has(ItemRegistry.FIRE_KNOWLEDGE_FRAGMENT.get()))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.NATURE_PAGE.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ItemRegistry.NATURE_KNOWLEDGE_FRAGMENT.get())
                .define('B', Items.POISONOUS_POTATO)
                .unlockedBy("has_nature_fragment", has(ItemRegistry.NATURE_KNOWLEDGE_FRAGMENT.get()))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.ENDER_PAGE.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ItemRegistry.ENDER_KNOWLEDGE_FRAGMENT.get())
                .define('B', Items.ENDER_PEARL)
                .unlockedBy("has_ender_fragment", has(ItemRegistry.ENDER_KNOWLEDGE_FRAGMENT.get()))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.EVOCATION_PAGE.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ItemRegistry.EVOCATION_KNOWLEDGE_FRAGMENT.get())
                .define('B', Items.EMERALD)
                .unlockedBy("has_evocation_fragment", has(ItemRegistry.EVOCATION_KNOWLEDGE_FRAGMENT.get()))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.ICE_PAGE.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ItemRegistry.ICE_KNOWLEDGE_FRAGMENT.get())
                .define('B', io.redspace.ironsspellbooks.registries.ItemRegistry.FROZEN_BONE_SHARD.get())
                .unlockedBy("has_ice_fragment", has(ItemRegistry.ICE_KNOWLEDGE_FRAGMENT.get()))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.HOLY_PAGE.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ItemRegistry.HOLY_KNOWLEDGE_FRAGMENT.get())
                .define('B', io.redspace.ironsspellbooks.registries.ItemRegistry.DIVINE_PEARL.get())
                .unlockedBy("has_holy_fragment", has(ItemRegistry.HOLY_KNOWLEDGE_FRAGMENT.get()))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.BLOOD_PAGE.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ItemRegistry.BLOOD_KNOWLEDGE_FRAGMENT.get())
                .define('B', io.redspace.ironsspellbooks.registries.ItemRegistry.BLOOD_VIAL.get())
                .unlockedBy("has_blood_fragment", has(ItemRegistry.BLOOD_KNOWLEDGE_FRAGMENT.get()))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.LIGHTNING_PAGE.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ItemRegistry.LIGHTNING_KNOWLEDGE_FRAGMENT.get())
                .define('B', io.redspace.ironsspellbooks.registries.ItemRegistry.LIGHTNING_BOTTLE.get())
                .unlockedBy("has_lightning_fragment", has(ItemRegistry.LIGHTNING_KNOWLEDGE_FRAGMENT.get()))
                .save(recipeOutput);
        CommonCompatSetup.recipeProvider(recipeOutput);
    }
}
