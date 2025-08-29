package com.relimer.ironsrestrictions.util;

import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.SchoolType;

import java.util.ArrayList;
import java.util.List;

public class SchoolUtils {
    public static List<SchoolType> getLoopSchools() {
        List<SchoolType> list = new ArrayList<>(SchoolRegistry.REGISTRY.get().getValues());
        list.remove(SchoolRegistry.ELDRITCH.get());
        return list;
    }
}
