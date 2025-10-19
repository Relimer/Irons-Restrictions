package com.relimer.ironsrestrictions.registries;

import com.relimer.ironsrestrictions.command.LearnRarityCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CommandRegistry {
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {

        var commandDispatcher = event.getDispatcher();

        LearnRarityCommand.register(commandDispatcher);

    }
}
