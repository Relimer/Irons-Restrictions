package io.redspace.ironsspellbooks.ironsrestrictionsmixin;

import com.relimer.ironsrestrictions.Config;
import io.redspace.ironsspellbooks.api.item.weapons.MagicSwordItem;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import io.redspace.ironsspellbooks.api.spells.SpellSlot;
import io.redspace.ironsspellbooks.item.Scroll;
import io.redspace.ironsspellbooks.item.SpellBook;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.common.capability.CurioItemHandler;

import java.util.Map;
import java.util.Optional;

@Mixin(AbstractSpell.class)
public class AbstractSpellMixin {

    @Redirect(
            method = "canBeCastedBy",
            at = @At(
                    value = "INVOKE",
                    target = "Lio/redspace/ironsspellbooks/api/spells/AbstractSpell;isLearned(Lnet/minecraft/world/entity/player/Player;)Z"
            )
    )
    private boolean redirectIsLearned(AbstractSpell spell, Player player) {
        if (player == null) return false;
        if (spell.isLearned(player)) return true;

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
        Optional<ICuriosItemHandler> curiosHandlerOpt = CuriosApi.getCuriosInventory(player);
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
        if (itemStack.getItem() instanceof SpellBook || itemStack.getItem() instanceof Scroll || Config.ImbuedItemsRequireLearning.isTrue()) {
            return false;
        }
        if (ISpellContainer.isSpellContainer(itemStack)) {
            ISpellContainer container = ISpellContainer.get(itemStack);
            if (container != null) {
                for (SpellSlot spellSlot : container.getAllSpells()) {
                    if (spellSlot.getSpell() == abstractSpell) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
