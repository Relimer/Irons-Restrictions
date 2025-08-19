package com.relimer.ironsrestrictions.datagen;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.loot.AddItemModifier;
import com.relimer.ironsrestrictions.loot.ChestLootCondition;
import com.relimer.ironsrestrictions.registries.ItemRegistry;
import com.relimer.ironsrestrictions.setup.CommonCompatSetup;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class LootModifierProvider extends GlobalLootModifierProvider {
    public LootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, IronsRestrictions.MODID);
    }

    @Override
    protected void start() {
        this.add("fire_knowledge_fragment",
                new AddItemModifier(new LootItemCondition[]{
                        new ChestLootCondition(),
                        new LocationCheck(Optional.of(LocationPredicate.Builder.inDimension(Level.NETHER).build()), new BlockPos(0,0,0)),
                        LootItemRandomChanceCondition.randomChance(0.5f).build(),
                }, ItemRegistry.FIRE_KNOWLEDGE_FRAGMENT.get(), 4));
        this.add("fire_manuscript",
                new AddItemModifier(new LootItemCondition[]{
                        new ChestLootCondition(),
                        new LocationCheck(Optional.of(LocationPredicate.Builder.inDimension(Level.NETHER).build()), new BlockPos(0,0,0)),
                        LootItemRandomChanceCondition.randomChance(0.2f).build()
                }, ItemRegistry.FIRE_PAGE.get(), 1));

        this.add("nature_knowledge_fragment",
                new AddItemModifier(new LootItemCondition[]{
                        new ChestLootCondition(),
                        new LocationCheck(Optional.of(LocationPredicate.Builder.inDimension(Level.OVERWORLD).build()), new BlockPos(0,0,0)),
                        LootItemRandomChanceCondition.randomChance(0.5f).build()
                }, ItemRegistry.NATURE_KNOWLEDGE_FRAGMENT.get(), 4));
        this.add("nature_manuscript",
                new AddItemModifier(new LootItemCondition[]{
                        new ChestLootCondition(),
                        new LocationCheck(Optional.of(LocationPredicate.Builder.inDimension(Level.OVERWORLD).build()), new BlockPos(0,0,0)),
                        LootItemRandomChanceCondition.randomChance(0.2f).build()
                }, ItemRegistry.NATURE_PAGE.get(), 1));

        this.add("evocation_knowledge_fragment",
                new AddItemModifier(new LootItemCondition[]{
                        new ChestLootCondition(),
                        new LocationCheck(Optional.of(LocationPredicate.Builder.inDimension(Level.OVERWORLD).build()), new BlockPos(0,0,0)),
                        LootItemRandomChanceCondition.randomChance(0.5f).build()
                }, ItemRegistry.EVOCATION_KNOWLEDGE_FRAGMENT.get(), 4));
        this.add("evocation_manuscript",
                new AddItemModifier(new LootItemCondition[]{
                        new ChestLootCondition(),
                        new LocationCheck(Optional.of(LocationPredicate.Builder.inDimension(Level.OVERWORLD).build()), new BlockPos(0,0,0)),
                        LootItemRandomChanceCondition.randomChance(0.2f).build()
                }, ItemRegistry.EVOCATION_PAGE.get(), 1));

        this.add("ender_knowledge_fragment",
                new AddItemModifier(new LootItemCondition[]{
                        new ChestLootCondition(),
                        new LocationCheck(Optional.of(LocationPredicate.Builder.inDimension(Level.END).build()), new BlockPos(0,0,0)),
                        LootItemRandomChanceCondition.randomChance(0.5f).build()
                }, ItemRegistry.ENDER_KNOWLEDGE_FRAGMENT.get(), 4));
        this.add("ender_manuscript",
                new AddItemModifier(new LootItemCondition[]{
                        new ChestLootCondition(),
                        new LocationCheck(Optional.of(LocationPredicate.Builder.inDimension(Level.END).build()), new BlockPos(0,0,0)),
                        LootItemRandomChanceCondition.randomChance(0.2f).build()
                }, ItemRegistry.ENDER_PAGE.get(),1));

        this.add("ice_knowledge_fragment",
                new AddItemModifier(new LootItemCondition[]{
                        new ChestLootCondition(),
                        new LocationCheck(Optional.of(LocationPredicate.Builder.inDimension(Level.OVERWORLD).build()), new BlockPos(0,0,0)),
                        LootItemRandomChanceCondition.randomChance(0.5f).build()
                }, ItemRegistry.ICE_KNOWLEDGE_FRAGMENT.get(), 4));
        this.add("ice_manuscript",
                new AddItemModifier(new LootItemCondition[]{
                        new ChestLootCondition(),
                        new LocationCheck(Optional.of(LocationPredicate.Builder.inDimension(Level.OVERWORLD).build()), new BlockPos(0,0,0)),
                        LootItemRandomChanceCondition.randomChance(0.2f).build()
                }, ItemRegistry.ICE_PAGE.get(),1));

        this.add("holy_knowledge_fragment",
                new AddItemModifier(new LootItemCondition[]{
                        new ChestLootCondition(),
                        new LocationCheck(Optional.of(LocationPredicate.Builder.inDimension(Level.OVERWORLD).build()), new BlockPos(0,0,0)),
                        LootItemRandomChanceCondition.randomChance(0.3f).build()
                }, ItemRegistry.HOLY_KNOWLEDGE_FRAGMENT.get(), 4));
        this.add("holy_manuscript",
                new AddItemModifier(new LootItemCondition[]{
                        new ChestLootCondition(),
                        new LocationCheck(Optional.of(LocationPredicate.Builder.inDimension(Level.OVERWORLD).build()), new BlockPos(0,0,0)),
                        LootItemRandomChanceCondition.randomChance(0.1f).build()
                }, ItemRegistry.HOLY_PAGE.get(),1));

        this.add("blood_knowledge_fragment",
                new AddItemModifier(new LootItemCondition[]{
                        new ChestLootCondition(),
                        new LocationCheck(Optional.of(LocationPredicate.Builder.inDimension(Level.OVERWORLD).build()), new BlockPos(0,0,0)),
                        LootItemRandomChanceCondition.randomChance(0.5f).build()
                }, ItemRegistry.BLOOD_KNOWLEDGE_FRAGMENT.get(), 4));
        this.add("blood_manuscript",
                new AddItemModifier(new LootItemCondition[]{
                        new ChestLootCondition(),
                        new LocationCheck(Optional.of(LocationPredicate.Builder.inDimension(Level.OVERWORLD).build()), new BlockPos(0,0,0)),
                        LootItemRandomChanceCondition.randomChance(0.2f).build()
                }, ItemRegistry.BLOOD_PAGE.get(),1));

        this.add("lightning_knowledge_fragment",
                new AddItemModifier(new LootItemCondition[]{
                        new ChestLootCondition(),
                        new LocationCheck(Optional.of(LocationPredicate.Builder.inDimension(Level.OVERWORLD).build()), new BlockPos(0,0,0)),
                        LootItemRandomChanceCondition.randomChance(0.5f).build()
                }, ItemRegistry.LIGHTNING_KNOWLEDGE_FRAGMENT.get(), 4));
        this.add("lightning_manuscript",
                new AddItemModifier(new LootItemCondition[]{
                        new ChestLootCondition(),
                        new LocationCheck(Optional.of(LocationPredicate.Builder.inDimension(Level.OVERWORLD).build()), new BlockPos(0,0,0)),
                        LootItemRandomChanceCondition.randomChance(0.2f).build()
                }, ItemRegistry.LIGHTNING_PAGE.get(),1));
        CommonCompatSetup.lootModifierProviders(this);
    }
}
