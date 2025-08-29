package com.relimer.ironsrestrictions.util;

import com.mojang.serialization.Codec;
import com.relimer.ironsrestrictions.IronsRestrictions;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import java.util.Objects;

public class SchoolContainer implements ISchoolContainer {
    private final SchoolType schoolType;

    public SchoolContainer(SchoolType schoolType) {
        this.schoolType = schoolType;
    }
    public String getManuscriptId() {
        return String.format("item.%s.%s_manuscript", IronsRestrictions.MODID, getSchoolId().getPath());
    }
    public String getManuscriptDescription() {
        return String.format("item.%s.%s_manuscript_desc", IronsRestrictions.MODID, getSchoolId().getPath());
    }
    public String getKnowledgeFragmentID() {
        return String.format("item.%s.%s_knowledge_fragment", IronsRestrictions.MODID, getSchoolId().getPath());
    }
    public SchoolType getSchoolType() {
        return schoolType;
    }

    public int getSchoolColour() {
        return 0xFF000000 | Objects.requireNonNull(schoolType.getDisplayName().getStyle().getColor()).getValue();
    }

    @Override
    public ResourceLocation getSchoolId() {
        return schoolType.getId();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchoolContainer that = (SchoolContainer) o;
        return schoolType.equals(that.schoolType);
    }

    @Override
    public int hashCode() {
        return schoolType.hashCode();
    }

    public static final Codec<SchoolType> SCHOOL_TYPE_CODEC =
            SchoolRegistry.REGISTRY.get().getCodec();
    public static final Codec<SchoolContainer> CODEC =
            SCHOOL_TYPE_CODEC.xmap(SchoolContainer::new, SchoolContainer::getSchoolType);

    public static SchoolContainer readFromBuffer(FriendlyByteBuf buf) {
        ResourceLocation id = buf.readResourceLocation();
        SchoolType school = SchoolRegistry.REGISTRY.get().getValue(id);
        return new SchoolContainer(school != null ? school : SchoolRegistry.FIRE.get());
    }
    public static void writeToBuffer(FriendlyByteBuf buf, SchoolContainer container) {
        buf.writeResourceLocation(container.getSchoolId());
    }
}
