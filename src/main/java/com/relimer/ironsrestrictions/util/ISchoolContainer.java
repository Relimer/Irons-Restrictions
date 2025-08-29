package com.relimer.ironsrestrictions.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public interface ISchoolContainer {
    ResourceLocation getSchoolId();

    static boolean isSchoolContainer(ItemStack itemStack) {
        if (itemStack == null || itemStack.isEmpty()) return false;
        if (!itemStack.hasTag()) return false;

        return itemStack.getTag().contains("SchoolId", net.minecraft.nbt.Tag.TAG_STRING);
    }
}
