package com.relimer.ironsrestrictions.item;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.network.OpenSchoolScreenPacket;
import com.relimer.ironsrestrictions.setup.Messages;
import com.relimer.ironsrestrictions.util.SchoolContainer;
import com.relimer.ironsrestrictions.util.SchoolUtils;
import com.relimer.ironsrestrictions.util.SpellUtils;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.util.MinecraftInstanceHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
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

public class Manuscript extends Item {

    public Manuscript() {
        super(new Properties().rarity(Rarity.EPIC));
    }
    private SchoolContainer getOrCreate(ItemStack itemStack) {
        return ManuscriptData.getSchoolContainer(itemStack);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand pUsedHand) {
        ItemStack itemStack = player.getItemInHand(pUsedHand);
        ItemStack offHand = player.getOffhandItem();
        ItemStack mainHand = player.getMainHandItem();
        IronsRestrictions.LOGGER.info("Player: " + player);
        ItemStack scrollHand = null;
        if(offHand.is(ItemRegistry.SCROLL.get())){
            scrollHand = offHand;
        }
        if(mainHand.is(ItemRegistry.SCROLL.get())){
            scrollHand = mainHand;
        }
        if (player instanceof ServerPlayer serverPlayer) {
            SchoolContainer schoolComponent = ManuscriptData.getSchoolContainer(itemStack);
            if(scrollHand != null) {
                var spellSlot = ISpellContainer.getOrCreate(scrollHand).getSpellAtIndex(0);
                var spell = spellSlot.getSpell();
                var school = spell.getSchoolType();
                if(school == schoolComponent.getSchoolType() && !spell.isLearned(player) && SpellUtils.getLearnableSpells().contains(spell)) {
                    var data = MagicData.getPlayerMagicData(serverPlayer).getSyncedData();
                    data.learnSpell(spell);
                    IronsRestrictions.LOGGER.info(player.getName().getString() + " learnt Spell: " + spell);
                    serverPlayer.displayClientMessage(Component.translatable("item.irons_restrictions.manuscript.learn_scroll").append(spell.getDisplayName(player).getString()).withStyle(ChatFormatting.GOLD), true);
                    player.playNotifySound(SoundRegistry.LEARN_ELDRITCH_SPELL.get(), SoundSource.MASTER, 1f, Utils.random.nextIntBetweenInclusive(9, 11) * .1f);
                    if(!serverPlayer.gameMode.isCreative()) {
                        itemStack.shrink(1);
                    }
                    player.getCooldowns().addCooldown(scrollHand.getItem(), 20);
                    return InteractionResultHolder.success(itemStack);
                }
            }
            Messages.sendToPlayer(new OpenSchoolScreenPacket(pUsedHand, schoolComponent.getSchoolType()), serverPlayer);
            return InteractionResultHolder.success(itemStack);
        }
        return InteractionResultHolder.fail(itemStack);
    }


    @Override
    public @NotNull Component getName(@NotNull ItemStack itemStack) {
        SchoolContainer data = getOrCreate(itemStack);
        return Component.translatable(data.getManuscriptId());
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> lines, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, lines, pIsAdvanced);
        MinecraftInstanceHelper.ifPlayerPresent(player -> {
            SchoolContainer data = getOrCreate(pStack);
            lines.add(Component.translatable(data.getManuscriptDescription()).withStyle(ChatFormatting.GRAY));
        });
    }
}
