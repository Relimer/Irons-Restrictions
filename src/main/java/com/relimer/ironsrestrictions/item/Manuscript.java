package com.relimer.ironsrestrictions.item;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.network.OpenSchoolScreenPacket;
import com.relimer.ironsrestrictions.registries.ComponentRegistry;
import com.relimer.ironsrestrictions.util.SchoolContainer;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.util.MinecraftInstanceHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
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

import java.util.List;

public class Manuscript extends Item {

    public Manuscript() {
        super(new Item.Properties().rarity(Rarity.EPIC));
    }
    private SchoolContainer getOrCreate(ItemStack itemStack) {
        return itemStack.getOrDefault(ComponentRegistry.SCHOOL_COMPONENT.get(), new SchoolContainer(SchoolRegistry.FIRE.get()));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand pUsedHand) {
        ItemStack itemStack = player.getItemInHand(pUsedHand);
        IronsRestrictions.LOGGER.info("Player: " + player);

        if (player instanceof ServerPlayer serverPlayer) {
            IronsRestrictions.LOGGER.info("Server Player: " + serverPlayer);
            SchoolContainer schoolComponent = itemStack.getOrDefault(ComponentRegistry.SCHOOL_COMPONENT.get(), new SchoolContainer(SchoolRegistry.FIRE.get()));
            IronsRestrictions.LOGGER.info("Component: " + schoolComponent);
            PacketDistributor.sendToPlayer(serverPlayer, new OpenSchoolScreenPacket(pUsedHand, schoolComponent.getSchoolType()));

            IronsRestrictions.LOGGER.info("Success");
            return InteractionResultHolder.success(itemStack);
        }

        IronsRestrictions.LOGGER.info("Fail");
        return InteractionResultHolder.fail(itemStack);
    }
    @Override
    public @NotNull Component getName(@NotNull ItemStack itemStack) {
        SchoolContainer data = getOrCreate(itemStack);
        return Component.translatable(data.getManuscriptId());
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @NotNull TooltipContext pContext, @NotNull List<Component> lines, @NotNull TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pContext, lines, pIsAdvanced);
        MinecraftInstanceHelper.ifPlayerPresent(player -> {
            SchoolContainer data = getOrCreate(pStack);
            lines.add(Component.translatable(data.getManuscriptDescription()).withStyle(ChatFormatting.GRAY));
        });
    }
}
