package com.relimer.ironsrestrictions.datagen;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.loot.AddItemModifier;
import com.relimer.ironsrestrictions.loot.ChestLootCondition;
import com.relimer.ironsrestrictions.registries.ComponentRegistry;
import com.relimer.ironsrestrictions.registries.ItemRegistry;
import com.relimer.ironsrestrictions.util.SchoolContainer;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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
        this.add("knowledge_fragment",
                new AddItemModifier(new LootItemCondition[]{
                        new ChestLootCondition(),
                        new LocationCheck(Optional.of(LocationPredicate.Builder.inDimension(Level.OVERWORLD).build()), new BlockPos(0, 0, 0)),
                        LootItemRandomChanceCondition.randomChance(0.5f).build()
                }, io.redspace.ironsspellbooks.registries.ItemRegistry.LOST_KNOWLEDGE_FRAGMENT.get(), 4));

    }
}
