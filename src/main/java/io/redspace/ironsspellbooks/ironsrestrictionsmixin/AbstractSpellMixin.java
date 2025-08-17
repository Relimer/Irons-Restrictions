package io.redspace.ironsspellbooks.ironsrestrictionsmixin;

import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.ironsrestrictionsmixinhelper.AbstractSpellMixinHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(AbstractSpell.class)
public class AbstractSpellMixin {

    @Inject(method = "getSchoolType", at = @At("RETURN"), cancellable = true)
    private void onGetSchoolType(CallbackInfoReturnable<SchoolType> cir) {
        Map<SchoolType, SchoolType> schoolRemap = AbstractSpellMixinHelper.getSchoolRemap();
        SchoolType original = cir.getReturnValue();
        if (schoolRemap != null && schoolRemap.containsKey(original)) {
            cir.setReturnValue(schoolRemap.get(original));
        }
    }
}
