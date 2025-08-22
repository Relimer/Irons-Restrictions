package com.relimer.ironsrestrictions.registries;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.item.*;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collection;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, IronsRestrictions.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
    public static final DeferredHolder<Item, Item> MANUSCRIPT = ITEMS.register("manuscript", Manuscript::new);
    public static final DeferredHolder<Item, Item> UNFINISHED_MANUSCRIPT = ITEMS.register("unfinished_manuscript", UnfinishedManuscript::new);
    public static final DeferredHolder<Item, Item> FRAGMENT = ITEMS.register("fragment", () -> new Item(ItemPropertiesHelper.material().rarity(Rarity.UNCOMMON)));

    public static Collection<DeferredHolder<Item, ? extends Item>> getIronsRestrictionsItems() {
        return ITEMS.getEntries();
    }
}
