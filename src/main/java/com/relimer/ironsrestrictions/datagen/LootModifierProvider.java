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
        this.add("fragment",
                new AddItemModifier(new LootItemCondition[]{
                        new ChestLootCondition(),
                        LootItemRandomChanceCondition.randomChance(0.6f).build()
                }, ItemRegistry.FRAGMENT.get(), 4));
        this.add("unfinished_manuscript",
                new AddItemModifier(new LootItemCondition[]{
                        new ChestLootCondition(),
                        LootItemRandomChanceCondition.randomChance(0.3f).build()
                }, ItemRegistry.UNFINISHED_MANUSCRIPT.get(), 1));

    }
}
