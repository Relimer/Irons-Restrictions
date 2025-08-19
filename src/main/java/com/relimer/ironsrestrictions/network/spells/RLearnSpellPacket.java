package com.relimer.ironsrestrictions.network.spells;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.registries.ItemRegistry;
import com.relimer.ironsrestrictions.setup.CommonCompatSetup;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class RLearnSpellPacket implements CustomPacketPayload {
    private final byte hand;
    private final String spell;

    public static final CustomPacketPayload.Type<RLearnSpellPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "learn_spell"));
    public static final StreamCodec<RegistryFriendlyByteBuf, RLearnSpellPacket> STREAM_CODEC = CustomPacketPayload.codec(RLearnSpellPacket::write, RLearnSpellPacket::new);

    public RLearnSpellPacket(InteractionHand interactionHand, String spell) {
        this.hand = handToByte(interactionHand);
        this.spell = spell;
    }

    public RLearnSpellPacket(FriendlyByteBuf buf) {
        hand = buf.readByte();
        spell = buf.readUtf();
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeByte(hand);
        buf.writeUtf(spell);
    }

    public static void handle(RLearnSpellPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer serverPlayer) {
                ItemStack itemStack = serverPlayer.getItemInHand(byteToHand(packet.hand));
                AbstractSpell spell = SpellRegistry.getSpell(packet.spell);
                var data = MagicData.getPlayerMagicData(serverPlayer).getSyncedData();
                var page = itemStack.is(ItemRegistry.FIRE_PAGE.get()) ||
                        itemStack.is(ItemRegistry.NATURE_PAGE.get()) ||
                        itemStack.is(ItemRegistry.EVOCATION_PAGE.get()) ||
                        itemStack.is(ItemRegistry.ENDER_PAGE.get()) ||
                        itemStack.is(ItemRegistry.ICE_PAGE.get()) ||
                        itemStack.is(ItemRegistry.HOLY_PAGE.get()) ||
                        itemStack.is(ItemRegistry.BLOOD_PAGE.get()) ||
                        itemStack.is(ItemRegistry.LIGHTNING_PAGE.get()) ||
                        CommonCompatSetup.isPage(itemStack);
                if (spell != SpellRegistry.none() && !data.isSpellLearned(spell) && page && itemStack.getCount() > 0) {
                    data.learnSpell(spell);
                    if (!serverPlayer.getAbilities().instabuild) {
                        itemStack.shrink(1);
                    }
                }
            }
        });
    }

    public static byte handToByte(InteractionHand hand) {
        return (byte) (hand == InteractionHand.MAIN_HAND ? 1 : 0);
    }

    public static InteractionHand byteToHand(byte b) {
        return b > 0 ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
