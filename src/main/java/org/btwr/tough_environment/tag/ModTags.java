package org.btwr.tough_environment.tag;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.btwr.tough_environment.ToughEnvironmentMod;

public class ModTags {

    public static class Blocks {
        public static final TagKey<Block> CONVERTED_STONE_BLOCKS = register("converted_stone_blocks");
        public static final TagKey<Block> BROKEN_STONE_BLOCKS = register("broken_stone_blocks");

        // Blocks that are mortared; mostly used to identify what loose blocks can hold on to if placed against.
        public static final TagKey<Block> MORTARED_BLOCKS = register("mortared_blocks");

        /** Workaround tag to group miscallaneous blocks like the snow layer blocks and others. **/
        public static final TagKey<Block> MISC_REQUIRING_TOOL = register("misc_requiring_tools");

        public static final TagKey<Block> NEEDS_DIAMOND_CHISEL = register("needs_diamond_chisel");
        public static final TagKey<Block> NEEDS_IRON_CHISEL = register("needs_iron_chisel");
        public static final TagKey<Block> NEEDS_STONE_CHISEL = register("needs_stone_chisel");

        public static final TagKey<Block> INCORRECT_FOR_DIAMOND_CHISEL = register("incorrect_for_diamond_chisel");
        public static final TagKey<Block> INCORRECT_FOR_IRON_CHISEL = register("incorrect_for_iron_chisel");
        public static final TagKey<Block> INCORRECT_FOR_STONE_CHISEL = register("incorrect_for_stone_chisel");
        public static final TagKey<Block> INCORRECT_FOR_WOODEN_CHISEL = register("incorrect_for_wooden_chisel");

        // Used for inverse tag checks in TieredToolMaterials.class
        public static final TagKey<Block> INCORRECT_FOR_PRIMITIVE_WOOD = register("incorrect_for_primitive_wood");
        public static final TagKey<Block> INCORRECT_FOR_PRIMITIVE_STONE = register("incorrect_for_primitive_stone");
        public static final TagKey<Block> INCORRECT_FOR_MODERN_GOLD = register("incorrect_for_modern_gold");
        public static final TagKey<Block> INCORRECT_FOR_MODERN_IRON = register("incorrect_for_modern_iron");
        public static final TagKey<Block> INCORRECT_FOR_MODERN_DIAMOND = register("incorrect_for_modern_diamond");
        public static final TagKey<Block> INCORRECT_FOR_ADVANCED_NETHERITE = register("incorrect_for_advanced_netherite");

        public static final TagKey<Block> LOOSE_STONE_BLOCKS = register("loose_full_blocks");
        public static final TagKey<Block> LOOSE_STONE_SLABS = register("loose_slabs");
        public static final TagKey<Block> LOOSE_STONE_STAIRS = register("loose_stairs");

        public static final TagKey<Block> STONE_CONVERTING_STRATA1 = register("stone_converting_strata1");
        public static final TagKey<Block> STONE_CONVERTING_STRATA2 = register("stone_converting_strata2");
        public static final TagKey<Block> STONE_CONVERTING_STRATA3 = register("stone_converting_strata3");

        public static final TagKey<Block> ORES_LVL1 = register("ores_lvl1");
        public static final TagKey<Block> ORES_LVL2 = register("ores_lvl2");
        public static final TagKey<Block> ORES_LVL3 = register("ores_lvl3");
        public static final TagKey<Block> ORES_LVL4 = register("ores_lvl4");

        public static final TagKey<Block> STONE_ORES = register("stone_ores");
        public static final TagKey<Block> MANTLE_ORES =  register("mantle_ores");
        public static final TagKey<Block> DEEPSLATE_ORES = register("deepslate_ores");

        public static final TagKey<Block> VANILLA_DIRT_BLOCKS = register("vanilla_dirt_blocks");
        public static final TagKey<Block> SIMPLE_DIRT_BLOCKS = register("simple_dirt_blocks");

        public static final TagKey<Block> TURNED_TO_FALLING_BLOCKS = register("turned_to_falling_blocks");

        public static final TagKey<Block> CAN_FALL_IN_OVERWORLD = register("can_fall_in_overworld");
        public static final TagKey<Block> CAN_FALL_IN_THE_NETHER = register("can_fall_in_the_nether");
        public static final TagKey<Block> CAN_FALL_IN_THE_END = register("can_fall_in_the_end");

        /** Blocks which can be lava-logged **/
        public static final TagKey<Block> LAVA_LOGGABLE = register("lava_loggable");

        /** Stone blocks that are lava logged **/
        public static final TagKey<Block> LAVA_LOGGED_STONE = register("lava_logged_stone");

        /** Deepslate blocks that are lava logged **/
        public static final TagKey<Block> LAVA_LOGGED_DEEPSLATE = register("lava_logged_deepslate");

        private static TagKey<Block> register(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(ToughEnvironmentMod.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> SHOULD_NOT_MERGE = register("should_not_merge");
        public static final TagKey<Item> SMALL_STONES = register("small_stones");
        public static final TagKey<Item> MORTARING_ITEMS = register("mortaring_items");
        public static final TagKey<Item> CHISELS = register("chisels");

        private static TagKey<Item> register(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(ToughEnvironmentMod.MOD_ID, name));
        }
    }

    public static class Mineable {
        public static final TagKey<Block> CHISEL = register("mineable/chisel");

        // Special mineable tags per tier (wooden, stone, etc.), so we can specify additional restrictions
        public static final TagKey<Block> WOODEN_PRIMITIVE = register("mineable/wooden_primitive");
        public static final TagKey<Block> STONE_PRIMITIVE = register("mineable/stone_primitive");
        public static final TagKey<Block> GOLD_MODERN = register("mineable/gold_modern");
        public static final TagKey<Block> IRON_MODERN = register("mineable/iron_modern");
        public static final TagKey<Block> DIAMOND_MODERN = register("mineable/diamond_modern");
        public static final TagKey<Block> NETHERITE_ADVANCED = register("mineable/netherite_advanced");

        private static TagKey<Block> register(String id) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(ToughEnvironmentMod.MOD_ID, id));
        }
    }

}