package io.redspace.ironsspellbooks.ironsrestrictionsmixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.gui.EldritchResearchScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(EldritchResearchScreen.class)
public class EldritchResearchScreenMixin {
    @Shadow
    List<AbstractSpell> learnableSpells;


    @ModifyExpressionValue(
            method = "init",
            at = @At(value = "INVOKE", target = "Lio/redspace/ironsspellbooks/api/registry/SpellRegistry;getEnabledSpells()Ljava/util/List;")
    )
    List<AbstractSpell> modifyLearnableSpells(List<AbstractSpell> originalList) {
        List<AbstractSpell> filtered = originalList.stream()
                .filter(spell -> spell.getSchoolType().equals(SchoolRegistry.ELDRITCH.get()))
                .toList();
        return filtered;
    }
}
