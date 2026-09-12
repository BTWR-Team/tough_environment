package org.btwr.tough_environment.datagen.recipe;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import org.btwr.shared_library.recipe.ExtendedShapelessRecipe;
import org.btwr.shared_library.api.tag.BTWRConventionalTags;
import org.btwr.shared_library.util.utils.IdUtils;
import org.btwr.tough_environment.block.ModBlocks;
import org.btwr.tough_environment.item.ModItems;

import java.util.concurrent.CompletableFuture;


public class TERecipeProvider extends TEBaseRecipeProvider {

    public TERecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        this.addFullBlockRecipes(exporter);
        this.addSlabBlockRecipes(exporter);
        this.addStairBlockRecipes(exporter);
        this.addExistingCopyForVanillaBlockRecipes(exporter);
        this.addItemRecipes(exporter);
        this.addExistingCopyForVanillaItemRecipes(exporter);
        this.addLesserDropRecipes(exporter);
        this.addMiscRecipes(exporter);
        this.addCookingRecipes(exporter);

        /**
        PistonPackingRecipe.JsonBuilder.create().result(Blocks.GRAVEL)
                .category(CraftingRecipeCategory.MISC)
                .ingredient(Items.FLINT)
                .criterion("has_flint", conditionsFromItem(Items.FLINT))
                .offerTo(exporter);
         **/
    }

    private void addMiscRecipes(RecipeExporter exporter) {
        // Tools
        ShapelessRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.CHISEL_WOOD)
                .input(Items.STICK)
                .criterion("has_stick", conditionsFromItem(Items.STICK))
                .offerTo(exporter, IdUtils.ofTE("chisel_wood"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.CHISEL_STONE)
                .input(ModItems.SMALL_STONE)
                .criterion("has_small_stone", conditionsFromItem(ModItems.SMALL_STONE))
                .offerTo(exporter, IdUtils.ofTE("chisel_stone"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.CHISEL_IRON)
                .input('#', Items.IRON_NUGGET)
                .pattern("##")
                .pattern("##")
                .criterion("has_iron_nugget", RecipeProvider.conditionsFromItem(Items.IRON_NUGGET))
                .offerTo(exporter, IdUtils.ofTE("chisel_iron"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.CHISEL_DIAMOND)
                .input(Items.DIAMOND)
                .criterion("has_diamond", RecipeProvider.conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter, IdUtils.ofTE("chisel_diamond"));

        // Ore chunks from dust
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.COAL)
                .input(ModItems.DUST_COAL)
                .input(ModItems.DUST_COAL)
                .criterion("has_dust_coal", RecipeProvider.conditionsFromItem(ModItems.DUST_COAL))
                .offerTo(exporter, IdUtils.ofTE("coal_from_dust_coal"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.RAW_IRON)
                .input(ModItems.DUST_IRON)
                .input(ModItems.DUST_IRON)
                .criterion("has_dust_iron", RecipeProvider.conditionsFromItem(ModItems.DUST_IRON))
                .offerTo(exporter, IdUtils.ofTE("raw_iron_from_dust_iron"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.RAW_GOLD)
                .input(ModItems.DUST_GOLD)
                .input(ModItems.DUST_GOLD)
                .criterion("has_dust_gold", RecipeProvider.conditionsFromItem(ModItems.DUST_GOLD))
                .offerTo(exporter, IdUtils.ofTE("raw_gold_from_dust_gold"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.RAW_COPPER)
                .input(ModItems.DUST_COPPER)
                .input(ModItems.DUST_COPPER)
                .criterion("has_dust_copper", RecipeProvider.conditionsFromItem(ModItems.DUST_COPPER))
                .offerTo(exporter, IdUtils.ofTE("raw_copper_from_dust_copper"));

        // Misc
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.CLAY_BALL)
                .input('#', ModItems.PILE_CLAY)
                .pattern("#")
                .pattern("#")
                .criterion("has_pile_clay", RecipeProvider.conditionsFromItem(ModItems.PILE_CLAY))
                .offerTo(exporter, IdUtils.ofTE("clay_ball_from_pile_clay"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.NETHER_SLUDGE,4)
                .input('N', Items.NETHERRACK)
                .input('C', Items.CLAY_BALL)
                .pattern("NN")
                .pattern("CN")
                .criterion("has_netherrack", conditionsFromItem(Items.NETHERRACK))
                .offerTo(exporter, IdUtils.ofTE("nether_sludge"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.NETHER_BRICK_UNFIRED)
                .input(ModItems.NETHER_SLUDGE)
                .criterion("has_nether_sludge", conditionsFromItem(ModItems.NETHER_SLUDGE))
                .offerTo(exporter, IdUtils.ofTE("nether_brick_unfired"));

        // Stone Bricks from Stone with chisel
        this.addStoneBrickFromToolRecipes(exporter);
    }

    private void addStoneBrickFromToolRecipes(RecipeExporter exporter) {
        // Stone Brick
        ExtendedShapelessRecipe.JsonBuilder.create(RecipeCategory.MISC, ModItems.STONE_BRICK)
                .withToolDamage()
                .input(Items.STONE)
                .input(BTWRConventionalTags.Items.MODERN_CHISELS)
                .criterion("has_stone", conditionsFromItem(Items.STONE))
                .offerTo(exporter, IdUtils.ofTE("stone_brick_from_stone"));

        ExtendedShapelessRecipe.JsonBuilder.create(RecipeCategory.MISC, ModItems.STONE_BRICK)
                .withToolDamage()
                .input(Items.STONE)
                .input(BTWRConventionalTags.Items.ADVANCED_CHISELS)
                .criterion("has_stone", conditionsFromItem(Items.STONE))
                .offerTo(exporter, IdUtils.ofTE("stone_brick_from_stone_advanced"));

        // Deepslate Stone Brick
        ExtendedShapelessRecipe.JsonBuilder.create(RecipeCategory.MISC, ModItems.STONE_BRICK_2)
                .withToolDamage()
                .input(Items.DEEPSLATE)
                .input(BTWRConventionalTags.Items.MODERN_CHISELS)
                .criterion("has_deepslate", conditionsFromItem(Items.DEEPSLATE))
                .offerTo(exporter, IdUtils.ofTE("stone_brick_2_from_stone"));

        ExtendedShapelessRecipe.JsonBuilder.create(RecipeCategory.MISC, ModItems.STONE_BRICK_2)
                .withToolDamage()
                .input(Items.DEEPSLATE)
                .input(BTWRConventionalTags.Items.ADVANCED_CHISELS)
                .criterion("has_deepslate", conditionsFromItem(Items.DEEPSLATE))
                .offerTo(exporter, IdUtils.ofTE("stone_brick_2_from_stone_advanced"));
    }

    private void addLesserDropRecipes(RecipeExporter exporter) {
        // Piles, Stones & Shards from Full Blocks
        offerLesserDropsFromBlock(exporter, ModItems.PILE_DIRT, 8, ModBlocks.DIRT_LOOSE, IdUtils.ofTE("pile_dirt_from_block_dirt_loose"));
        offerLesserDropsFromBlock(exporter, ModItems.PILE_SAND, 8, Blocks.SAND, IdUtils.ofTE("pile_sand_from_block_sand"));
        offerLesserDropsFromBlock(exporter, ModItems.PILE_RED_SAND, 8, Blocks.RED_SAND, IdUtils.ofTE("pile_red_sand_from_block_red_sand"));
        offerLesserDropsFromBlock(exporter, ModItems.PILE_GRAVEL, 8, Blocks.GRAVEL, IdUtils.ofTE("pile_gravel_from_block_gravel"));
        offerLesserDropsFromBlock(exporter, ModItems.SMALL_STONE, 8, ModBlocks.COBBLESTONE_LOOSE, IdUtils.ofTE("small_stone_from_block_cobblestone_loose"));
        // TODO: insert level 1 (mantle) here when added!
        offerLesserDropsFromBlock(exporter, ModItems.SMALL_STONE_2, 8, ModBlocks.COBBLED_DEEPSLATE_LOOSE, IdUtils.ofTE("small_stone_2_from_block_cobbled_deepslate_loose"));
        offerLesserDropsFromBlock(exporter, ModItems.SHARD_ANDESITE, 8, ModBlocks.ANDESITE_LOOSE, IdUtils.ofTE("shard_andesite_from_block_andesite_loose"));
        offerLesserDropsFromBlock(exporter, ModItems.SHARD_GRANITE, 8, ModBlocks.GRANITE_LOOSE, IdUtils.ofTE("shard_granite_from_block_granite_loose"));
        offerLesserDropsFromBlock(exporter, ModItems.SHARD_DIORITE, 8, ModBlocks.DIORITE_LOOSE, IdUtils.ofTE("shard_diorite_from_block_diorite_loose"));
        offerLesserDropsFromBlock(exporter, Items.BRICK, 8, ModBlocks.BRICKS_LOOSE, IdUtils.ofTE("brick_from_block_bricks_loose"));
        offerLesserDropsFromBlock(exporter, Items.NETHER_BRICK, 8, ModBlocks.NETHER_BRICKS_LOOSE, IdUtils.ofTE("nether_brick_from_block_nether_bricks_loose"));

        offerLesserDropsFromBlock(exporter, ModItems.STONE_BRICK, 4, ModBlocks.STONE_BRICKS_LOOSE, IdUtils.ofTE("stone_brick_from_block_stone_bricks_loose"));
        offerLesserDropsFromBlock(exporter, ModItems.STONE_BRICK_2, 4, ModBlocks.DEEPSLATE_BRICKS_LOOSE, IdUtils.ofTE("stone_brick_2_from_block_deepslate_bricks_loose"));

        // Piles, Stones & Shards from Stair Blocks
        offerLesserDropsFromStairs(exporter, ModItems.SMALL_STONE, 6, ModBlocks.COBBLESTONE_LOOSE_STAIRS, IdUtils.ofTE("small_stone_from_stairs_cobblestone_loose"));
        // TODO: insert level 1 (mantle) here when added!
        offerLesserDropsFromStairs(exporter, ModItems.SMALL_STONE_2, 6, ModBlocks.COBBLED_DEEPSLATE_LOOSE_STAIRS, IdUtils.ofTE("small_stone_2_from_stairs_cobbled_deepslate_loose"));
        offerLesserDropsFromStairs(exporter, ModItems.SHARD_ANDESITE, 6, ModBlocks.ANDESITE_LOOSE_STAIRS, IdUtils.ofTE("shard_andesite_from_stairs_andesite_loose"));
        offerLesserDropsFromStairs(exporter, ModItems.SHARD_GRANITE, 6, ModBlocks.GRANITE_LOOSE_STAIRS, IdUtils.ofTE("shard_granite_from_stairs_granite_loose"));
        offerLesserDropsFromStairs(exporter, ModItems.SHARD_DIORITE, 6, ModBlocks.DIORITE_LOOSE_STAIRS, IdUtils.ofTE("shard_diorite_from_stairs_diorite_loose"));
        offerLesserDropsFromStairs(exporter, Items.BRICK, 6, ModBlocks.BRICKS_LOOSE_STAIRS, IdUtils.ofTE("brick_from_stairs_bricks_loose"));
        offerLesserDropsFromStairs(exporter, Items.NETHER_BRICK, 6, ModBlocks.NETHER_BRICKS_LOOSE_STAIRS, IdUtils.ofTE("nether_brick_from_stairs_nether_bricks_loose"));

        offerLesserDropsFromStairs(exporter, ModItems.STONE_BRICK, 3, ModBlocks.STONE_BRICKS_LOOSE_STAIRS, IdUtils.ofTE("stone_brick_from_stairs_stone_bricks_loose"));
        offerLesserDropsFromStairs(exporter, ModItems.STONE_BRICK_2, 3, ModBlocks.DEEPSLATE_BRICKS_LOOSE_STAIRS, IdUtils.ofTE("stone_brick_2_from_stairs_deepslate_bricks_loose"));

        // Piles, Stones & Shards from Slabs
        offerLesserDropsFromSlab(exporter, ModItems.PILE_DIRT, 4, ModBlocks.SLAB_DIRT, IdUtils.ofTE("pile_dirt_from_slab_dirt"));
        offerLesserDropsFromSlab(exporter, ModItems.PILE_SAND, 4, ModBlocks.SLAB_SAND, IdUtils.ofTE("pile_sand_from_slab_sand"));
        offerLesserDropsFromSlab(exporter, ModItems.PILE_RED_SAND, 4, ModBlocks.SLAB_RED_SAND, IdUtils.ofTE("pile_red_sand_from_slab_red_sand"));
        offerLesserDropsFromSlab(exporter, ModItems.PILE_GRAVEL, 4, ModBlocks.SLAB_GRAVEL, IdUtils.ofTE("pile_gravel_from_slab_gravel"));
        offerLesserDropsFromSlab(exporter, ModItems.SMALL_STONE, 4, ModBlocks.SLAB_COBBLESTONE_LOOSE, IdUtils.ofTE("small_stone_from_slab_cobblestone_loose"));
        // TODO: insert level 1 (mantle) here when added!
        offerLesserDropsFromSlab(exporter, ModItems.SMALL_STONE_2, 4, ModBlocks.SLAB_COBBLED_DEEPSLATE_LOOSE, IdUtils.ofTE("small_stone_2_from_slab_cobbled_deepslate_loose"));
        offerLesserDropsFromSlab(exporter, ModItems.SHARD_ANDESITE, 4, ModBlocks.SLAB_ANDESITE_LOOSE, IdUtils.ofTE("shard_andesite_from_slab_andesite_loose"));
        offerLesserDropsFromSlab(exporter, ModItems.SHARD_GRANITE,4, ModBlocks.SLAB_GRANITE_LOOSE, IdUtils.ofTE("shard_granite_from_slab_granite_loose"));
        offerLesserDropsFromSlab(exporter, ModItems.SHARD_DIORITE, 4, ModBlocks.SLAB_DIORITE_LOOSE, IdUtils.ofTE("shard_diorite_from_slab_diorite_loose"));
        offerLesserDropsFromSlab(exporter, Items.BRICK, 4, ModBlocks.SLAB_BRICKS_LOOSE, IdUtils.ofTE("brick_from_slab_bricks_loose"));
        offerLesserDropsFromSlab(exporter, Items.NETHER_BRICK, 4, ModBlocks.SLAB_NETHER_BRICKS_LOOSE, IdUtils.ofTE("nether_brick_from_slab_nether_bricks_loose"));

        offerLesserDropsFromSlab(exporter, ModItems.STONE_BRICK, 2, ModBlocks.SLAB_STONE_BRICKS_LOOSE, IdUtils.ofTE("brick_from_slab_stone_bricks_loose"));
        offerLesserDropsFromSlab(exporter, ModItems.STONE_BRICK_2, 2, ModBlocks.SLAB_DEEPSLATE_BRICKS_LOOSE, IdUtils.ofTE("brick_from_slab_deepslate_bricks_loose"));
    }

    private void addFullBlockRecipes(RecipeExporter exporter) {
        // Exclusive recipes

        // we give 2 packed earth in this recipe that uses 4 loose dirt
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DIRT_PACKED, 2)
                .input('D', ModBlocks.DIRT_LOOSE)
                .pattern("DD")
                .pattern("DD")
                .criterion("has_dirt_loose", conditionsFromItem(ModBlocks.DIRT_LOOSE))
                .offerTo(exporter, IdUtils.ofTE("dirt_packed_from_loose_dirt"));

        // and we convert each packed earth back to 2 loose dirt blocks each
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DIRT_LOOSE, 2)
                .input(ModBlocks.DIRT_PACKED)
                .criterion("has_dirt_packed", conditionsFromItem(ModBlocks.DIRT_PACKED))
                .offerTo(exporter, IdUtils.ofTE("dirt_loose_from_dirt_packed"));

        // From slabs
        offerBlockFromSlabs(exporter, ModBlocks.DIRT_LOOSE, ModBlocks.SLAB_DIRT, IdUtils.ofTE("block_from_slab_dirt"));
        offerBlockFromSlabs(exporter, Blocks.SAND, ModBlocks.SLAB_SAND, IdUtils.ofTE("block_from_slab_sand"));
        offerBlockFromSlabs(exporter, Blocks.RED_SAND, ModBlocks.SLAB_RED_SAND, IdUtils.ofTE("block_from_slab_red_sand"));
        offerBlockFromSlabs(exporter, Blocks.GRAVEL, ModBlocks.SLAB_GRAVEL, IdUtils.ofTE("block_from_slab_gravel"));
        offerBlockFromSlabs(exporter, ModBlocks.COBBLESTONE_LOOSE, ModBlocks.SLAB_COBBLESTONE_LOOSE, IdUtils.ofTE("block_from_slab_cobblestone_loose"));
        offerBlockFromSlabs(exporter, ModBlocks.COBBLED_DEEPSLATE_LOOSE, ModBlocks.SLAB_COBBLED_DEEPSLATE_LOOSE, IdUtils.ofTE("block_from_slab_cobbled_deepslate_loose"));
        offerBlockFromSlabs(exporter, ModBlocks.GRANITE_LOOSE, ModBlocks.SLAB_GRANITE_LOOSE, IdUtils.ofTE("block_from_slab_granite_loose"));
        offerBlockFromSlabs(exporter, ModBlocks.ANDESITE_LOOSE, ModBlocks.SLAB_ANDESITE_LOOSE, IdUtils.ofTE("block_from_slab_andesite_loose"));
        offerBlockFromSlabs(exporter, ModBlocks.DIORITE_LOOSE, ModBlocks.SLAB_DIORITE_LOOSE, IdUtils.ofTE("block_from_slab_diorite_loose"));
        offerBlockFromSlabs(exporter, ModBlocks.BRICKS_LOOSE, ModBlocks.SLAB_BRICKS_LOOSE, IdUtils.ofTE("block_from_slab_bricks_loose"));
        offerBlockFromSlabs(exporter, ModBlocks.STONE_BRICKS_LOOSE, ModBlocks.SLAB_STONE_BRICKS_LOOSE, IdUtils.ofTE("block_from_slab_stone_bricks_loose"));
        offerBlockFromSlabs(exporter, ModBlocks.DIRT_PACKED, ModBlocks.SLAB_DIRT_PACKED, IdUtils.ofTE("block_from_slab_packed_dirt"));

        offerSimpleConverting(exporter, ModBlocks.DIRT_LOOSE, ModBlocks.SLAB_DIRT_PACKED, IdUtils.ofTE("dirt_loose_from_slab_dirt_packed"));

        offerBlockFromSlabs(exporter, ModBlocks.DEEPSLATE_BRICKS_LOOSE, ModBlocks.SLAB_DEEPSLATE_BRICKS_LOOSE, IdUtils.ofTE("block_from_slab_deepslate_bricks_loose"));
        offerBlockFromSlabs(exporter, ModBlocks.NETHER_BRICKS_LOOSE, ModBlocks.SLAB_NETHER_BRICKS_LOOSE, IdUtils.ofTE("block_from_slab_nether_bricks_loose"));

        // From piles, small stones/shards
        offerBlockFromLesserDrops(exporter, Blocks.SAND, ModItems.PILE_SAND, IdUtils.ofTE("block_from_pile_sand"));
        offerBlockFromLesserDrops(exporter, Blocks.RED_SAND, ModItems.PILE_RED_SAND, IdUtils.ofTE("block_from_pile_red_sand"));
        offerBlockFromLesserDrops(exporter, Blocks.GRAVEL, ModItems.PILE_GRAVEL, IdUtils.ofTE("block_from_pile_gravel"));
        offerBlockFromLesserDrops(exporter, ModBlocks.DIRT_LOOSE, ModItems.PILE_DIRT, IdUtils.ofTE("block_from_pile_dirt"));
        offerBlockFromLesserDrops(exporter, ModBlocks.COBBLESTONE_LOOSE, ModItems.SMALL_STONE, IdUtils.ofTE("block_from_small_stone"));
        offerBlockFromLesserDrops(exporter, ModBlocks.COBBLED_DEEPSLATE_LOOSE, ModItems.SMALL_STONE_2, IdUtils.ofTE("block_from_small_stone_2"));
        offerBlockFromLesserDrops(exporter, ModBlocks.GRANITE_LOOSE, ModItems.SHARD_GRANITE, IdUtils.ofTE("block_from_shard_granite"));
        offerBlockFromLesserDrops(exporter, ModBlocks.ANDESITE_LOOSE, ModItems.SHARD_ANDESITE, IdUtils.ofTE("block_from_shard_andesite"));
        offerBlockFromLesserDrops(exporter, ModBlocks.DIORITE_LOOSE, ModItems.SHARD_DIORITE, IdUtils.ofTE("block_from_shard_diorite"));
        offerBlockFromLesserDrops(exporter, ModBlocks.BRICKS_LOOSE, Items.BRICK, IdUtils.ofTE("block_from_brick"));
        offerBlockFromLesserDrops(exporter, ModBlocks.NETHER_BRICKS_LOOSE, Items.NETHER_BRICK, IdUtils.ofTE("block_from_nether_brick"));

        offerStoneBricksBlockFromLesserDrops(exporter, ModBlocks.STONE_BRICKS_LOOSE, ModItems.STONE_BRICK, IdUtils.ofTE("block_from_stone_brick"));
        offerStoneBricksBlockFromLesserDrops(exporter, ModBlocks.DEEPSLATE_BRICKS_LOOSE, ModItems.STONE_BRICK_2, IdUtils.ofTE("deepslate_from_stone_brick"));
    }

    private void addStairBlockRecipes(RecipeExporter exporter) {
        // Simple stairs recipes
        offerSimpleStairs(exporter, ModBlocks.WHITE_STONE_STAIRS, ModBlocks.WHITE_STONE, IdUtils.ofTE("white_stone_stairs"));
        offerSimpleStairs(exporter, ModBlocks.WHITE_COBBLESTONE_STAIRS, ModBlocks.WHITE_COBBLESTONE, IdUtils.ofTE("white_cobblestone_stairs"));

        // 2x2 and 3x3 stair recipes in one method.
        offerCombinedStairs(exporter, ModBlocks.COBBLESTONE_LOOSE_STAIRS, ModBlocks.COBBLESTONE_LOOSE, ModItems.SMALL_STONE, IdUtils.ofTE("stairs_cobblestone_loose"));
        offerCombinedStairs(exporter, ModBlocks.COBBLED_DEEPSLATE_LOOSE_STAIRS, ModBlocks.COBBLED_DEEPSLATE_LOOSE, ModItems.SMALL_STONE_2, IdUtils.ofTE("stairs_cobbled_deepslate_loose"));
        offerCombinedStairs(exporter, ModBlocks.GRANITE_LOOSE_STAIRS, ModBlocks.GRANITE_LOOSE, ModItems.SHARD_GRANITE, IdUtils.ofTE("stairs_granite_loose"));
        offerCombinedStairs(exporter, ModBlocks.ANDESITE_LOOSE_STAIRS, ModBlocks.ANDESITE_LOOSE, ModItems.SHARD_ANDESITE, IdUtils.ofTE("stairs_andesite_loose"));
        offerCombinedStairs(exporter, ModBlocks.DIORITE_LOOSE_STAIRS, ModBlocks.DIORITE_LOOSE, ModItems.SHARD_DIORITE, IdUtils.ofTE("stairs_diorite_loose"));
        offerCombinedStairs(exporter, ModBlocks.BRICKS_LOOSE_STAIRS, ModBlocks.BRICKS_LOOSE, Items.BRICK, IdUtils.ofTE("stairs_bricks_loose"));
        offerCombinedStairs(exporter, ModBlocks.STONE_BRICKS_LOOSE_STAIRS, ModBlocks.STONE_BRICKS_LOOSE, ModItems.STONE_BRICK, IdUtils.ofTE("stairs_stone_bricks_loose"));
        offerCombinedStairs(exporter, ModBlocks.DEEPSLATE_BRICKS_LOOSE_STAIRS, ModBlocks.DEEPSLATE_BRICKS_LOOSE, ModItems.STONE_BRICK_2, IdUtils.ofTE("stairs_deepslate_bricks_loose"));
        offerCombinedStairs(exporter, ModBlocks.NETHER_BRICKS_LOOSE_STAIRS, ModBlocks.NETHER_BRICKS_LOOSE, Items.NETHER_BRICK, IdUtils.ofTE("stairs_nether_bricks_loose"));

        offerVeryCompactStairs(exporter, ModBlocks.STONE_BRICKS_LOOSE_STAIRS, ModItems.STONE_BRICK, IdUtils.ofTE("stairs_stone_bricks_loose"));
        offerVeryCompactStairs(exporter, ModBlocks.DEEPSLATE_BRICKS_LOOSE_STAIRS, ModItems.STONE_BRICK_2, IdUtils.ofTE("stairs_deepslate_bricks_loose"));
    }

    private void addSlabBlockRecipes(RecipeExporter exporter) {
        offerSimpleSlabs(exporter, ModBlocks.SLAB_WHITE_STONE, ModBlocks.WHITE_STONE, IdUtils.ofTE("slab_white_stone"));
        offerSimpleSlabs(exporter, ModBlocks.SLAB_WHITE_COBBLESTONE, ModBlocks.WHITE_COBBLESTONE, IdUtils.ofTE("slab_white_cobblestone"));
        offerSimpleSlabs(exporter, ModBlocks.SLAB_DIRT_PACKED, ModBlocks.SLAB_DIRT_PACKED, IdUtils.ofTE("slab_dirt_packed"));

        // From lesser drops/items (stones/piles/dust)
        offerSlabFromLesserDrops(exporter, ModBlocks.SLAB_DIRT, ModItems.PILE_DIRT, IdUtils.ofTE("slab_dirt"));
        offerSlabFromLesserDrops(exporter, ModBlocks.SLAB_SAND, ModItems.PILE_SAND, IdUtils.ofTE("slab_sand"));
        offerSlabFromLesserDrops(exporter, ModBlocks.SLAB_RED_SAND, ModItems.PILE_RED_SAND, IdUtils.ofTE("slab_red_sand"));
        offerSlabFromLesserDrops(exporter, ModBlocks.SLAB_GRAVEL, ModItems.PILE_GRAVEL, IdUtils.ofTE("slab_gravel"));
        offerSlabFromLesserDrops(exporter, ModBlocks.SLAB_COBBLESTONE_LOOSE, ModItems.SMALL_STONE, IdUtils.ofTE("slab_cobblestone_loose"));
        offerSlabFromLesserDrops(exporter, ModBlocks.SLAB_COBBLED_DEEPSLATE_LOOSE, ModItems.SMALL_STONE_2, IdUtils.ofTE("slab_cobbled_deepslate_loose"));
        offerSlabFromLesserDrops(exporter, ModBlocks.SLAB_GRANITE_LOOSE, ModItems.SHARD_GRANITE, IdUtils.ofTE("slab_granite_loose"));
        offerSlabFromLesserDrops(exporter, ModBlocks.SLAB_ANDESITE_LOOSE, ModItems.SHARD_ANDESITE, IdUtils.ofTE("slab_andesite_loose"));
        offerSlabFromLesserDrops(exporter, ModBlocks.SLAB_DIORITE_LOOSE, ModItems.SHARD_DIORITE, IdUtils.ofTE("slab_diorite_loose"));
        offerSlabFromLesserDrops(exporter, ModBlocks.SLAB_BRICKS_LOOSE, Items.BRICK, IdUtils.ofTE("slab_bricks_loose"));
        offerSlabFromLesserDrops(exporter, ModBlocks.SLAB_NETHER_BRICKS_LOOSE, Items.NETHER_BRICK, IdUtils.ofTE("slab_nether_bricks_loose"));

        offerStoneBrickSlabFromLesserDrops(exporter, ModBlocks.SLAB_STONE_BRICKS_LOOSE, ModItems.STONE_BRICK, IdUtils.ofTE("slab_stone_bricks_loose"));
        offerStoneBrickSlabFromLesserDrops(exporter, ModBlocks.SLAB_DEEPSLATE_BRICKS_LOOSE, ModItems.STONE_BRICK_2, IdUtils.ofTE("slab_deepslate_bricks_loose"));

        // From full loose blocks
        offerSlabsFromBlock(exporter, ModBlocks.SLAB_DIRT, ModBlocks.DIRT_LOOSE, IdUtils.ofTE("slab_dirt_from_block"));
        offerSlabsFromBlock(exporter, ModBlocks.SLAB_SAND, Blocks.SAND, IdUtils.ofTE("slab_sand_from_block"));
        offerSlabsFromBlock(exporter, ModBlocks.SLAB_RED_SAND, Blocks.RED_SAND, IdUtils.ofTE("slab_red_sand_from_block"));
        offerSlabsFromBlock(exporter, ModBlocks.SLAB_GRAVEL, Blocks.GRAVEL, IdUtils.ofTE("slab_gravel_from_block"));
        offerSlabsFromBlock(exporter, ModBlocks.SLAB_COBBLESTONE_LOOSE, ModBlocks.COBBLESTONE_LOOSE, IdUtils.ofTE("slab_cobblestone_loose_from_block"));
        offerSlabsFromBlock(exporter, ModBlocks.SLAB_COBBLED_DEEPSLATE_LOOSE, ModBlocks.COBBLED_DEEPSLATE_LOOSE, IdUtils.ofTE("slab_cobbled_deepslate_loose_from_block"));
        offerSlabsFromBlock(exporter, ModBlocks.SLAB_GRANITE_LOOSE, ModBlocks.GRANITE_LOOSE, IdUtils.ofTE("slab_granite_loose_from_block"));
        offerSlabsFromBlock(exporter, ModBlocks.SLAB_ANDESITE_LOOSE, ModBlocks.ANDESITE_LOOSE, IdUtils.ofTE("slab_andesite_loose_from_block"));
        offerSlabsFromBlock(exporter, ModBlocks.SLAB_DIORITE_LOOSE, ModBlocks.DIORITE_LOOSE, IdUtils.ofTE("slab_diorite_loose_from_block"));
        offerSlabsFromBlock(exporter, ModBlocks.SLAB_BRICKS_LOOSE, ModBlocks.BRICKS_LOOSE, IdUtils.ofTE("slab_bricks_loose_from_block"));
        offerSlabsFromBlock(exporter, ModBlocks.SLAB_NETHER_BRICKS_LOOSE, ModBlocks.NETHER_BRICKS_LOOSE, IdUtils.ofTE("slab_nether_bricks_loose_from_block"));
        offerSlabsFromBlock(exporter, ModBlocks.SLAB_DIRT_PACKED, ModBlocks.DIRT_PACKED, IdUtils.ofTE("slab_dirt_packed_from_block"));

        offerSlabsFromBlock(exporter, ModBlocks.SLAB_STONE_BRICKS_LOOSE, ModBlocks.STONE_BRICKS_LOOSE, IdUtils.ofTE("slab_stone_bricks_loose_from_block"));
        offerSlabsFromBlock(exporter, ModBlocks.SLAB_DEEPSLATE_BRICKS_LOOSE, ModBlocks.DEEPSLATE_BRICKS_LOOSE, IdUtils.ofTE("slab_deepslate_bricks_loose_from_block"));
    }

    private void addItemRecipes(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.COPPER_INGOT)
                .input('N', ModItems.COPPER_NUGGET)
                .pattern("NNN")
                .pattern("NNN")
                .pattern("NNN")
                .criterion("has_copper_nugget", conditionsFromItem(ModItems.COPPER_NUGGET))
                .offerTo(exporter, IdUtils.ofTE("copper_ingot_from_copper_nugget"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.NETHERITE_INGOT)
                .input('N', ModItems.NETHERITE_NUGGET)
                .pattern("NNN")
                .pattern("NNN")
                .pattern("NNN")
                .criterion("has_netherite_nugget", conditionsFromItem(ModItems.NETHERITE_NUGGET))
                .offerTo(exporter, IdUtils.ofTE("netherite_ingot_from_netherite_nugget"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COPPER_NUGGET, 9)
                .input(Items.COPPER_INGOT)
                .criterion("has_copper_ingot", conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter, IdUtils.ofTE("copper_nugget_from_copper_ingot"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.NETHERITE_NUGGET, 9)
                .input(Items.NETHERITE_INGOT)
                .criterion("has_netherite_ingot", conditionsFromItem(Items.NETHERITE_INGOT))
                .offerTo(exporter, IdUtils.ofTE("netherite_nugget_from_netherite_ingot"));
    }

    private void addExistingCopyForVanillaItemRecipes(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Items.ANVIL)
                .input('I', Items.IRON_INGOT)
                .pattern("III")
                .pattern(" I ")
                .pattern("III")
                .criterion("has_iron_nugget", conditionsFromItem(Items.IRON_NUGGET))
                .offerTo(exporter, IdUtils.ofTE("anvil"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Items.SHIELD)
                .input('P', ItemTags.PLANKS)
                .input('N', Items.IRON_NUGGET)
                .pattern(" N ")
                .pattern("NPN")
                .pattern(" N ")
                .criterion("has_iron_nugget", conditionsFromItem(Items.IRON_NUGGET))
                .offerTo(exporter,  IdUtils.ofTE("shield"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.COMPASS)
                .input('R', Items.REDSTONE)
                .input('N', Items.IRON_NUGGET)
                .pattern(" N ")
                .pattern("NRN")
                .pattern(" N ")
                .criterion("has_iron_nugget", conditionsFromItem(Items.IRON_NUGGET))
                .offerTo(exporter,  IdUtils.ofTE("compass"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, Items.CLOCK)
                .input('R', Items.QUARTZ)
                .input('N', Items.GOLD_NUGGET)
                .pattern(" N ")
                .pattern("NRN")
                .pattern(" N ")
                .criterion("has_gold_nugget", conditionsFromItem(Items.GOLD_NUGGET))
                .offerTo(exporter,  IdUtils.ofTE("clock"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.BUCKET)
                .input('N', Items.IRON_NUGGET)
                .pattern("N N")
                .pattern("N N")
                .pattern("NNN")
                .criterion("has_iron_nugget", conditionsFromItem(Items.IRON_NUGGET))
                .offerTo(exporter,  IdUtils.ofTE("bucket"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.TOOLS, Items.FLINT_AND_STEEL)
                .input(Items.FLINT)
                .input(Items.IRON_NUGGET)
                .criterion("has_flint", conditionsFromItem(Items.FLINT))
                .offerTo(exporter,  IdUtils.ofTE("flint_and_steel"));
    }

    // We add recipes for some existing vanilla blocks.
    // In this case, we need a new recipe for Furnace because the one with making it out of
    // 8 loose cobblestone is too hard to acquire with just stone chisel.
    private void addExistingCopyForVanillaBlockRecipes(RecipeExporter exporter) {
        // change furnace recipe
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, Items.FURNACE)
                .input('#', ModBlocks.SLAB_COBBLESTONE_LOOSE)
                .pattern("##")
                .pattern("##")
                .criterion("has_slab_cobblestone_loose", conditionsFromItem(ModBlocks.SLAB_COBBLESTONE_LOOSE))
                .offerTo(exporter, IdUtils.ofTE("furnace"));

        // change clay block recipes
        offerLesserDropsFromBlock(exporter, Items.CLAY_BALL,4, Blocks.CLAY, IdUtils.ofTE("clay_ball_from_clay_block"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Blocks.CLAY)
                .input('#', Items.CLAY_BALL)
                .pattern("##")
                .pattern("##")
                .criterion(hasItem(Items.CLAY_BALL), conditionsFromItem(Items.CLAY_BALL))
                .offerTo(exporter, IdUtils.ofTE("clay"));
    }

    private void addCookingRecipes(RecipeExporter exporter) {
        this.smeltRecipeFor(ModBlocks.WHITE_COBBLESTONE.asItem(),  ModBlocks.WHITE_STONE.asItem(), RecipeCategory.BUILDING_BLOCKS, exporter);

        this.blastRecipeFor(ModBlocks.WHITE_COBBLESTONE.asItem(),  Items.END_STONE, RecipeCategory.BUILDING_BLOCKS, exporter);

        this.smeltAndBlastRecipeFor(Items.STONE, ModBlocks.COBBLESTONE_LOOSE.asItem(), RecipeCategory.BUILDING_BLOCKS, exporter);
        this.smeltAndBlastRecipeFor(Items.DEEPSLATE, ModBlocks.COBBLED_DEEPSLATE_LOOSE.asItem(), RecipeCategory.BUILDING_BLOCKS, exporter);
        this.smeltAndBlastRecipeFor(Items.ANDESITE, ModBlocks.ANDESITE_LOOSE.asItem(), RecipeCategory.BUILDING_BLOCKS, exporter);
        this.smeltAndBlastRecipeFor(Items.GRANITE, ModBlocks.GRANITE_LOOSE.asItem(), RecipeCategory.BUILDING_BLOCKS, exporter);
        this.smeltAndBlastRecipeFor(Items.DIORITE, ModBlocks.DIORITE_LOOSE.asItem(), RecipeCategory.BUILDING_BLOCKS, exporter);
        this.smeltAndBlastRecipeFor(Items.BRICKS, ModBlocks.BRICKS_LOOSE.asItem(), RecipeCategory.BUILDING_BLOCKS, exporter);
        this.smeltAndBlastRecipeFor(Items.STONE_BRICKS, ModBlocks.STONE_BRICKS_LOOSE.asItem(), RecipeCategory.BUILDING_BLOCKS, exporter);
        this.smeltAndBlastRecipeFor(Items.DEEPSLATE_BRICKS, ModBlocks.DEEPSLATE_BRICKS_LOOSE.asItem(), RecipeCategory.BUILDING_BLOCKS, exporter);
        this.smeltAndBlastRecipeFor(Items.NETHER_BRICK, ModItems.NETHER_BRICK_UNFIRED, RecipeCategory.BUILDING_BLOCKS, exporter);
    }

    private void smeltAndSmokingRecipe(Item output, Item input, RecipeCategory category, RecipeExporter exporter) {
        this.smeltRecipeFor(output, input, category, exporter);
        this.smokingRecipeFor(output, input, category, exporter);
    }

    private void smeltAndBlastRecipeFor(Item output, Item input, RecipeCategory category, RecipeExporter exporter) {
        this.smeltRecipeFor(output, input, category, exporter);
        this.blastRecipeFor(output, input, category, exporter);
    }

    private void smeltRecipeFor(Item output, Item input, RecipeCategory category, RecipeExporter exporter) {
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(input), category, output, 0.10f, 200)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter, IdUtils.ofTE(Registries.ITEM.getId(output).getPath() + "_from_smelting"));
    }

    private void smokingRecipeFor(Item output, Item input, RecipeCategory category, RecipeExporter exporter) {
        CookingRecipeJsonBuilder.createSmoking(Ingredient.ofItems(input), category, output, 0.15f, 100)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter, IdUtils.ofTE(Registries.ITEM.getId(output).getPath() + "_from_smoking"));
    }

    private void blastRecipeFor(Item output, Item input, RecipeCategory category, RecipeExporter exporter) {
        CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(input), category, output, 0.15f, 100)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter, IdUtils.ofTE(Registries.ITEM.getId(output).getPath() + "_from_blasting"));
    }

}