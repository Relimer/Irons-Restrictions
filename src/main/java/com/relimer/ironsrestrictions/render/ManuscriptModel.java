package com.relimer.ironsrestrictions.render;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.item.ManuscriptData;
import com.relimer.ironsrestrictions.util.ISchoolContainer;
import com.relimer.ironsrestrictions.util.TextureUtils;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class ManuscriptModel extends NBTOverrideItemModel {
    public ManuscriptModel(BakedModel original, ModelBakery loader) {
        super(original, loader);
    }

    @Override
    public Optional<ResourceLocation> getModelFromTag(ItemStack itemStack, CompoundTag tag) {
        if (ISchoolContainer.isSchoolContainer(itemStack)) {
            var school = ManuscriptData.getSchoolContainer(itemStack).getSchoolType();
            return Optional.of(getManuscriptModelLocation(school));
        }
        return Optional.empty();
    }

    public static ResourceLocation getManuscriptModelLocation(SchoolType schoolType) {
        return TextureUtils.getModelOrDefault(ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, String.format("item/%s_manuscript", schoolType.getId().getPath())), ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "item/manuscript"));
    }
}
