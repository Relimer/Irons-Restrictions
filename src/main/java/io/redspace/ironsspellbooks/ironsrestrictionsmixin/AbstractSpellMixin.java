package io.redspace.ironsspellbooks.ironsrestrictionsmixin;

import com.relimer.ironsrestrictions.Config;
import com.relimer.ironsrestrictions.compat.FallenGemsAffixSpellCastTrigger;
import com.relimer.ironsrestrictions.network.RarityData;
import com.relimer.ironsrestrictions.player.PlayerRarityProvider;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.item.Scroll;
import io.redspace.ironsspellbooks.item.SpellBook;
import io.redspace.ironsspellbooks.player.ClientMagicData;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import java.util.Map;
import java.util.Optional;


@Mixin(AbstractSpell.class)
public abstract class AbstractSpellMixin {
    @Shadow(remap = false)
    public abstract boolean obfuscateStats(Player player);
    @Shadow(remap = false)
    public abstract boolean isLearned(Player player);
    @Shadow(remap = false)
    public abstract boolean requiresLearning();
    @Shadow(remap = false)
    public abstract String getComponentId();

    private static final Style ELDRITCH_OBFUSCATED_STYLE = Style.EMPTY.withObfuscated(true).withFont(ResourceLocation.withDefaultNamespace("alt"));

    @Inject(method = "canBeCastedBy", at = @At("HEAD"), cancellable = true, remap = false)
    public void canBeCastedBy(int spellLevel, CastSource castSource, MagicData playerMagicData, Player player, CallbackInfoReturnable<CastResult> cir) {
        AbstractSpell spell = (AbstractSpell) (Object) this;
        if(Config.ImbuedItemsRequireLearning.get().equals(true) && ironsSpells_nSpellbooksRestrictions$imbued(spell, player)) {
            cir.setReturnValue(new CastResult(CastResult.Type.FAILURE, Component.translatable("ui.irons_spellbooks.cast_error_unlearned").withStyle(ChatFormatting.RED)));
        } else if (Config.ImbuedItemsRequireLearning.get().equals(false) && ironsSpells_nSpellbooksRestrictions$imbued(spell, player)) {
            cir.setReturnValue((new CastResult(CastResult.Type.SUCCESS)));
        }
        if (requiresLearning() && isLearned(player) && !irons_Restrictions$hasUnlockedRarity(spell, spellLevel, player)) {
            cir.setReturnValue(new CastResult(CastResult.Type.FAILURE, Component.translatable("ui.irons_restrictions.cast_error_rarity").withStyle(ChatFormatting.RED)));
        }
    }

    @Unique
    private boolean ironsSpells_nSpellbooksRestrictions$imbued(AbstractSpell spell, Player player) {
        if (player == null || spell == null) return false;

        for (InteractionHand hand : InteractionHand.values()) {
            ItemStack held = player.getItemInHand(hand);
            if (irons_Restrictions$isSpellImbued(held, spell)) {
                return true;
            }
        }
        for (ItemStack armorItem : player.getArmorSlots()) {
            if (irons_Restrictions$isSpellImbued(armorItem, spell)) {
                return true;
            }
        }
        if(ModList.get().isLoaded("curios")) {
            LazyOptional<ICuriosItemHandler> curiosHandlerOpt = CuriosApi.getCuriosInventory(player);
            if (curiosHandlerOpt.isPresent()) {
                ICuriosItemHandler handler = curiosHandlerOpt.orElse(null);
                for (Map.Entry<String, ICurioStacksHandler> entry : handler.getCurios().entrySet()) {
                    for (int i = 0; i < entry.getValue().getSlots(); i++) {
                        ItemStack curioStack = entry.getValue().getStacks().getStackInSlot(i);
                        if (irons_Restrictions$isSpellImbued(curioStack, spell)) {
                            return true;
                        }
                    }
                }
            }
        }

        if(ModList.get().isLoaded("fallen_gems_affixes")) {
            return FallenGemsAffixSpellCastTrigger.getter(player);
        }

        return false;
    }

