package org.btwr.tough_environment.datagen.loot_table;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import org.btwr.tough_environment.block.ModBlocks;
import org.btwr.tough_environment.datagen.loot_table.util.LootConditions;
import org.btwr.tough_environment.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;


public class VanillaOverrideLootTableProvider extends BaseLootTableProvider {

    public VanillaOverrideLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public String getName() {
        return "Tough Environment Vanilla Override Loot Tables";
    }

    @Override
    public void generate() {
        this.generateAggregateBlockDrops();
        this.generateStoneLikeBlockDrops();

        //this.addDrop(Blocks.DIAMOND_ORE, block -> oreDrops(block, Items.DIAMOND));
        //this.addDrop(Blocks.DEEPSLATE_DIAMOND_ORE, block -> oreDrops(block, Items.DIAMOND));

    }

    private void generateAggregateBlockDrops() {
        /** Blocks that require a tool to looseBlock fully */
        addDrop(Blocks.DIRT, dropsForLooseAggregate(
                ModBlocks.DIRT_LOOSE, ModBlocks.DIRT_LOOSE, LootConditions.WITH_SHOVEL_FULLY_HARVESTS, ModItems.PILE_DIRT, 6)
        );

        addDrop(Blocks.SAND, dropsForLooseAggregate(
                Blocks.SAND, Blocks.SAND, LootConditions.WITH_SHOVEL_FULLY_HARVESTS, ModItems.PILE_SAND, 6)
        );

        addDrop(Blocks.RED_SAND,
                dropsForLooseAggregate(Blocks.RED_SAND, Blocks.RED_SAND, LootConditions.WITH_SHOVEL_FULLY_HARVESTS, ModItems.PILE_RED_SAND, 6)
        );

        addDrop(Blocks.GRAVEL,
                dropsForLooseAggregate(Blocks.GRAVEL, Blocks.GRAVEL, LootConditions.WITH_SHOVEL_FULLY_HARVESTS,
                        ModItems.PILE_GRAVEL, 6, List.of(new AdditionalDrop(Items.FLINT, 1, 0.1f))
                )
        );

        addDrop(Blocks.GRASS_BLOCK,
                dropsForLooseAggregate(Blocks.GRASS_BLOCK, ModBlocks.DIRT_LOOSE, LootConditions.WITH_SHOVEL_FULLY_HARVESTS,
                        ModItems.PILE_DIRT, 6, null
                )
        );

        addDrop(Blocks.CLAY,
                dropsForBreakingToLooseBlock(Blocks.CLAY, Blocks.CLAY, LootConditions.WITH_SHOVEL_FULLY_HARVESTS, Items.CLAY_BALL, 4
                )
        );

        this.generateDirtLikeBlockDrops();
    }

    /**
     * Podzol, mycelium, coarse dirt and dirt path are hand-written under
     * {@code resources/data/minecraft/loot_table/blocks}; adding them here too would give
     * processResources two copies of the same file. Rooted dirt was the one member of the family
     * without a table, so it kept vanilla behaviour and never broke down into piles.
     */
    private void generateDirtLikeBlockDrops() {
        // Vanilla drops rooted dirt itself, so that is what a proper shovel keeps giving
        addDrop(Blocks.ROOTED_DIRT,
                dropsForLooseAggregate(Blocks.ROOTED_DIRT, Blocks.ROOTED_DIRT, LootConditions.WITH_SHOVEL_FULLY_HARVESTS, ModItems.PILE_DIRT, 6)
        );
    }

