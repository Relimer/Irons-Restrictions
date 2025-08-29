package com.relimer.ironsrestrictions.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.item.ManuscriptData;
import com.relimer.ironsrestrictions.registries.ItemRegistry;
import com.relimer.ironsrestrictions.util.RecipeUtils;
import com.relimer.ironsrestrictions.util.SchoolContainer;
import com.relimer.ironsrestrictions.util.SchoolUtils;
import io.redspace.ironsspellbooks.ironsrestrictionsmixin.SchoolTypeAccessorMixin;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

public class RRecipeProvider extends RecipeProvider {


    public RRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        SchoolUtils.getLoopSchools().forEach(holder -> {
            ItemStack itemStack = new ItemStack(ItemRegistry.MANUSCRIPT.get());
            ManuscriptData.setSchoolContainer(itemStack, new SchoolContainer(holder));

            JsonObject recipe = new JsonObject();
            recipe.addProperty("type", "minecraft:crafting_shaped");

            JsonArray pattern = new JsonArray();
            pattern.add(" A ");
            pattern.add("ABA");
            pattern.add(" A ");
            recipe.add("pattern", pattern);

            JsonObject a = new JsonObject();
            a.addProperty("item", ItemRegistry.FRAGMENT.getId().toString());
            JsonObject b = new JsonObject();
            b.addProperty("tag", ((SchoolTypeAccessorMixin) holder).getFocus().location().toString());
            JsonObject key = new JsonObject();
            key.add("A", a);
            key.add("B", b);
            recipe.add("key", key);

            JsonObject result = new JsonObject();
            result.addProperty("item", ItemRegistry.MANUSCRIPT.getId().toString());
            result.add("nbt", ManuscriptData.createNBT(new SchoolContainer(holder))); // ← Convert your NBT to Json
            result.addProperty("count", 1);

            recipe.add("result", result);
            RecipeUtils.saveCustomRecipe(ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, holder.getId().getPath() + "_manuscript"), recipe, pWriter);
        });
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.FRAGMENT.get(), 2)
                .requires(io.redspace.ironsspellbooks.registries.ItemRegistry.SCROLL.get())
                .unlockedBy("has_scroll", has(io.redspace.ironsspellbooks.registries.ItemRegistry.SCROLL.get()))
                .save(pWriter, ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "fragment"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.UNFINISHED_MANUSCRIPT.get())
                .requires(ItemRegistry.FRAGMENT.get())
                .requires(io.redspace.ironsspellbooks.registries.ItemRegistry.MAGIC_CLOTH.get(), 4)
                .unlockedBy("has_fragment", has(ItemRegistry.FRAGMENT.get()))
                .save(pWriter, ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "unfinished_manuscript"));
    }
}
