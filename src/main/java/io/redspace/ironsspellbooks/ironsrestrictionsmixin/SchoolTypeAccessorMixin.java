package io.redspace.ironsspellbooks.ironsrestrictionsmixin;

import io.redspace.ironsspellbooks.api.spells.SchoolType;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SchoolType.class)
public interface SchoolTypeAccessorMixin {
    @Accessor(value = "focus")
    TagKey<Item> getFocus();
}
