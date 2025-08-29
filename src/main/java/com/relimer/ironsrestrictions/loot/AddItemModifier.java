package com.relimer.ironsrestrictions.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.redspace.ironsspellbooks.loot.AppendLootModifier;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

import java.util.Random;
import java.util.function.Supplier;

public class AddItemModifier extends LootModifier {
    public static final Supplier<Codec<AddItemModifier>> CODEC = Suppliers.memoize(() ->
            RecordCodecBuilder.create(inst -> codecStart(inst).and(
                            BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(e -> e.item))
                    .and(Codec.INT.optionalFieldOf("count",1).forGetter(m -> m.count))
                    .apply(inst, AddItemModifier::new)));
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
            if(condition instanceof LootItemRandomChanceCondition lootItemRandomChanceCondition) {
                for (int i = 1; i < count; i++) {
                    if (lootItemRandomChanceCondition.test(context)) {
                        c++;
                    }
                }
            }
        }
        generatedLoot.add(new ItemStack(item, c));
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
