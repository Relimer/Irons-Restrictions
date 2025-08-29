package com.relimer.ironsrestrictions.datagen;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.registries.ItemRegistry;
import com.relimer.ironsrestrictions.util.SchoolUtils;
import com.relimer.ironsrestrictions.util.TextureUtils;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class RModelProvider extends ItemModelProvider {

    public RModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        ItemModelBuilder manuscript = getBuilder(ItemRegistry.MANUSCRIPT.get().toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID,"item/manuscript_base"))
                .texture("layer1", ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "item/manuscript_base"))
                .texture("layer2", ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "item/manuscript_overlay"));
        basicItem(ItemRegistry.FRAGMENT.get());
        basicItem(ItemRegistry.UNFINISHED_MANUSCRIPT.get());

        SchoolUtils.getLoopSchools().forEach(holder -> {
            ResourceLocation manuscriptTex = ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "item/" + holder.getId().getPath() + "_manuscript");
            ResourceLocation manuscriptBase = TextureUtils.getTextureOrDefault(manuscriptTex, ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "item/manuscript_base"), existingFileHelper);
            if(manuscriptBase.equals(manuscriptTex)) {
                getBuilder(holder.getId().getPath() + "_manuscript")
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", manuscriptBase);
            }
        });
    }
}
