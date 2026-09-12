package org.btwr.tough_environment.datagen.loot_table.util;

import net.minecraft.item.Item;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

import static org.btwr.shared_library.api.tag.BTWRConventionalTags.Items.*;

public class LootConditions {
    public static final LootCondition.Builder WITH_PICKAXE_FULLY_HARVESTS = withMatchingToolTag(PICKAXES_HARVEST_FULL_BLOCK);
    public static final LootCondition.Builder WITH_ADVANCED_PICKAXES = withMatchingToolTag(ADVANCED_PICKAXES);
    public static final LootCondition.Builder WITH_MODERN_PICKAXES = withMatchingToolTag(MODERN_PICKAXES);
    public static final LootCondition.Builder WITH_PRIMITIVE_PICKAXES = withMatchingToolTag(PRIMITIVE_PICKAXES);

    public static final LootCondition.Builder WITH_SHOVEL_FULLY_HARVESTS =  withMatchingToolTag(SHOVELS_HARVEST_FULL_BLOCK);
    public static final LootCondition.Builder WITH_ADVANCED_SHOVELS =  withMatchingToolTag(ADVANCED_SHOVELS);
    public static final LootCondition.Builder WITH_MODERN_SHOVELS =  withMatchingToolTag(MODERN_SHOVELS);

    public static final LootCondition.Builder WITH_ADVANCED_CHISELS =  withMatchingToolTag(ADVANCED_CHISELS);
    public static final LootCondition.Builder WITH_MODERN_CHISELS =  withMatchingToolTag(MODERN_CHISELS);
    public static final LootCondition.Builder WITH_PRIMITIVE_CHISELS =  withMatchingToolTag(PRIMITIVE_CHISELS);

    public static final LootCondition.Builder WITHOUT_HOE = withMatchingToolTag(ItemTags.HOES).invert();

    private static LootCondition.Builder withMatchingToolTag(TagKey<Item> itemTag) {
        return MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(itemTag));
    }
}