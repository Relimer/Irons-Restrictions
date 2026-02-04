package com.relimer.ironsrestrictions.util;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;

public interface ItemCombinerMenuAccessor {
    Player getPlayer();
    Container getInputSlots();
}
