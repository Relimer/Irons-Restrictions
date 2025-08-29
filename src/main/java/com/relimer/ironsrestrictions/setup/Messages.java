package com.relimer.ironsrestrictions.setup;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.network.OpenSchoolScreenPacket;
import com.relimer.ironsrestrictions.network.spells.RLearnSpellPacket;
import io.redspace.ironsspellbooks.network.ServerboundLearnSpell;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class Messages {
    private static SimpleChannel INSTANCE;
    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }
    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();
        INSTANCE = net;
        net.messageBuilder(OpenSchoolScreenPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(OpenSchoolScreenPacket::new)
                .encoder(OpenSchoolScreenPacket::toBytes)
                .consumerMainThread(OpenSchoolScreenPacket::handle)
                .add();
        net.messageBuilder(RLearnSpellPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(RLearnSpellPacket::new)
                .encoder(RLearnSpellPacket::toBytes)
                .consumerMainThread(RLearnSpellPacket::handle)
                .add();
    }
    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);

    }

    public static <MSG> void sendToAllPlayers(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }

    public static <MSG> void sendToPlayersTrackingEntity(MSG message, Entity entity) {
        sendToPlayersTrackingEntity(message, entity, false);
    }

    public static <MSG> void sendToPlayersTrackingEntity(MSG message, Entity entity, boolean sendToSource) {
        INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), message);
        if (sendToSource && entity instanceof ServerPlayer serverPlayer)
            sendToPlayer(message, serverPlayer);
    }
}
