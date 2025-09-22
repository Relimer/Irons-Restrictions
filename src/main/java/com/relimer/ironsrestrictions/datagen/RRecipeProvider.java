package com.relimer.ironsrestrictions.datagen;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.registries.ComponentRegistry;
import com.relimer.ironsrestrictions.registries.ItemRegistry;
import com.relimer.ironsrestrictions.util.SchoolContainer;
import com.relimer.ironsrestrictions.util.SchoolUtils;
import io.redspace.ironsspellbooks.recipe_types.alchemist_cauldron.BrewAlchemistCauldronRecipe;
import io.redspace.ironsspellbooks.recipe_types.alchemist_cauldron.FillAlchemistCauldronRecipe;
import io.redspace.ironsspellbooks.registries.FluidRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

import static io.redspace.ironsspellbooks.datagen.IronRecipeProvider.cauldronBottledInteraction;

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

        BrewAlchemistCauldronRecipe.builder()
                .withInput(FluidRegistry.COMMON_INK, 1000)
                .withReagent(io.redspace.ironsspellbooks.registries.ItemRegistry.UPGRADE_ORB.get())
                .withByproduct(ItemRegistry.COMMON_UPGRADE)
                .saveSoak(recipeOutput);
        BrewAlchemistCauldronRecipe.builder()
                .withInput(FluidRegistry.UNCOMMON_INK, 1000)
                .withReagent(io.redspace.ironsspellbooks.registries.ItemRegistry.UPGRADE_ORB.get())
                .withByproduct(ItemRegistry.UNCOMMON_UPGRADE)
                .saveSoak(recipeOutput);
        BrewAlchemistCauldronRecipe.builder()
                .withInput(FluidRegistry.RARE_INK, 1000)
                .withReagent(io.redspace.ironsspellbooks.registries.ItemRegistry.UPGRADE_ORB.get())
                .withByproduct(ItemRegistry.RARE_UPGRADE)
                .saveSoak(recipeOutput);
        BrewAlchemistCauldronRecipe.builder()
                .withInput(FluidRegistry.EPIC_INK, 1000)
                .withReagent(io.redspace.ironsspellbooks.registries.ItemRegistry.UPGRADE_ORB.get())
                .withByproduct(ItemRegistry.EPIC_UPGRADE)
                .saveSoak(recipeOutput);
        BrewAlchemistCauldronRecipe.builder()
                .withInput(FluidRegistry.LEGENDARY_INK, 1000)
                .withReagent(io.redspace.ironsspellbooks.registries.ItemRegistry.UPGRADE_ORB.get())
                .withByproduct(ItemRegistry.LEGENDARY_UPGRADE)
                .saveSoak(recipeOutput);

        new FillAlchemistCauldronRecipe.Builder()
                .withInput(ItemRegistry.COMMON_UPGRADE.get())
                .withReturnItem(io.redspace.ironsspellbooks.registries.ItemRegistry.UPGRADE_ORB.get())
                .withFluid(new FluidStack(FluidRegistry.COMMON_INK, 1000))
                .withSound(SoundEvents.BOTTLE_EMPTY)
                .mustFitAll(true)
                .save(recipeOutput);
        new FillAlchemistCauldronRecipe.Builder()
                .withInput(ItemRegistry.UNCOMMON_UPGRADE.get())
                .withReturnItem(io.redspace.ironsspellbooks.registries.ItemRegistry.UPGRADE_ORB.get())
                .withFluid(new FluidStack(FluidRegistry.UNCOMMON_INK, 1000))
                .withSound(SoundEvents.BOTTLE_EMPTY)
                .mustFitAll(true)
                .save(recipeOutput);
        new FillAlchemistCauldronRecipe.Builder()
                .withInput(ItemRegistry.RARE_UPGRADE.get())
                .withReturnItem(io.redspace.ironsspellbooks.registries.ItemRegistry.UPGRADE_ORB.get())
                .withFluid(new FluidStack(FluidRegistry.RARE_INK, 1000))
                .withSound(SoundEvents.BOTTLE_EMPTY)
                .mustFitAll(true)
                .save(recipeOutput);
        new FillAlchemistCauldronRecipe.Builder()
                .withInput(ItemRegistry.EPIC_UPGRADE.get())
                .withReturnItem(io.redspace.ironsspellbooks.registries.ItemRegistry.UPGRADE_ORB.get())
                .withFluid(new FluidStack(FluidRegistry.EPIC_INK, 1000))
                .withSound(SoundEvents.BOTTLE_EMPTY)
                .mustFitAll(true)
                .save(recipeOutput);
        new FillAlchemistCauldronRecipe.Builder()
                .withInput(ItemRegistry.LEGENDARY_UPGRADE.get())
                .withReturnItem(io.redspace.ironsspellbooks.registries.ItemRegistry.UPGRADE_ORB.get())
                .withFluid(new FluidStack(FluidRegistry.LEGENDARY_INK, 1000))
                .withSound(SoundEvents.BOTTLE_EMPTY)
                .mustFitAll(true)
                .save(recipeOutput);
    }
}