    @Unique
    private boolean irons_Restrictions$hasUnlockedRarity(AbstractSpell abstractSpell, int spellLevel, Player player) {
        SpellRarity rarity = abstractSpell.getRarity(spellLevel);
        int minLevel = abstractSpell.getMinLevelForRarity(rarity);
        RarityData rarityData = player.getCapability(PlayerRarityProvider.SYNCED_RARITY).orElse(new RarityData(((ServerPlayer) player)));
        SpellRarity currentRarity = rarityData.getSyncedData().getRarity().getSpellRarity();
        if(currentRarity == null) {
            return irons_Restrictions$imbuedChecks(abstractSpell, player);
        }
        if(minLevel <= abstractSpell.getMinLevelForRarity(currentRarity)) {
            return true;
        }

        return irons_Restrictions$imbuedChecks(abstractSpell, player);
    }
    @Unique
    private boolean irons_Restrictions$imbuedChecks(AbstractSpell spell, Player player) {
        for (InteractionHand hand : InteractionHand.values()) {
            ItemStack held = player.getItemInHand(hand);
            if (irons_Restrictions$isSpellImbued(held, spell)) {
                return true;
            }
        }
        for (ItemStack armorItem : player.getArmorSlots()) {
            if (irons_Restrictions$isSpellImbued(armorItem, spell)) {
                return true;
            }
        }
        Optional<ICuriosItemHandler> curiosHandlerOpt = CuriosApi.getCuriosInventory(player).resolve();
        if (curiosHandlerOpt.isPresent()) {
            ICuriosItemHandler handler = curiosHandlerOpt.get();
            for (Map.Entry<String, ICurioStacksHandler> entry : handler.getCurios().entrySet()) {
                for (int i = 0; i < entry.getValue().getSlots(); i++) {
                    ItemStack curioStack = entry.getValue().getStacks().getStackInSlot(i);
                    if (irons_Restrictions$isSpellImbued(curioStack, spell)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Unique
    private boolean irons_Restrictions$isSpellImbued(ItemStack itemStack, AbstractSpell abstractSpell) {
        if (itemStack.getItem() instanceof SpellBook || itemStack.getItem() instanceof Scroll) {
            return false;
        }
        if (ISpellContainer.isSpellContainer(itemStack)) {
            ISpellContainer container = ISpellContainer.get(itemStack);
            if (container != null && !container.isEmpty()) {
                for (SpellSlot spellSlot : container.getAllSpells()) {
                    if (spellSlot.getSpell() == abstractSpell) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Inject(method = "getDisplayName", at = @At("HEAD"), cancellable = true, remap = false)
    private void getDisplayName(Player player, CallbackInfoReturnable<MutableComponent> cir) {
        boolean obfuscateName = player != null && obfuscateStats(player);
        MutableComponent name = Component.translatable(getComponentId())
                .withStyle(obfuscateName ? ELDRITCH_OBFUSCATED_STYLE : Style.EMPTY);
        cir.setReturnValue(name);
    }

    @Inject(method = "obfuscateStats", at = @At("HEAD"), cancellable = true, remap = false)
    private void overrideObfuscateStats(@Nullable Player player, CallbackInfoReturnable<Boolean> cir) {
        boolean result = requiresLearning() && !isLearned(player);
        cir.setReturnValue(result);
    }
    @Inject(method = "isLearned", at = @At("HEAD"), cancellable = true, remap = false)
    public void overrideIsLearned(Player player, CallbackInfoReturnable<Boolean> cir) {

        if (player == null) {
            cir.setReturnValue(false);
        } else if (player.level().isClientSide) {
            cir.setReturnValue(ClientMagicData.getSyncedSpellData(player).isSpellLearned((AbstractSpell) (Object) this));
        } else {
            cir.setReturnValue(MagicData.getPlayerMagicData(player).getSyncedData().isSpellLearned((AbstractSpell) (Object) this));
        }
    }

    @Inject(method = "requiresLearning", at = @At("HEAD"), cancellable = true, remap = false)
    public void overrideNeedsLearning(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}
