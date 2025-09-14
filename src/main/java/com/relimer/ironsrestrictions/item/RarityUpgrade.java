package com.relimer.ironsrestrictions.item;

import com.relimer.ironsrestrictions.Config;
import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.anim.Animations;
import com.relimer.ironsrestrictions.network.spells.ClientRarityData;
import com.relimer.ironsrestrictions.network.spells.SyncPlayerRarityDataPacket;
import com.relimer.ironsrestrictions.network.spells.SyncedRarityData;
import dev.kosmx.playerAnim.api.layered.AnimationStack;
import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.api.layered.modifier.AbstractFadeModifier;
import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import dev.kosmx.playerAnim.core.util.Ease;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.SpellAnimations;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.player.ClientSpellCastHelper;
import io.redspace.ironsspellbooks.util.MinecraftInstanceHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RarityUpgrade extends Item {
    SpellRarity rarity;
    SpellRarity prevRarity;
    int requiredAdvancements;
    public RarityUpgrade(SpellRarity rarity, SpellRarity previousRarity, int reqAdv) {
        super(new Properties().rarity(Rarity.EPIC).stacksTo(1));
        this.rarity = rarity;
        this.prevRarity = previousRarity;
        this.requiredAdvancements = reqAdv;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand pUsedHand) {
        ItemStack itemStack = player.getItemInHand(pUsedHand);
        if (level.isClientSide && player instanceof AbstractClientPlayer clientPlayer) {
            if(shouldFail(0, 0, prevRarity, player, this)) {
                return InteractionResultHolder.fail(itemStack);
            }
            Animations.play(clientPlayer, Animations.UPGRADE);
        }
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            final int[] advCount = {0};
            serverPlayer.server.getAdvancements().tree().nodes().stream().forEach(advancementHolder -> {
                advancementHolder.advancement().display()
                        .filter(displayInfo -> !displayInfo.isHidden())
                        .ifPresent(displayInfo -> {
                            if(serverPlayer.getAdvancements().getOrStartProgress(advancementHolder.holder()).isDone()) {
                                advCount[0]++;
                            }
                        })
                ;
            }
            );
            if(shouldFail(advCount[0], requiredAdvancements, prevRarity, player, this)) {
                return InteractionResultHolder.fail(itemStack);
            }
            SyncedRarityData data = new SyncedRarityData(player);
            data.setRarity(rarity);
            PacketDistributor.sendToPlayer(serverPlayer, new SyncPlayerRarityDataPacket(data));

            if (!serverPlayer.getAbilities().instabuild) {
                itemStack.shrink(1);
            }
            player.getCooldowns().addCooldown(this, 20);
            return InteractionResultHolder.success(itemStack);
        }
        return InteractionResultHolder.fail(itemStack);
    }
    private static boolean shouldFail(int advCount, int requiredAdvancements, SpellRarity prevRarity, Player player, Item item) {
        return advCount < requiredAdvancements || ClientRarityData.getCurrentRarity() != prevRarity || player.getCooldowns().isOnCooldown(item);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @NotNull TooltipContext pContext, @NotNull List<Component> lines, @NotNull TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pContext, lines, pIsAdvanced);
        MinecraftInstanceHelper.ifPlayerPresent(player -> {
            lines.add(Component.translatable("item.irons_restrictions.upgrade_desc").withStyle(ChatFormatting.GRAY));
        });
    }
}
