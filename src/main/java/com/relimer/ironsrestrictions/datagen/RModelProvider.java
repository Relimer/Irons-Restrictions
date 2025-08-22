package com.relimer.ironsrestrictions.datagen;

import com.relimer.ironsrestrictions.IronsRestrictions;
import com.relimer.ironsrestrictions.registries.ComponentRegistry;
import com.relimer.ironsrestrictions.registries.ItemRegistry;
import com.relimer.ironsrestrictions.util.SchoolContainer;
import com.relimer.ironsrestrictions.util.SchoolUtils;
import com.relimer.ironsrestrictions.util.TextureUtils;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.function.Function;

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
            ResourceLocation manuscriptTex = ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "item/" + holder.value().getId().getPath() + "_manuscript");
            ResourceLocation manuscriptBase = TextureUtils.getTextureOrDefault(manuscriptTex, ResourceLocation.fromNamespaceAndPath(IronsRestrictions.MODID, "item/manuscript_base"), existingFileHelper);
            if(manuscriptBase.equals(manuscriptTex)) {
                getBuilder(holder.value().getId().getPath() + "_manuscript")
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", manuscriptBase);
            }
        });
    }
}
