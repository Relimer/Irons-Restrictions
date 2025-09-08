package com.relimer.ironsrestrictions.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.relimer.ironsrestrictions.network.spells.ClientRarityData;
import com.relimer.ironsrestrictions.network.spells.SyncPlayerRarityDataPacket;
import com.relimer.ironsrestrictions.network.spells.SyncedRarityData;
import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.command.SpellArgument;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.server.command.EnumArgument;

public class LearnRarityCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralCommandNode<CommandSourceStack> command = dispatcher.register(Commands.literal("rarity")
                .requires((p) -> p.hasPermission(2))
                .then(Commands.literal("set").then(Commands.argument("rarity", EnumArgument.enumArgument(SpellRarity.class))
                        .executes(context -> {
                            SpellRarity rarity = context.getArgument("rarity", SpellRarity.class);
                            return set(context.getSource(), rarity);
                        })
                ))
        );
    }

    private static int set(CommandSourceStack source, SpellRarity rarity) {
        ServerPlayer player = source.getPlayer();

        SyncedRarityData data = new SyncedRarityData(player);
        data.setRarity(rarity);

        // Sync the data to the player
        PacketDistributor.sendToPlayer(player, new SyncPlayerRarityDataPacket(data));

        source.sendSuccess(() -> source.getDisplayName().copy().append(" set to rarity: " + rarity.name()), false);

        return 1;
    }
}
