package com.relimer.ironsrestrictions.compat.alshanexfamiliars.registries;

import com.relimer.ironsrestrictions.compat.alshanexfamiliars.item.SoundManuscript;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredHolder;

import static com.relimer.ironsrestrictions.registries.ItemRegistry.ITEMS;

public class AFItemRegistry {
    public static DeferredHolder<Item, Item> SOUND_PAGE = null;
    public static DeferredHolder<Item, Item> SOUND_KNOWLEDGE_FRAGMENT = null;
    public static void registerCompatItems() {
        SOUND_PAGE = ITEMS.register("sound_manuscript", () -> new SoundManuscript(ItemPropertiesHelper.material().rarity(Rarity.EPIC)));
        SOUND_KNOWLEDGE_FRAGMENT  = ITEMS.register("sound_knowledge_fragment", () -> new Item(ItemPropertiesHelper.material().rarity(Rarity.UNCOMMON)));
    }
}
