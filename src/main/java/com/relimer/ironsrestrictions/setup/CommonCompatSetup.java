package com.relimer.ironsrestrictions.setup;

import com.relimer.ironsrestrictions.compat.alshanexfamiliars.datagen.AFLootModifierProvider;
import com.relimer.ironsrestrictions.compat.alshanexfamiliars.datagen.AFRecipeProvider;
import com.relimer.ironsrestrictions.compat.alshanexfamiliars.network.OpenSoundScreenPacket;
import com.relimer.ironsrestrictions.compat.alshanexfamiliars.registries.AFFillCreativeTabs;
import com.relimer.ironsrestrictions.compat.alshanexfamiliars.registries.AFItemRegistry;
import com.relimer.ironsrestrictions.compat.alshanexfamiliars.registries.AFRSchoolRegistry;
import com.relimer.ironsrestrictions.compat.cataclysmspellbooks.datagen.CSLootModifierProvider;
import com.relimer.ironsrestrictions.compat.cataclysmspellbooks.datagen.CSRecipeProvider;
import com.relimer.ironsrestrictions.compat.cataclysmspellbooks.network.OpenAbyssalScreenPacket;
import com.relimer.ironsrestrictions.compat.cataclysmspellbooks.registries.CSFillCreativeTabs;
import com.relimer.ironsrestrictions.compat.cataclysmspellbooks.registries.CSItemRegistry;
import com.relimer.ironsrestrictions.compat.cataclysmspellbooks.registries.CSRSchoolRegistry;
import com.relimer.ironsrestrictions.datagen.LootModifierProvider;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import net.acetheeldritchking.cataclysm_spellbooks.registries.CSSchoolRegistry;
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
        if(ModList.get().isLoaded("cataclysm_spellbooks")) {
            CSItemRegistry.registerCompatItems();
        }
    }
    public static void fillCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if(ModList.get().isLoaded("alshanex_familiars")) {
            AFFillCreativeTabs.addCompatItems(event);
        }if(ModList.get().isLoaded("cataclysm_spellbooks")) {
            CSFillCreativeTabs.addCompatItems(event);
        }
    }

    public static void registerSchools() {
        if(ModList.get().isLoaded("alshanex_familiars")) {
            AFRSchoolRegistry.register();
        }if(ModList.get().isLoaded("cataclysm_spellbooks")) {
            CSRSchoolRegistry.register();
        }
    }
    public static boolean isPage(ItemStack itemStack) {
        if(ModList.get().isLoaded("alshanex_familiars")) {
            if(itemStack.is(AFItemRegistry.SOUND_PAGE)) return true;
        }
        if(ModList.get().isLoaded("cataclysm_spellbooks")) {
            if(itemStack.is(CSItemRegistry.ABYSSAL_PAGE)) return true;
        }
        return false;
    }

    public static Map<SchoolType, SchoolType> putSchoolRemap(Map<SchoolType, SchoolType> schoolRemap) {
        Map<SchoolType, SchoolType> remap = new HashMap<SchoolType, SchoolType>(schoolRemap);
        if(ModList.get().isLoaded("alshanex_familiars")) {
            remap.put(AFSchoolRegistry.SOUND.get(), AFRSchoolRegistry.SOUND.get());
        }
        if(ModList.get().isLoaded("cataclysm_spellbooks")) {
            remap.put(CSSchoolRegistry.ABYSSAL.get(), CSRSchoolRegistry.ABYSSAL.get());
        }
        return remap;
    }
    public static void payloadHandler(PayloadRegistrar payloadRegistrar) {
        if(ModList.get().isLoaded("alshanex_familiars")) {
            payloadRegistrar.playToClient(OpenSoundScreenPacket.TYPE, OpenSoundScreenPacket.STREAM_CODEC, OpenSoundScreenPacket::handle);
        }
        if(ModList.get().isLoaded("cataclysm_spellbooks")) {
            payloadRegistrar.playToClient(OpenAbyssalScreenPacket.TYPE, OpenAbyssalScreenPacket.STREAM_CODEC, OpenAbyssalScreenPacket::handle);
        }
    }

    public static void lootModifierProviders(LootModifierProvider lootModifierProvider) {
        if(ModList.get().isLoaded("alshanex_familiars")) {
            AFLootModifierProvider.add(lootModifierProvider);
        }
        if(ModList.get().isLoaded("cataclysm_spellbooks")) {
            CSLootModifierProvider.add(lootModifierProvider);
        }
    }
    public static void recipeProvider(RecipeOutput recipeOutput) {
        if(ModList.get().isLoaded("alshanex_familiars")) {
            AFRecipeProvider.add(recipeOutput);
        }
        if(ModList.get().isLoaded("cataclysm_spellbooks")) {
            CSRecipeProvider.add(recipeOutput);
        }
    }

}

