package com.relimer.ironsrestrictions.datagen;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.registries.ComponentRegistry;
import com.relimer.ironsrestrictions.registries.ItemRegistry;
import com.relimer.ironsrestrictions.util.SchoolContainer;
import com.relimer.ironsrestrictions.util.SchoolUtils;
import com.relimer.ironsrestrictions.util.TextureUtils;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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
                    .define('A', io.redspace.ironsspellbooks.registries.ItemRegistry.LOST_KNOWLEDGE_FRAGMENT.get())
                    .define('B', holder.value().getFocus())
                    .unlockedBy("has_fragment", has(io.redspace.ironsspellbooks.registries.ItemRegistry.LOST_KNOWLEDGE_FRAGMENT.get()))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, holder.value().getId().getPath() + "_manuscript"));
        });
    }
}
