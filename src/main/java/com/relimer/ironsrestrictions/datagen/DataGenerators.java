package com.relimer.ironsrestrictions.datagen;

import com.relimer.ironsrestrictions.IronsRestrictions;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = IronsRestrictions.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // Add data providers
        if (event.includeServer()) {
            generator.addProvider(true, new LootModifierProvider(packOutput));
            generator.addProvider(true, new RRecipeProvider(packOutput));
        }

        if (event.includeClient()) {
            generator.addProvider(true, new RModelProvider(packOutput, IronsRestrictions.MODID, existingFileHelper));
        }
    }
}
