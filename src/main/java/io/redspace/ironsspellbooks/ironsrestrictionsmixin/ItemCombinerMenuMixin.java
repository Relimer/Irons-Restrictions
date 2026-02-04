package io.redspace.ironsspellbooks.ironsrestrictionsmixin;

import com.relimer.ironsrestrictions.util.ItemCombinerMenuAccessor;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ItemCombinerMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemCombinerMenu.class)
public abstract class ItemCombinerMenuMixin implements ItemCombinerMenuAccessor {
    @Shadow
    protected Player player;
    @Shadow
    protected Container inputSlots;
    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Container getInputSlots() {
        return inputSlots;
    }
}
