package com.relimer.ironsrestrictions.compat.cataclysmspellbooks.datagen;

import com.relimer.ironsrestrictions.compat.alshanexfamiliars.registries.AFItemRegistry;
import com.relimer.ironsrestrictions.compat.cataclysmspellbooks.registries.CSItemRegistry;
import com.relimer.ironsrestrictions.datagen.LootModifierProvider;
import com.relimer.ironsrestrictions.loot.AddItemModifier;
import com.relimer.ironsrestrictions.loot.ChestLootCondition;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;

import java.util.Optional;

public class CSLootModifierProvider {
    public static void add(LootModifierProvider lootModifierProvider) {
        lootModifierProvider.add("sound_manuscript",
                new AddItemModifier(new LootItemCondition[]{
                        new ChestLootCondition(),
                        new LocationCheck(Optional.of(LocationPredicate.Builder.inDimension(Level.OVERWORLD).build()), new BlockPos(0,0,0)),
                        LootItemRandomChanceCondition.randomChance(0.2f).build()
                }, CSItemRegistry.ABYSSAL_PAGE.get(),1));
        lootModifierProvider.add("sound_knowledge_fragment",
                new AddItemModifier(new LootItemCondition[]{
                        new ChestLootCondition(),
                        new LocationCheck(Optional.of(LocationPredicate.Builder.inDimension(Level.OVERWORLD).build()), new BlockPos(0,0,0)),
                        LootItemRandomChanceCondition.randomChance(0.5f).build()
                }, CSItemRegistry.ABYSSAL_KNOWLEDGE_FRAGMENT.get(), 4));
    }
}
