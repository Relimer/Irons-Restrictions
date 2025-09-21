package com.relimer.ironsrestrictions.jei;

import com.relimer.ironsrestrictions.registries.ComponentRegistry;
import com.relimer.ironsrestrictions.util.ISchoolContainer;
import com.relimer.ironsrestrictions.util.SchoolContainer;
import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ManuscriptSubtypeInterpreter implements ISubtypeInterpreter<ItemStack> {
    @Override
    public @Nullable Object getSubtypeData(@NotNull ItemStack ingredient, @NotNull UidContext context) {
        if (ISchoolContainer.isSchoolContainer(ingredient)) {
            SchoolContainer schoolContainer = ingredient.get(ComponentRegistry.SCHOOL_COMPONENT.get());
            if (schoolContainer != null) {
                return schoolContainer.getSchoolId().getPath() + "_manuscript";
            }
        }
        return null;
    }

    @Override
    public @NotNull String getLegacyStringSubtypeInfo(@NotNull ItemStack ingredient, @NotNull UidContext context) {
        return "";
    }
}
