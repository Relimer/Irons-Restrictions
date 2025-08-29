package com.relimer.ironsrestrictions.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.relimer.ironsrestrictions.registries.LootConditionRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

import java.util.Set;

public class ChestLootCondition implements LootItemCondition {

    public static final Serializer<ChestLootCondition> SERIALIZER = new Serializer<ChestLootCondition>() {
        @Override
        public void serialize(JsonObject json, ChestLootCondition condition, JsonSerializationContext context) {
        }

        @Override
        public ChestLootCondition deserialize(JsonObject json, JsonDeserializationContext context) {
            return new ChestLootCondition();
        }
    };

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
