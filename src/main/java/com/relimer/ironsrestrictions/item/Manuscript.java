package com.relimer.ironsrestrictions.item;

import com.relimer.ironsrestrictions.network.OpenSchoolScreenPacket;
import com.relimer.ironsrestrictions.setup.Messages;
import com.relimer.ironsrestrictions.util.SchoolContainer;
import io.redspace.ironsspellbooks.util.MinecraftInstanceHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
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

        if (player instanceof ServerPlayer serverPlayer) {
            SchoolContainer schoolComponent = ManuscriptData.getSchoolContainer(itemStack);
            Messages.sendToPlayer(new OpenSchoolScreenPacket(pUsedHand, schoolComponent.getSchoolType()), serverPlayer);
        }
        return super.use(level, player, pUsedHand);
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
