package com.relimer.ironsrestrictions.item;

import com.relimer.ironsrestrictions.Config;
import com.relimer.ironsrestrictions.registries.ItemRegistry;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.util.MinecraftInstanceHelper;
import net.minecraft.ChatFormatting;
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
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class UnfinishedManuscript extends Item {

    public UnfinishedManuscript() {
        super(new Properties().rarity(Rarity.EPIC));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand pUsedHand) {
        ItemStack itemStack = player.getItemInHand(pUsedHand);
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            if (player.getCooldowns().isOnCooldown(this)) {
                ItemStack stack = player.getItemInHand(pUsedHand);
                return InteractionResultHolder.fail(stack);
            }
            double failureChance = Config.FailChance.getAsDouble();

            List<? extends String> spellIds = Config.ExcludeRandomLearntSpells.get();
            var learnableSpells = new ArrayList<>(SpellRegistry.getEnabledSpells().stream().filter(spell -> !spell.isLearned(player)).toList());
            for (String spellId : spellIds) {
                String namespace = spellId.split(":")[0];
                String path = spellId.split(":")[1];
                try {
                    ResourceLocation id = ResourceLocation.fromNamespaceAndPath(namespace, path);
                    AbstractSpell spell = SpellRegistry.getSpell(id);
                    if (spell != null) {
                        learnableSpells.remove(spell);
                    }
                } catch (Exception ignore) {
                }
            }

            if (learnableSpells.isEmpty()) {
                serverPlayer.displayClientMessage(Component.literal("You already know all available spells."), true);
                player.playNotifySound(SoundEvents.FLINTANDSTEEL_USE, SoundSource.MASTER, 1f, Utils.random.nextIntBetweenInclusive(9, 11) * .1f);
                return InteractionResultHolder.fail(itemStack);
            }
            if (!serverPlayer.getAbilities().instabuild) {
                itemStack.shrink(1);
            }
            player.getCooldowns().addCooldown(this, 20);

            if (player.getRandom().nextDouble() < failureChance) {
                serverPlayer.displayClientMessage(Component.literal("The manuscript crumbles to dust...").withStyle(ChatFormatting.DARK_RED), true);
                player.playNotifySound(SoundEvents.FIRE_EXTINGUISH, SoundSource.MASTER, 1f, Utils.random.nextIntBetweenInclusive(9, 11) * .1f);
                return InteractionResultHolder.success(itemStack);
            }

            var chosenSpell = learnableSpells.get(player.getRandom().nextInt(learnableSpells.size()));
            var data = MagicData.getPlayerMagicData(serverPlayer).getSyncedData();
            if (chosenSpell != SpellRegistry.none() && !data.isSpellLearned(chosenSpell) && itemStack.is(ItemRegistry.UNFINISHED_MANUSCRIPT) && itemStack.getCount() > 0) {
                player.playNotifySound(io.redspace.ironsspellbooks.registries.SoundRegistry.LEARN_ELDRITCH_SPELL.get(), SoundSource.MASTER, 1f, Utils.random.nextIntBetweenInclusive(9, 11) * .1f);
                data.learnSpell(chosenSpell);
            }
            serverPlayer.displayClientMessage(Component.literal("You learned a new spell: " + chosenSpell.getDisplayName(player).getString()).withStyle(ChatFormatting.GOLD), true);

            return InteractionResultHolder.success(itemStack);
        }
        return InteractionResultHolder.fail(itemStack);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @NotNull TooltipContext pContext, @NotNull List<Component> lines, @NotNull TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pContext, lines, pIsAdvanced);
        MinecraftInstanceHelper.ifPlayerPresent(player -> {
            lines.add(Component.translatable("item.irons_restrictions.unfinished_manuscript_desc").withStyle(ChatFormatting.GRAY));
        });
    }
}
