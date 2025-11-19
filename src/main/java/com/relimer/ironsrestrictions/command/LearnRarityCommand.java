package com.relimer.ironsrestrictions.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.relimer.ironsrestrictions.player.PlayerRarityProvider;
import com.relimer.ironsrestrictions.util.ConfigurableRarity;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.server.command.EnumArgument;

public class LearnRarityCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralCommandNode<CommandSourceStack> command = dispatcher.register(Commands.literal("rarity")
                .requires((p) -> p.hasPermission(2))
                .then(Commands.literal("set").then(Commands.argument("rarity", EnumArgument.enumArgument(ConfigurableRarity.class))
                        .executes(context -> {
                            ConfigurableRarity rarity = context.getArgument("rarity", ConfigurableRarity.class);
                            return set(context.getSource(), rarity);
                        })
                ))
        );
    }

    private static int set(CommandSourceStack source, ConfigurableRarity rarity) {
        ServerPlayer player = source.getPlayer();

        player.getCapability(PlayerRarityProvider.SYNCED_RARITY).ifPresent(rarityData -> {
            rarityData.getSyncedData().setRarity(rarity);
        });

        source.sendSuccess(() -> source.getDisplayName().copy().append(" set to rarity: " + rarity.name()), false);

        return 1;
    }
}
