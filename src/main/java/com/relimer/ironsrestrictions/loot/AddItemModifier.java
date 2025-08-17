package com.relimer.ironsrestrictions.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

public class AddItemModifier extends LootModifier {
    public static final MapCodec<AddItemModifier> CODEC = RecordCodecBuilder.mapCodec(inst ->
            LootModifier.codecStart(inst).and(
                    BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(e -> e.item))
                    .and(Codec.INT.optionalFieldOf("count",1).forGetter(m -> m.count))
                    .apply(inst, AddItemModifier::new));
    private  final Item item;
    private Integer count;

    public AddItemModifier(LootItemCondition[] conditionsIn, Item item, Integer count) {
        super(conditionsIn);
        this.item = item;
        this.count = count;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {

        int c = 1;
        for (LootItemCondition condition : this.conditions) {
            if(!condition.test(context)) {
                return generatedLoot;
            }
            if(condition instanceof LootItemRandomChanceCondition) {
                NumberProvider chanceProvider = ((LootItemRandomChanceCondition) condition).chance();
                float chance = chanceProvider.getFloat(context);
                for (int i = 1; i < count; i++) {
                    if (context.getRandom().nextFloat() < chance) {
                        c++;
                    }
                }
            }
        }
        generatedLoot.add(new ItemStack(item, c));
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
