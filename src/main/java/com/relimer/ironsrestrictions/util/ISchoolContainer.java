package com.relimer.ironsrestrictions.util;

import com.relimer.ironsrestrictions.registries.ComponentRegistry;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public interface ISchoolContainer {
    ResourceLocation getSchoolId();
    void setSchoolId(ResourceLocation id);

    ISchoolContainer EMPTY = new ISchoolContainer() {
        public ResourceLocation getSchoolId() { return null; }
        public void setSchoolId(ResourceLocation id) { }
    };

    static boolean isSchoolContainer(ItemStack itemStack) {
        return itemStack != null && !itemStack.isEmpty() && itemStack.has(ComponentRegistry.SCHOOL_COMPONENT);
    }

}
