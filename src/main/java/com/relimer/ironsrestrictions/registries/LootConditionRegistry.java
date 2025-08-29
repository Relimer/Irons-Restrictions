package com.relimer.ironsrestrictions.registries;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.loot.ChestLootCondition;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class LootConditionRegistry {
    public static final DeferredRegister<LootItemConditionType> LOOT_CONDITIONS =
            DeferredRegister.create(Registries.LOOT_CONDITION_TYPE, IronsRestrictions.MODID);

    public static final Supplier<LootItemConditionType> IS_CHEST_LOOT =
            LOOT_CONDITIONS.register("is_chest_loot",
                    () -> new LootItemConditionType(ChestLootCondition.SERIALIZER));

    public static void register(IEventBus eventBus) {
        LOOT_CONDITIONS.register(eventBus);
    }
}
