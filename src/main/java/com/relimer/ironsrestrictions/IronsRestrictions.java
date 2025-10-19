package com.relimer.ironsrestrictions;
import com.relimer.ironsrestrictions.registries.*;
import com.relimer.ironsrestrictions.setup.Messages;
import io.redspace.ironsspellbooks.block.alchemist_cauldron.AlchemistCauldronRecipe;
import io.redspace.ironsspellbooks.block.alchemist_cauldron.AlchemistCauldronRecipeRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;


// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(IronsRestrictions.MODID)
public class IronsRestrictions {
    public static final String MODID = "irons_restrictions";
    public static final Logger LOGGER = LogUtils.getLogger();

    public IronsRestrictions(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
        modEventBus.addListener(this::commonSetup);

        ItemRegistry.register(modEventBus);
        CreativeTabRegistry.register(modEventBus);
        LootModifierRegistry.register(modEventBus);
        LootConditionRegistry.register(modEventBus);

        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
    private void commonSetup(final net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent event) {
        event.enqueueWork(Messages::register);
        }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = IronsRestrictions.MODID, value = Dist.CLIENT)
    static class ClientModEvents {
        @SubscribeEvent
        static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
    public static ResourceLocation id(@NotNull String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
