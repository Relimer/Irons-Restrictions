package com.relimer.ironsrestrictions.compat.cataclysmspellbooks.registries;

import com.relimer.ironsrestrictions.compat.alshanexfamiliars.item.SoundManuscript;
import com.relimer.ironsrestrictions.compat.cataclysmspellbooks.item.AbyssalManuscript;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredHolder;

import static com.relimer.ironsrestrictions.registries.ItemRegistry.ITEMS;

public class CSItemRegistry {
    public static DeferredHolder<Item, Item> ABYSSAL_PAGE = null;
    public static DeferredHolder<Item, Item> ABYSSAL_KNOWLEDGE_FRAGMENT = null;
    public static void registerCompatItems() {
        ABYSSAL_PAGE = ITEMS.register("abyssal_manuscript", () -> new AbyssalManuscript(ItemPropertiesHelper.material().rarity(Rarity.EPIC)));
        ABYSSAL_KNOWLEDGE_FRAGMENT  = ITEMS.register("abyssal_knowledge_fragment", () -> new Item(ItemPropertiesHelper.material().rarity(Rarity.UNCOMMON)));
    }
}
