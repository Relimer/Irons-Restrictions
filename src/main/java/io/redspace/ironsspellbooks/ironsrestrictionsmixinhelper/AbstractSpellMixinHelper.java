package io.redspace.ironsspellbooks.ironsrestrictionsmixinhelper;

import io.redspace.ironsspellbooks.api.spells.SchoolType;

import java.util.Map;

public class AbstractSpellMixinHelper {
    static Map<SchoolType,SchoolType> remap = null;
    public static void setSchoolRemap(Map<SchoolType, SchoolType> map) {
        remap = map;
    }

    public static Map<SchoolType,SchoolType> getSchoolRemap() {
        return remap;
    }
}
