package com.relimer.ironsrestrictions.registries;

import com.relimer.ironsrestrictions.command.LearnRarityCommand;
import io.redspace.ironsspellbooks.command.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber
public class CommandRegistry {
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {

        var commandDispatcher = event.getDispatcher();

        LearnRarityCommand.register(commandDispatcher);

    }
}
