package io.redspace.ironsspellbooks.ironsrestrictionsmixin;

import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.gui.arcane_anvil.ArcaneAnvilMenu;
import io.redspace.ironsspellbooks.item.Scroll;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ArcaneAnvilMenu.class)
public abstract class ArcaneAnvilMenuMixin {
    @Inject(method = "createResult", at = @At("HEAD"), cancellable = true)
    private void beforeCreateResult(CallbackInfo ci) {
        ItemCombinerMenuAccessor accessor = (ItemCombinerMenuAccessor) (Object) this;
        Player player = accessor.getPlayer();
        Container inputSlots = accessor.getInputSlots();
        ItemStack baseItem = inputSlots.getItem(0);
        ItemStack modifier = inputSlots.getItem(1);

        if (Utils.canImbue(baseItem) && modifier.getItem() instanceof Scroll) {
            var spell = ISpellContainer.get(modifier).getSpellAtIndex(0);

            if (player != null && !MagicData.getPlayerMagicData(player)
                    .getSyncedData()
                    .isSpellLearned(spell.getSpell())) {
                ci.cancel();
            }
        }
    }
}
