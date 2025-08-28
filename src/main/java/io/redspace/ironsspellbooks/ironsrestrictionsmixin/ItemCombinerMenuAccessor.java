package io.redspace.ironsspellbooks.ironsrestrictionsmixin;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ItemCombinerMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ItemCombinerMenu.class)
public interface ItemCombinerMenuAccessor {
    @Accessor("player")
    Player getPlayer();

    @Accessor("inputSlots")
    Container getInputSlots();
}
