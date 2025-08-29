package com.relimer.ironsrestrictions.datagen;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.loot.AddItemModifier;
import com.relimer.ironsrestrictions.loot.ChestLootCondition;
import com.relimer.ironsrestrictions.registries.ItemRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

import java.util.concurrent.CompletableFuture;

public class LootModifierProvider extends GlobalLootModifierProvider {
    public LootModifierProvider(PackOutput output) {
        super(output, IronsRestrictions.MODID);
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
