package com.relimer.ironsrestrictions.datagen;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.registries.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

public class RRecipeProvider extends RecipeProvider {


    public RRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.FRAGMENT.get(), 2)
                .requires(io.redspace.ironsspellbooks.registries.ItemRegistry.SCROLL.get())
                .unlockedBy("has_scroll", has(io.redspace.ironsspellbooks.registries.ItemRegistry.SCROLL.get()))
                .save(pWriter, ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "fragment"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.UNFINISHED_MANUSCRIPT.get())
                .requires(ItemRegistry.FRAGMENT.get())
                .requires(io.redspace.ironsspellbooks.registries.ItemRegistry.MAGIC_CLOTH.get(), 4)
                .unlockedBy("has_fragment", has(ItemRegistry.FRAGMENT.get()))
                .save(pWriter, ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "unfinished_manuscript"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.COMMON_UPGRADE.get())
                .requires(io.redspace.ironsspellbooks.registries.ItemRegistry.UPGRADE_ORB.get())
                .requires(io.redspace.ironsspellbooks.registries.ItemRegistry.INK_COMMON.get(), 4)
                .unlockedBy("has_orb", has(io.redspace.ironsspellbooks.registries.ItemRegistry.UPGRADE_ORB.get()))
                .save(pWriter, ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "common_upgrade"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.UNCOMMON_UPGRADE.get())
                .requires(io.redspace.ironsspellbooks.registries.ItemRegistry.UPGRADE_ORB.get())
                .requires(io.redspace.ironsspellbooks.registries.ItemRegistry.INK_UNCOMMON.get(), 4)
                .unlockedBy("has_orb", has(io.redspace.ironsspellbooks.registries.ItemRegistry.UPGRADE_ORB.get()))
                .save(pWriter, ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "uncommon_upgrade"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.RARE_UPGRADE.get())
                .requires(io.redspace.ironsspellbooks.registries.ItemRegistry.UPGRADE_ORB.get())
                .requires(io.redspace.ironsspellbooks.registries.ItemRegistry.INK_RARE.get(), 4)
                .unlockedBy("has_orb", has(io.redspace.ironsspellbooks.registries.ItemRegistry.UPGRADE_ORB.get()))
                .save(pWriter, ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "rare_upgrade"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.EPIC_UPGRADE.get())
                .requires(io.redspace.ironsspellbooks.registries.ItemRegistry.UPGRADE_ORB.get())
                .requires(io.redspace.ironsspellbooks.registries.ItemRegistry.INK_EPIC.get(), 4)
                .unlockedBy("has_orb", has(io.redspace.ironsspellbooks.registries.ItemRegistry.UPGRADE_ORB.get()))
                .save(pWriter, ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "epic_upgrade"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.LEGENDARY_UPGRADE.get())
                .requires(io.redspace.ironsspellbooks.registries.ItemRegistry.UPGRADE_ORB.get())
                .requires(io.redspace.ironsspellbooks.registries.ItemRegistry.INK_LEGENDARY.get(), 4)
                .unlockedBy("has_orb", has(io.redspace.ironsspellbooks.registries.ItemRegistry.UPGRADE_ORB.get()))
                .save(pWriter, ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "legendary_upgrade"));
    }
}
