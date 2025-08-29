package com.relimer.ironsrestrictions.util;

import com.google.gson.JsonObject;
import com.relimer.ironsrestrictions.registries.ItemRegistry;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;

import java.util.function.Consumer;

public class RecipeUtils {

    public static void saveCustomRecipe(ResourceLocation id, JsonObject json, Consumer<FinishedRecipe> pWriter) {
        Advancement.Builder advancementBuilder = Advancement.Builder.advancement()
                .parent(ResourceLocation.parse("minecraft:recipes/root"))
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
                .addCriterion("has_fragment", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item()
                        .of(ItemRegistry.FRAGMENT.get()).build()))
                .rewards(AdvancementRewards.Builder.recipe(id))
                .requirements(RequirementsStrategy.OR);
        pWriter.accept(new FinishedRecipe() {
            @Override
            public void serializeRecipeData(JsonObject jsonOut) {
                jsonOut.addProperty("type", json.get("type").getAsString());
                jsonOut.add("pattern", json.get("pattern"));
                jsonOut.add("key", json.get("key"));
                jsonOut.add("result", json.get("result"));
            }

            @Override
            public ResourceLocation getId() {
                return id;
            }

            @Override
            public RecipeSerializer<?> getType() {
                return RecipeSerializer.SHAPED_RECIPE;
            }

            @Override
            public JsonObject serializeAdvancement() {
                return advancementBuilder.serializeToJson();
            }

            @Override
            public ResourceLocation getAdvancementId() {
                return ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "recipes/misc/" + id.getPath());
            }
        });
    }
}

