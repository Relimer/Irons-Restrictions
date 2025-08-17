package com.relimer.ironsrestrictions;

import java.util.List;
import java.util.Map;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class Config {

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec.BooleanValue REPLACE_VANILLA_SCHOOLS = BUILDER
            .comment("If true, replaces Iron's Spellbooks default school assignments with Irons Restrictions equivalents (e.g., irons_spellbooks:fire → irons_restrictions:fire)")
            .define("replaceVanillaSchools", true);

    public static final ModConfigSpec.ConfigValue<List<? extends String>> FIRE_KNOWLEDGE_FRAGMENT_LOOT_TABLES;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> NATURE_KNOWLEDGE_FRAGMENT_LOOT_TABLES;
    static {
        BUILDER.push("LootTables");
        BUILDER.comment("""
                 A list of loot sources and their spawn chances.
                 Format: '{source}',<chance>
                 
                 Sources:
                 - 'namespace:loot_table'
                 - '#namespace:tag' (tagged loot tables)
                 - '@modid' (all loot tables from mod)
                 - 'biome:namespace:biome_name'
                 - 'dimension:namespace:dimension_name'
                 """);
        FIRE_KNOWLEDGE_FRAGMENT_LOOT_TABLES = BUILDER
                .defineListAllowEmpty("fireKnowledgeFragmentLootTables", List.of("'dimension:the_nether',0.25"), Config::validateLootTables);
        NATURE_KNOWLEDGE_FRAGMENT_LOOT_TABLES = BUILDER
                .defineListAllowEmpty("natureKnowledgeFragmentLootTables", List.of("'dimension:overworld',0.25"), Config::validateLootTables);

        BUILDER.pop();
    }


    public static final ModConfigSpec SPEC = BUILDER.build();

    private static boolean validateLootTables(final Object obj) {
        if(!(obj instanceof String str))  return false;
        int commaIndex = str.indexOf(",");
        if(commaIndex < 0)  return false;
        String keyPart = str.strip().substring(0, commaIndex);
        if(!(keyPart.startsWith("'") && keyPart.endsWith("'"))) return false;
        String source = keyPart.strip().substring(1, keyPart.length() - 1);
        if(source.startsWith("#")) return  ResourceLocation.tryParse(source.substring(1)) != null;
        else if (source.startsWith("@")) return source.length() > 1 && source.substring(1).matches("[a-z0-9_\\-]+");
        else if (source.startsWith("biome:") || source.startsWith("dimension:")){
            String loc = source.substring(source.indexOf(":") + 1);
            return ResourceLocation.tryParse(loc) != null;
        }
        if (ResourceLocation.tryParse(source) == null) return false;

        String rarityPart = str.strip().substring(commaIndex + 1);
        try {
            double rarity = Double.parseDouble(rarityPart);
            return rarity >= 0 && rarity <= 1;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
}
