package com.relimer.ironsrestrictions.registries;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.item.*;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, IronsRestrictions.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
    public static final RegistryObject<Item> MANUSCRIPT = ITEMS.register("manuscript", Manuscript::new);
    public static final RegistryObject<Item> FRAGMENT = ITEMS.register("fragment", () -> new Item(ItemPropertiesHelper.material().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> UNFINISHED_MANUSCRIPT = ITEMS.register("unfinished_manuscript", UnfinishedManuscript::new);

    public static final RegistryObject<Item> COMMON_UPGRADE = ITEMS.register("common_upgrade", () -> new RarityUpgrade(SpellRarity.COMMON, null, 0));
    public static final RegistryObject<Item> UNCOMMON_UPGRADE = ITEMS.register("uncommon_upgrade", () -> new RarityUpgrade(SpellRarity.UNCOMMON, SpellRarity.COMMON, 5));
    public static final RegistryObject<Item> RARE_UPGRADE = ITEMS.register("rare_upgrade", () -> new RarityUpgrade(SpellRarity.RARE, SpellRarity.UNCOMMON, 10));
    public static final RegistryObject<Item> EPIC_UPGRADE = ITEMS.register("epic_upgrade", () -> new RarityUpgrade(SpellRarity.EPIC, SpellRarity.RARE, 15));
    public static final RegistryObject<Item> LEGENDARY_UPGRADE = ITEMS.register("legendary_upgrade", () -> new RarityUpgrade(SpellRarity.LEGENDARY, SpellRarity.EPIC, 20));

    public static Collection<RegistryObject<Item>> getIronsRestrictionsItems() {
        return ITEMS.getEntries();
    }
}
