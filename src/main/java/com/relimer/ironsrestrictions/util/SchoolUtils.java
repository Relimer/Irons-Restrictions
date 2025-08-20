package com.relimer.ironsrestrictions.util;

import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import net.minecraft.core.Holder;

import java.util.ArrayList;
import java.util.List;

public class SchoolUtils {
    public static List<Holder.Reference<SchoolType>> getLoopSchools() {
        List<Holder.Reference<SchoolType>> list = new ArrayList<>(List.of());
        list.addAll(SchoolRegistry.REGISTRY.holders().toList());
        list.remove(SchoolRegistry.ELDRITCH);
        return list;
    }
}
