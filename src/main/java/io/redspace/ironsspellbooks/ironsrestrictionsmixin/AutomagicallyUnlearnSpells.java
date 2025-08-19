package io.redspace.ironsspellbooks.ironsrestrictionsmixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AbstractSpell.class)
public class AutomagicallyUnlearnSpells {

    @WrapMethod(method = "requiresLearning")
    public boolean requiresLearning(Operation<Boolean> original) {
        return true;
    }
}
