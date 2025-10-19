package com.relimer.ironsrestrictions.anim;

import com.relimer.ironsrestrictions.IronsRestrictions;
import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.api.layered.modifier.AbstractFadeModifier;
import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import dev.kosmx.playerAnim.core.util.Ease;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;


public class Animations {
    public static final ResourceLocation ANIMATION_RESOURCE = ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "animation");

    public static final ResourceLocation UPGRADE = ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "upgrade");

    public static void  play(AbstractClientPlayer clientPlayer, ResourceLocation resourceLocation) {
        var rawanimation = PlayerAnimationRegistry.getAnimation(resourceLocation);
        if (rawanimation instanceof KeyframeAnimation) {
            var playerAnimationData = (ModifierLayer<IAnimation>) PlayerAnimationAccess.getPlayerAssociatedData(clientPlayer).get(Animations.ANIMATION_RESOURCE);
            if (playerAnimationData != null) {
                var animation = new KeyframeAnimationPlayer(rawanimation) {

                    @Override
                    public void tick() {
                        super.tick();
                    }
                };
                playerAnimationData.replaceAnimationWithFade(AbstractFadeModifier.standardFadeIn(2, Ease.INOUTSINE), animation, true);
            }
        }
    }
}
