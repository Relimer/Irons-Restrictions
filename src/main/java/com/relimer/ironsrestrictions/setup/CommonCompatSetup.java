package com.relimer.ironsrestrictions.setup;

import com.relimer.ironsrestrictions.compat.alshanexfamiliars.datagen.AFLootModifierProvider;
import com.relimer.ironsrestrictions.compat.alshanexfamiliars.datagen.AFRecipeProvider;
import com.relimer.ironsrestrictions.compat.alshanexfamiliars.network.OpenSoundScreenPacket;
import com.relimer.ironsrestrictions.compat.alshanexfamiliars.registries.AFFillCreativeTabs;
import com.relimer.ironsrestrictions.compat.alshanexfamiliars.registries.AFItemRegistry;
import com.relimer.ironsrestrictions.compat.alshanexfamiliars.registries.AFRSchoolRegistry;
import com.relimer.ironsrestrictions.datagen.LootModifierProvider;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import net.alshanex.alshanex_familiars.registry.AFSchoolRegistry;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import java.util.HashMap;
import java.util.Map;

public class CommonCompatSetup {
    public static void registerItems() {
        if(ModList.get().isLoaded("alshanex_familiars")) {
            AFItemRegistry.registerCompatItems();
        }
    }
    public static void fillCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if(ModList.get().isLoaded("alshanex_familiars")) {
            AFFillCreativeTabs.addCompatItems(event);
        }
    }

    public static void registerSchools() {
        if(ModList.get().isLoaded("alshanex_familiars")) {
            AFRSchoolRegistry.register();
        }
    }
    public static boolean isPage(ItemStack itemStack) {
        if(ModList.get().isLoaded("alshanex_familiars")) {
            if(itemStack.is(AFItemRegistry.SOUND_PAGE)) return true;
        }
        return false;
    }

    public static Map<SchoolType, SchoolType> putSchoolRemap(Map<SchoolType, SchoolType> schoolRemap) {
        Map<SchoolType, SchoolType> remap = new HashMap<SchoolType, SchoolType>(schoolRemap);
        if(ModList.get().isLoaded("alshanex_familiars")) {
            remap.put(AFSchoolRegistry.SOUND.get(), AFRSchoolRegistry.SOUND.get());
        }
        return remap;
    }
    public static void payloadHandler(PayloadRegistrar payloadRegistrar) {
        if(ModList.get().isLoaded("alshanex_familiars")) {
            payloadRegistrar.playToClient(OpenSoundScreenPacket.TYPE, OpenSoundScreenPacket.STREAM_CODEC, OpenSoundScreenPacket::handle);
        }
    }

    public static void lootModifierProviders(LootModifierProvider lootModifierProvider) {
        if(ModList.get().isLoaded("alshanex_familiars")) {
            AFLootModifierProvider.add(lootModifierProvider);
        }
    }
    public static void recipeProvider(RecipeOutput recipeOutput) {
        if(ModList.get().isLoaded("alshanex_familiars")) {
            AFRecipeProvider.add(recipeOutput);
        }
    }

}

