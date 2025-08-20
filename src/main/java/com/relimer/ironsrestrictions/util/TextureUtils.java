package com.relimer.ironsrestrictions.util;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.neoforged.neoforge.common.data.ExistingFileHelper;


public class TextureUtils {
    public static ResourceLocation getTextureOrDefault(ResourceLocation preferred, ResourceLocation fallback) {
        ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();
        return resourceManager.getResource(preferred).isPresent() ? preferred : fallback;
    }
    public static ResourceLocation getTextureOrDefault(ResourceLocation preferred, ResourceLocation fallback, ExistingFileHelper existingFileHelper) {
        ResourceLocation preferredLocation = ResourceLocation.fromNamespaceAndPath(preferred.getNamespace(), "textures/" + preferred.getPath() + ".png");
        boolean hasCustomTexture = existingFileHelper.exists(preferredLocation, PackType.CLIENT_RESOURCES);
        return hasCustomTexture ? preferred : fallback;
    }

}
