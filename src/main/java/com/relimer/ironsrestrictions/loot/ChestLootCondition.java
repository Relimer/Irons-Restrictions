package com.relimer.ironsrestrictions.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.relimer.ironsrestrictions.registries.LootConditionRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

import java.util.Set;

public class ChestLootCondition implements LootItemCondition {
    public static final MapCodec<ChestLootCondition> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    // No fields, so pass a dummy unit to satisfy group()?
                    Codec.BOOL.fieldOf("dummy").forGetter(c -> true)  // always true, ignored
            ).apply(instance, (dummy) -> new ChestLootCondition())
    );


    @Override
    public LootItemConditionType getType() {
        return LootConditionRegistry.IS_CHEST_LOOT.get();
    }

    @Override
    public boolean test(LootContext context) {
        ResourceLocation lootTableId = context.getQueriedLootTableId();
        return lootTableId != null && lootTableId.getPath().startsWith("chests/");
    }

    @Override
    public Set<LootContextParam<?>> getReferencedContextParams() {
        return Set.of();
    }
}
