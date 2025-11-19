package com.relimer.ironsrestrictions.item;

import com.relimer.ironsrestrictions.anim.Animations;
import com.relimer.ironsrestrictions.network.PlayAnimationPacket;
import com.relimer.ironsrestrictions.network.RarityData;
import com.relimer.ironsrestrictions.network.spells.SyncedRarityData;
import com.relimer.ironsrestrictions.player.PlayerRarityProvider;
import com.relimer.ironsrestrictions.setup.Messages;
import com.relimer.ironsrestrictions.util.ConfigurableRarity;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.util.MinecraftInstanceHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RarityUpgrade extends Item {
    ConfigurableRarity rarity;
    SpellRarity prevRarity;
    int requiredAdvancements;
    public RarityUpgrade(ConfigurableRarity rarity, SpellRarity previousRarity, int reqAdv) {
        super(new Properties().rarity(Rarity.EPIC).stacksTo(1));
        this.rarity = rarity;
        this.prevRarity = previousRarity;
        this.requiredAdvancements = reqAdv;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand pUsedHand) {
        ItemStack itemStack = player.getItemInHand(pUsedHand);
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            RarityData rarityData = player.getCapability(PlayerRarityProvider.SYNCED_RARITY).orElse(new RarityData(serverPlayer));
            SpellRarity currentRarity = rarityData.getSyncedData().getRarity().getSpellRarity();
            if(player.getCooldowns().isOnCooldown(this)) {
                return InteractionResultHolder.fail(itemStack);
            }
            int value;
            if(currentRarity == null) {
                value = -1;
            } else {
                value = currentRarity.getValue();
            }
            int prevValue;
            if(prevRarity == null) {
                prevValue = -1;
            } else {
                prevValue = prevRarity.getValue();
            }
            if(value < prevValue){
                player.displayClientMessage(Component.translatable("item.irons_restrictions.upgrade.fail_below").withStyle(ChatFormatting.DARK_RED), true);
                return InteractionResultHolder.fail(itemStack);
            }
            if(value > prevValue){
                player.displayClientMessage(Component.translatable("item.irons_restrictions.upgrade.fail_above").withStyle(ChatFormatting.DARK_RED), true);
                return InteractionResultHolder.fail(itemStack);
            }
            player.playNotifySound(SoundEvents.TRIDENT_THUNDER, SoundSource.MASTER, 1f, Utils.random.nextIntBetweenInclusive(9, 11) * .1f);
            ((ServerLevel) level).sendParticles(ParticleTypes.ENCHANT, player.getX(), player.getY(), player.getZ(), 50, 0.5, 1, 0.5, 0.5);
            rarityData.getSyncedData().setRarity(rarity);
            Messages.sendToPlayer(new PlayAnimationPacket(Animations.UPGRADE), serverPlayer);

            if (!serverPlayer.gameMode.isCreative()) {
                itemStack.shrink(1);
            }
            player.getCooldowns().addCooldown(this, 20);
            return InteractionResultHolder.success(itemStack);
        }
        return InteractionResultHolder.fail(itemStack);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> lines, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, lines, pIsAdvanced);
        MinecraftInstanceHelper.ifPlayerPresent(player -> {
            lines.add(Component.translatable("item.irons_restrictions.upgrade_desc").withStyle(ChatFormatting.GRAY));
        });
    }
}
