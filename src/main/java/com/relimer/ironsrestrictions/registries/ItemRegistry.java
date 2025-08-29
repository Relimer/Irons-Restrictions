package com.relimer.ironsrestrictions.registries;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.item.*;
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

    public static Collection<RegistryObject<Item>> getIronsRestrictionsItems() {
        return ITEMS.getEntries();
    }
}
