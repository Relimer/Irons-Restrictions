package com.relimer.ironsrestrictions.datagen;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.registries.ComponentRegistry;
import com.relimer.ironsrestrictions.registries.ItemRegistry;
import com.relimer.ironsrestrictions.util.SchoolContainer;
import com.relimer.ironsrestrictions.util.SchoolUtils;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class RRecipeProvider extends RecipeProvider {

    public RRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }
    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        SchoolUtils.getLoopSchools().forEach(holder -> {
            ItemStack itemStack = new ItemStack(ItemRegistry.MANUSCRIPT);
            itemStack.set(ComponentRegistry.SCHOOL_COMPONENT, new SchoolContainer(holder.value()));
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, itemStack)
                    .pattern(" A ")
                    .pattern("ABA")
                    .pattern(" A ")
                    .define('A', ItemRegistry.FRAGMENT.get())
                    .define('B', holder.value().getFocus())
                    .unlockedBy("has_fragment", has(ItemRegistry.FRAGMENT.get()))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, holder.value().getId().getPath() + "_manuscript"));
        });
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.FRAGMENT.get(), 2)
                .requires(io.redspace.ironsspellbooks.registries.ItemRegistry.SCROLL.get())
                .unlockedBy("has_scroll", has(io.redspace.ironsspellbooks.registries.ItemRegistry.SCROLL.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "fragment"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.UNFINISHED_MANUSCRIPT.get())
                .requires(ItemRegistry.FRAGMENT.get())
                .requires(io.redspace.ironsspellbooks.registries.ItemRegistry.MAGIC_CLOTH.get(), 4)
                .unlockedBy("has_fragment", has(ItemRegistry.FRAGMENT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "unfinished_manuscript"));
    }
}
