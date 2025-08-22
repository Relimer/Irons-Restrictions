package com.relimer.ironsrestrictions.util;

import com.relimer.ironsrestrictions.IronsRestrictions;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.neoforged.neoforge.common.data.ExistingFileHelper;


public class TextureUtils {
    public static ResourceLocation getTextureOrDefault(ResourceLocation preferred, ResourceLocation fallback) {
        ResourceLocation preferredLocation = ResourceLocation.fromNamespaceAndPath(preferred.getNamespace(), preferred.getPath());
        IronsRestrictions.LOGGER.debug("Preffered Location: " + preferredLocation);
        ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();
        return resourceManager.getResource(preferredLocation).isPresent() ? preferred : fallback;
    }
    public static ResourceLocation getTextureOrDefault(ResourceLocation preferred, ResourceLocation fallback, ExistingFileHelper existingFileHelper) {
        ResourceLocation preferredLocation = ResourceLocation.fromNamespaceAndPath(preferred.getNamespace(), "textures/" + preferred.getPath() + ".png");
        boolean hasCustomTexture = existingFileHelper.exists(preferredLocation, PackType.CLIENT_RESOURCES);
        return hasCustomTexture ? preferred : fallback;
    }
    public static ResourceLocation getModelOrDefault(ResourceLocation preferred, ResourceLocation fallback) {
        ResourceLocation preferredLocation = ResourceLocation.fromNamespaceAndPath(preferred.getNamespace(), "models/" + preferred.getPath() + ".json");
        ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();
        return resourceManager.getResource(preferredLocation).isPresent() ? preferred : fallback;
    }
}