    private void generateStoneLikeBlockDrops() {
        /** Blocks that require a tool to looseBlock fully */

        addDrop(Blocks.STONE,
                dropsForStoneLike(Blocks.STONE, ModBlocks.COBBLESTONE_LOOSE, ModItems.PILE_GRAVEL, ModItems.SMALL_STONE, ModItems.STONE_BRICK)
        );

        addDrop(Blocks.DEEPSLATE,
                dropsForStoneLike(Blocks.DEEPSLATE, ModBlocks.COBBLED_DEEPSLATE_LOOSE, ModItems.PILE_GRAVEL, ModItems.SMALL_STONE_2, ModItems.STONE_BRICK_2)
        );

        addDrop(Blocks.ANDESITE,
                dropsForAlternativeStoneLike(Blocks.ANDESITE, ModBlocks.ANDESITE_LOOSE, ModItems.PILE_GRAVEL, ModItems.SHARD_ANDESITE)
        );

        addDrop(Blocks.GRANITE,
                dropsForAlternativeStoneLike(Blocks.GRANITE, ModBlocks.GRANITE_LOOSE, ModItems.PILE_GRAVEL, ModItems.SHARD_GRANITE)
        );

        addDrop(Blocks.DIORITE,
                dropsForAlternativeStoneLike(Blocks.DIORITE, ModBlocks.DIORITE_LOOSE, ModItems.PILE_GRAVEL, ModItems.SHARD_DIORITE));

        addDrop(Blocks.COBBLESTONE_SLAB,
                dropsForSlab(Blocks.COBBLESTONE_SLAB, ModBlocks.SLAB_COBBLESTONE_LOOSE, ModItems.SMALL_STONE, 4)
        );

        addDrop(Blocks.COBBLED_DEEPSLATE_SLAB,
                dropsForSlab(Blocks.COBBLED_DEEPSLATE_SLAB, ModBlocks.SLAB_COBBLED_DEEPSLATE_LOOSE, ModItems.SMALL_STONE_2, 4)
        );

        addDrop(Blocks.ANDESITE_SLAB,
                dropsForSlab(Blocks.ANDESITE_SLAB, ModBlocks.SLAB_ANDESITE_LOOSE, ModItems.SHARD_ANDESITE, 4)
        );

        addDrop(Blocks.GRANITE_SLAB,
                dropsForSlab(Blocks.GRANITE_SLAB, ModBlocks.SLAB_GRANITE_LOOSE, ModItems.SHARD_GRANITE, 4)
        );

        addDrop(Blocks.DIORITE_SLAB,
                dropsForSlab(Blocks.DIORITE_SLAB, ModBlocks.SLAB_DIORITE_LOOSE, ModItems.SHARD_DIORITE, 4)
        );

        /** Non-loose blocks that break into loose **/

        // full blocks
        addDrop(Blocks.BRICKS,
                dropsForBreakingToLooseBlock(Blocks.BRICKS, ModBlocks.BRICKS_LOOSE,
                        LootConditions.WITH_PICKAXE_FULLY_HARVESTS, Items.BRICK, 8
                )
        );

        addDrop(Blocks.STONE_BRICKS,
                dropsForBreakingToLooseBlock(Blocks.STONE_BRICKS, ModBlocks.STONE_BRICKS_LOOSE,
                        LootConditions.WITH_PICKAXE_FULLY_HARVESTS, ModItems.STONE_BRICK, 8
                )
        );

        addDrop(Blocks.DEEPSLATE_BRICKS,
                dropsForBreakingToLooseBlock(Blocks.DEEPSLATE_BRICKS, ModBlocks.DEEPSLATE_BRICKS_LOOSE,
                        LootConditions.WITH_PICKAXE_FULLY_HARVESTS, ModItems.STONE_BRICK_2, 8
                )
        );

        addDrop(Blocks.NETHER_BRICKS,
                dropsForBreakingToLooseBlock(Blocks.NETHER_BRICKS, ModBlocks.NETHER_BRICKS_LOOSE,
                        LootConditions.WITH_PICKAXE_FULLY_HARVESTS, Items.NETHER_BRICK, 8
                )
        );

        // slabs
        addDrop(Blocks.BRICK_SLAB,
                dropsForBreakingToLooseBlock(Blocks.BRICK_SLAB, ModBlocks.SLAB_BRICKS_LOOSE,
                        LootConditions.WITH_PICKAXE_FULLY_HARVESTS, Items.BRICK, 4
                )
        );

        addDrop(Blocks.STONE_BRICK_SLAB,
                dropsForBreakingToLooseBlock(Blocks.STONE_BRICK_SLAB, ModBlocks.SLAB_STONE_BRICKS_LOOSE,
                        LootConditions.WITH_PICKAXE_FULLY_HARVESTS, ModItems.STONE_BRICK, 4
                )
        );

        addDrop(Blocks.DEEPSLATE_BRICK_SLAB,
                dropsForBreakingToLooseBlock(Blocks.DEEPSLATE_BRICK_SLAB, ModBlocks.SLAB_DEEPSLATE_BRICKS_LOOSE,
                        LootConditions.WITH_PICKAXE_FULLY_HARVESTS, ModItems.STONE_BRICK_2, 4
                )
        );

        addDrop(Blocks.NETHER_BRICK_SLAB,
                dropsForBreakingToLooseBlock(Blocks.NETHER_BRICK_SLAB, ModBlocks.SLAB_NETHER_BRICKS_LOOSE,
                        LootConditions.WITH_PICKAXE_FULLY_HARVESTS, Items.NETHER_BRICK, 4
                )
        );

        // stairs
        addDrop(Blocks.COBBLESTONE_STAIRS,
                dropsForBreakingToLooseBlock(Blocks.COBBLESTONE_STAIRS, ModBlocks.COBBLESTONE_LOOSE_STAIRS,
                        LootConditions.WITH_PICKAXE_FULLY_HARVESTS, ModItems.SMALL_STONE, 3
                )
        );

        addDrop(Blocks.COBBLED_DEEPSLATE_STAIRS,
                dropsForBreakingToLooseBlock(Blocks.COBBLED_DEEPSLATE_STAIRS, ModBlocks.COBBLED_DEEPSLATE_LOOSE_STAIRS,
                        LootConditions.WITH_PICKAXE_FULLY_HARVESTS, ModItems.SMALL_STONE_2, 3
                )
        );

        addDrop(Blocks.GRANITE_STAIRS,
                dropsForBreakingToLooseBlock(Blocks.GRANITE_STAIRS, ModBlocks.GRANITE_LOOSE_STAIRS,
                        LootConditions.WITH_PICKAXE_FULLY_HARVESTS, ModItems.SHARD_GRANITE, 3
                )
        );

        addDrop(Blocks.ANDESITE_STAIRS,
                dropsForBreakingToLooseBlock(Blocks.ANDESITE_STAIRS, ModBlocks.ANDESITE_LOOSE_STAIRS,
                        LootConditions.WITH_PICKAXE_FULLY_HARVESTS, ModItems.SHARD_ANDESITE, 3
                )
        );

        addDrop(Blocks.DIORITE_STAIRS,
                dropsForBreakingToLooseBlock(Blocks.DIORITE_STAIRS, ModBlocks.DIORITE_LOOSE_STAIRS,
                        LootConditions.WITH_PICKAXE_FULLY_HARVESTS, ModItems.SHARD_DIORITE, 3
                )
        );

        addDrop(Blocks.BRICK_STAIRS,
                dropsForBreakingToLooseBlock(Blocks.BRICK_STAIRS, ModBlocks.BRICKS_LOOSE_STAIRS,
                        LootConditions.WITH_PICKAXE_FULLY_HARVESTS, Items.BRICK, 3
                )
        );

        addDrop(Blocks.STONE_BRICK_STAIRS,
                dropsForBreakingToLooseBlock(Blocks.STONE_BRICK_STAIRS, ModBlocks.STONE_BRICKS_LOOSE_STAIRS,
                        LootConditions.WITH_PICKAXE_FULLY_HARVESTS, ModItems.STONE_BRICK, 3
                )
        );

        addDrop(Blocks.DEEPSLATE_BRICK_STAIRS,
                dropsForBreakingToLooseBlock(Blocks.DEEPSLATE_BRICK_STAIRS, ModBlocks.DEEPSLATE_BRICKS_LOOSE_STAIRS,
                        LootConditions.WITH_PICKAXE_FULLY_HARVESTS, ModItems.STONE_BRICK_2, 3
                )
        );

        addDrop(Blocks.NETHER_BRICK_STAIRS,
                dropsForBreakingToLooseBlock(Blocks.NETHER_BRICK_STAIRS, ModBlocks.NETHER_BRICKS_LOOSE_STAIRS,
                        LootConditions.WITH_PICKAXE_FULLY_HARVESTS, Items.NETHER_BRICK, 3
                )
        );

        addDrop(Blocks.COAL_ORE,
                dropsForOre(Items.COAL_ORE, Items.COAL, ModItems.SMALL_STONE, ModItems.DUST_COAL, WITH_PICKAXE_FULLY_HARVESTS));
        addDrop(Blocks.DEEPSLATE_COAL_ORE,
                dropsForOre(Items.DEEPSLATE_COAL_ORE, Items.COAL, ModItems.SMALL_STONE_2, ModItems.DUST_COAL, WITH_ADVANCED_PICKAXES));
        addDrop(Blocks.COPPER_ORE,
                dropsForOre(Items.COPPER_ORE, Items.RAW_COPPER, ModItems.SMALL_STONE, ModItems.DUST_COPPER, WITH_PICKAXE_FULLY_HARVESTS));
        addDrop(Blocks.DEEPSLATE_COPPER_ORE,
                dropsForOre(Items.DEEPSLATE_COPPER_ORE, Items.RAW_COPPER, ModItems.SMALL_STONE_2, ModItems.DUST_COPPER, WITH_ADVANCED_PICKAXES));
        addDrop(Blocks.IRON_ORE,
                dropsForOre(Items.IRON_ORE, Items.RAW_IRON, ModItems.SMALL_STONE, ModItems.DUST_IRON, WITH_PICKAXE_FULLY_HARVESTS));
        addDrop(Blocks.DEEPSLATE_IRON_ORE,
                dropsForOre(Items.DEEPSLATE_IRON_ORE, Items.RAW_IRON, ModItems.SMALL_STONE_2, ModItems.DUST_IRON, WITH_ADVANCED_PICKAXES));
        addDrop(Blocks.GOLD_ORE,
                dropsForOre(Items.GOLD_ORE, Items.RAW_GOLD, ModItems.SMALL_STONE, ModItems.DUST_GOLD, WITH_PICKAXE_FULLY_HARVESTS));
        addDrop(Blocks.DEEPSLATE_GOLD_ORE,
                dropsForOre(Items.DEEPSLATE_GOLD_ORE, Items.RAW_GOLD, ModItems.SMALL_STONE_2, ModItems.DUST_GOLD, WITH_ADVANCED_PICKAXES));
        addDrop(Blocks.LAPIS_ORE,
                lapisOreDrops(Items.LAPIS_ORE, Items.LAPIS_LAZULI, ModItems.SMALL_STONE, WITH_PICKAXE_FULLY_HARVESTS));
        addDrop(Blocks.DEEPSLATE_LAPIS_ORE,
                lapisOreDrops(Items.DEEPSLATE_LAPIS_ORE, Items.LAPIS_LAZULI, ModItems.SMALL_STONE_2, WITH_ADVANCED_PICKAXES));
        addDrop(Blocks.REDSTONE_ORE,
                redstoneOreDrops(Items.REDSTONE_ORE, Items.REDSTONE, ModItems.SMALL_STONE, WITH_PICKAXE_FULLY_HARVESTS));
        addDrop(Blocks.DEEPSLATE_REDSTONE_ORE,
                redstoneOreDrops(Items.DEEPSLATE_REDSTONE_ORE, Items.REDSTONE, ModItems.SMALL_STONE_2, WITH_ADVANCED_PICKAXES));
        addDrop(Blocks.DIAMOND_ORE,
                dropsForGem(Items.DIAMOND_ORE, Items.DIAMOND, ModItems.SMALL_STONE, WITH_PICKAXE_FULLY_HARVESTS));
        addDrop(Blocks.DEEPSLATE_DIAMOND_ORE,
                dropsForGem(Items.DEEPSLATE_DIAMOND_ORE, Items.DIAMOND, ModItems.SMALL_STONE_2, WITH_ADVANCED_PICKAXES));
        addDrop(Blocks.EMERALD_ORE,
                dropsForGem(Items.EMERALD_ORE, Items.EMERALD, ModItems.SMALL_STONE, WITH_PICKAXE_FULLY_HARVESTS));
        addDrop(Blocks.DEEPSLATE_EMERALD_ORE,
                dropsForGem(Items.DEEPSLATE_EMERALD_ORE, Items.EMERALD, ModItems.SMALL_STONE_2, WITH_ADVANCED_PICKAXES));
    }

}