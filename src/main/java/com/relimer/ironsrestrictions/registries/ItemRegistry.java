package com.relimer.ironsrestrictions.registries;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.item.*;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collection;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, IronsRestrictions.MODID);

    public static void register(IEventBus eventBus) { ITEMS.register(eventBus); }

    public static final DeferredHolder<Item, Item> FIRE_PAGE = ITEMS.register("fire_manuscript", () -> new FireManuscript(ItemPropertiesHelper.material().rarity(Rarity.EPIC)));
    public static final DeferredHolder<Item, Item> NATURE_PAGE = ITEMS.register("nature_manuscript", () -> new NatureManuscript(ItemPropertiesHelper.material().rarity(Rarity.EPIC)));
    public static final DeferredHolder<Item, Item> EVOCATION_PAGE = ITEMS.register("evocation_manuscript", () -> new EvocationManuscript(ItemPropertiesHelper.material().rarity(Rarity.EPIC)));
    public static final DeferredHolder<Item, Item> ENDER_PAGE = ITEMS.register("ender_manuscript", () -> new EnderManuscript(ItemPropertiesHelper.material().rarity(Rarity.EPIC)));
    public static final DeferredHolder<Item, Item> ICE_PAGE = ITEMS.register("ice_manuscript", () -> new IceManuscript(ItemPropertiesHelper.material().rarity(Rarity.EPIC)));
    public static final DeferredHolder<Item, Item> HOLY_PAGE = ITEMS.register("holy_manuscript", () -> new HolyManuscript(ItemPropertiesHelper.material().rarity(Rarity.EPIC)));
    public static final DeferredHolder<Item, Item> BLOOD_PAGE = ITEMS.register("blood_manuscript", () -> new BloodManuscript(ItemPropertiesHelper.material().rarity(Rarity.EPIC)));
    public static final DeferredHolder<Item, Item> LIGHTNING_PAGE = ITEMS.register("lightning_manuscript", () -> new LightningManuscript(ItemPropertiesHelper.material().rarity(Rarity.EPIC)));

    public static final DeferredHolder<Item, Item> FIRE_KNOWLEDGE_FRAGMENT = ITEMS.register("fire_knowledge_fragment", () -> new Item(ItemPropertiesHelper.material().rarity(Rarity.UNCOMMON)));
    public static final DeferredHolder<Item, Item> NATURE_KNOWLEDGE_FRAGMENT = ITEMS.register("nature_knowledge_fragment", () -> new Item(ItemPropertiesHelper.material().rarity(Rarity.UNCOMMON)));
    public static final DeferredHolder<Item, Item> EVOCATION_KNOWLEDGE_FRAGMENT = ITEMS.register("evocation_knowledge_fragment", () -> new Item(ItemPropertiesHelper.material().rarity(Rarity.UNCOMMON)));
    public static final DeferredHolder<Item, Item> ENDER_KNOWLEDGE_FRAGMENT = ITEMS.register("ender_knowledge_fragment", () -> new Item(ItemPropertiesHelper.material().rarity(Rarity.UNCOMMON)));
    public static final DeferredHolder<Item, Item> ICE_KNOWLEDGE_FRAGMENT = ITEMS.register("ice_knowledge_fragment", () -> new Item(ItemPropertiesHelper.material().rarity(Rarity.UNCOMMON)));
    public static final DeferredHolder<Item, Item> HOLY_KNOWLEDGE_FRAGMENT = ITEMS.register("holy_knowledge_fragment", () -> new Item(ItemPropertiesHelper.material().rarity(Rarity.UNCOMMON)));
    public static final DeferredHolder<Item, Item> BLOOD_KNOWLEDGE_FRAGMENT = ITEMS.register("blood_knowledge_fragment", () -> new Item(ItemPropertiesHelper.material().rarity(Rarity.UNCOMMON)));
    public static final DeferredHolder<Item, Item> LIGHTNING_KNOWLEDGE_FRAGMENT = ITEMS.register("lightning_knowledge_fragment", () -> new Item(ItemPropertiesHelper.material().rarity(Rarity.UNCOMMON)));

    public static Collection<DeferredHolder<Item, ? extends Item>> getIronsRestrictionsItems() {
        return ITEMS.getEntries();
    }
}
