package com.relimer.ironsrestrictions.item;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.relimer.ironsrestrictions.util.SchoolContainer;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class ManuscriptData {
    private static final String NBT_KEY = "SchoolId";

    public static void setSchoolContainer(ItemStack stack, SchoolContainer container) {
        stack.getOrCreateTag().putString(NBT_KEY, container.getSchoolId().toString());
    }
    public static JsonElement createNBT(SchoolContainer container) {
        JsonObject json = new JsonObject();
        ResourceLocation schoolId = container.getSchoolId();
        json.addProperty(NBT_KEY, container.getSchoolId().toString());
        return json;
    }

    public static SchoolContainer getSchoolContainer(ItemStack stack) {
        if (stack.hasTag() && stack.getTag().contains(NBT_KEY)) {
            try {
                ResourceLocation id = ResourceLocation.parse(stack.getTag().getString(NBT_KEY));
                SchoolType school = SchoolRegistry.REGISTRY.get().getValue(id);
                if (school != null) {
                    return new SchoolContainer(school);
                }
            } catch (Exception ignored) {}
        }
        return new SchoolContainer(SchoolRegistry.FIRE.get());
    }
}