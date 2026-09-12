package org.btwr.tough_environment.datagen.loot_table;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import org.btwr.tough_environment.block.ModBlocks;
import org.btwr.tough_environment.datagen.loot_table.util.LootConditions;
import org.btwr.tough_environment.item.ModItems;

import java.util.concurrent.CompletableFuture;


public class TELootTableProvider extends BaseLootTableProvider {
    
    public TELootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public String getName() {
        return "Tough Environment Loot Tables";
    }

    @Override
    public void generate() {
        this.forMod();
    }

    private void forMod() {

        // Loose blocks
        addDrop(ModBlocks.DIRT_LOOSE,
                dropsForLooseAggregate(ModBlocks.DIRT_LOOSE, ModBlocks.DIRT_LOOSE,
                        LootConditions.WITH_SHOVEL_FULLY_HARVESTS, ModItems.PILE_DIRT, 6
                )
        );

        addDrop(ModBlocks.STONE_BRICKS_LOOSE,
                dropsForSimpleLooseBlock(ModBlocks.STONE_BRICKS_LOOSE,
                        LootConditions.WITH_PICKAXE_FULLY_HARVESTS, ModItems.STONE_BRICK, 8
                )
        );

        addDrop(ModBlocks.LAVA_FILLED_STONE_BRICKS_LOOSE,
                dropsForSimpleLooseBlock(ModBlocks.STONE_BRICKS_LOOSE,
                        LootConditions.WITH_PICKAXE_FULLY_HARVESTS, ModItems.STONE_BRICK, 8
                )
        );

        addDrop(ModBlocks.DEEPSLATE_BRICKS_LOOSE,
                dropsForSimpleLooseBlock(ModBlocks.DEEPSLATE_BRICKS_LOOSE,
                        LootConditions.WITH_PICKAXE_FULLY_HARVESTS, ModItems.STONE_BRICK_2, 8
                )
        );

        addDrop(ModBlocks.LAVA_FILLED_DEEPSLATE_BRICKS_LOOSE,
                dropsForSimpleLooseBlock(ModBlocks.DEEPSLATE_BRICKS_LOOSE,
                        LootConditions.WITH_PICKAXE_FULLY_HARVESTS, ModItems.STONE_BRICK_2, 8
                )
        );

        addDrop(ModBlocks.BRICKS_LOOSE,
                dropsForSimpleLooseBlock(ModBlocks.BRICKS_LOOSE,
                        LootConditions.WITH_PICKAXE_FULLY_HARVESTS, Items.BRICK, 8
                )
        );

        addDrop(ModBlocks.NETHER_BRICKS_LOOSE,
                dropsForSimpleLooseBlock(ModBlocks.NETHER_BRICKS_LOOSE,
                        LootConditions.WITH_PICKAXE_FULLY_HARVESTS, Items.NETHER_BRICK, 8
                )
        );

        addDrop(ModBlocks.COBBLESTONE_LOOSE);
        addDrop(ModBlocks.LAVA_FILLED_COBBLESTONE_LOOSE);
        addDrop(ModBlocks.COBBLED_DEEPSLATE_LOOSE);
        addDrop(ModBlocks.LAVA_FILLED_COBBLED_DEEPSLATE_LOOSE);
        addDrop(ModBlocks.GRANITE_LOOSE);
        addDrop(ModBlocks.DIORITE_LOOSE);
        addDrop(ModBlocks.ANDESITE_LOOSE);
        addDrop(ModBlocks.CALCITE_LOOSE);
        addDrop(ModBlocks.TUFF_LOOSE);
        addDrop(ModBlocks.BLACKSTONE_LOOSE);
        addDrop(ModBlocks.BASALT_LOOSE);
        addDrop(ModBlocks.END_STONE_LOOSE);

        addDrop(ModBlocks.WHITE_STONE, (Block block) -> drops(block, ModBlocks.WHITE_COBBLESTONE));
        addDrop(ModBlocks.WHITE_COBBLESTONE);

        addDrop(ModBlocks.SLAB_WHITE_STONE, (Block block) -> drops(block, ModBlocks.SLAB_WHITE_COBBLESTONE));
        addDrop(ModBlocks.SLAB_WHITE_COBBLESTONE);

        addDrop(ModBlocks.WHITE_STONE_STAIRS, (Block block) -> drops(block, ModBlocks.WHITE_COBBLESTONE_STAIRS));
        addDrop(ModBlocks.WHITE_COBBLESTONE_STAIRS);

        // Loose Slab blocks
        addDrop(ModBlocks.SLAB_DIRT,
                dropsForAggregateSlab(
                        ModBlocks.SLAB_DIRT,
                        ModItems.PILE_DIRT,
                        3,
                        LootConditions.WITH_SHOVEL_FULLY_HARVESTS
                )
        );

        addDrop(ModBlocks.SLAB_GRAVEL,
                dropsForAggregateSlab(
                        ModBlocks.SLAB_GRAVEL,
                        ModItems.PILE_GRAVEL,
                        3,
                        LootConditions.WITH_SHOVEL_FULLY_HARVESTS
                )
        );

        addDrop(ModBlocks.SLAB_SAND,
                dropsForAggregateSlab(
                        ModBlocks.SLAB_SAND,
                        ModItems.PILE_SAND,
                        3,
                        LootConditions.WITH_SHOVEL_FULLY_HARVESTS
                )
        );

        addDrop(ModBlocks.SLAB_RED_SAND,
                dropsForAggregateSlab(
                        ModBlocks.SLAB_RED_SAND,
                        ModItems.PILE_RED_SAND,
                        3,
                        LootConditions.WITH_SHOVEL_FULLY_HARVESTS
                )
        );

        addDrop(ModBlocks.SLAB_COBBLESTONE_LOOSE, slabDrops(ModBlocks.SLAB_COBBLESTONE_LOOSE));
        addDrop(ModBlocks.SLAB_COBBLED_DEEPSLATE_LOOSE, slabDrops(ModBlocks.SLAB_COBBLED_DEEPSLATE_LOOSE));
        addDrop(ModBlocks.SLAB_GRANITE_LOOSE, slabDrops(ModBlocks.SLAB_GRANITE_LOOSE));
        addDrop(ModBlocks.SLAB_DIORITE_LOOSE, slabDrops(ModBlocks.SLAB_DIORITE_LOOSE));
        addDrop(ModBlocks.SLAB_ANDESITE_LOOSE, slabDrops(ModBlocks.SLAB_ANDESITE_LOOSE));

        addDrop(ModBlocks.SLAB_BRICKS_LOOSE,
                dropsForAggregateSlab(
                        ModBlocks.SLAB_BRICKS_LOOSE,
                        Items.BRICK,
                        4,
                        LootConditions.WITH_PICKAXE_FULLY_HARVESTS
                )
        );

        addDrop(ModBlocks.SLAB_NETHER_BRICKS_LOOSE,
                dropsForAggregateSlab(
                        ModBlocks.SLAB_NETHER_BRICKS_LOOSE,
                        Items.NETHER_BRICK,
                        4,
                        LootConditions.WITH_PICKAXE_FULLY_HARVESTS
                )
        );


        // Loose Stair blocks
        addDrop(ModBlocks.COBBLESTONE_LOOSE_STAIRS,
                dropsForSimpleLooseBlock(
                        ModBlocks.COBBLESTONE_LOOSE_STAIRS,
                        LootConditions.WITH_PICKAXE_FULLY_HARVESTS,
                        ModItems.SMALL_STONE,
                        6
                )
        );

        addDrop(ModBlocks.COBBLED_DEEPSLATE_LOOSE_STAIRS,
                dropsForSimpleLooseBlock(
                        ModBlocks.COBBLED_DEEPSLATE_LOOSE_STAIRS,
                        LootConditions.WITH_PICKAXE_FULLY_HARVESTS,
                        ModItems.SMALL_STONE_2,
                        6
                )
        );

        addDrop(ModBlocks.GRANITE_LOOSE_STAIRS,
                dropsForSimpleLooseBlock(
                        ModBlocks.GRANITE_LOOSE_STAIRS,
                        LootConditions.WITH_PICKAXE_FULLY_HARVESTS,
                        ModItems.SHARD_GRANITE,
                        6
                )
        );

        addDrop(ModBlocks.ANDESITE_LOOSE_STAIRS,
                dropsForSimpleLooseBlock(
                        ModBlocks.ANDESITE_LOOSE_STAIRS,
                        LootConditions.WITH_PICKAXE_FULLY_HARVESTS,
                        ModItems.SHARD_ANDESITE,
                        6
                )
        );

        addDrop(ModBlocks.DIORITE_LOOSE_STAIRS,
                dropsForSimpleLooseBlock(
                        ModBlocks.DIORITE_LOOSE_STAIRS,
                        LootConditions.WITH_PICKAXE_FULLY_HARVESTS,
                        ModItems.SHARD_DIORITE,
                        6
                )
        );

        addDrop(ModBlocks.BRICKS_LOOSE_STAIRS,
                dropsForSimpleLooseBlock(
                        ModBlocks.BRICKS_LOOSE_STAIRS,
                        LootConditions.WITH_PICKAXE_FULLY_HARVESTS,
                        Items.BRICK,
                        6
                )
        );

        addDrop(ModBlocks.NETHER_BRICKS_LOOSE_STAIRS,
                dropsForSimpleLooseBlock(
                        ModBlocks.NETHER_BRICKS_LOOSE_STAIRS,
                        LootConditions.WITH_PICKAXE_FULLY_HARVESTS,
                        Items.NETHER_BRICK,
                        6
                )
        );

        // Non-loose full blocks
        addDrop(ModBlocks.DIRT_PACKED,
                dropsForSimpleLooseBlock(
                        ModBlocks.DIRT_PACKED,
                        LootConditions.WITH_SHOVEL_FULLY_HARVESTS,
                        ModItems.PILE_DIRT,
                        12
                )
        );

        // Non-loose slab blocks
        addDrop(ModBlocks.SLAB_DIRT_PACKED,
                dropsForSimpleLooseBlock(
                        ModBlocks.SLAB_DIRT_PACKED,
                        LootConditions.WITH_SHOVEL_FULLY_HARVESTS,
                        ModItems.PILE_DIRT,
                        6
                )
        );

        // Exclusive placed as items blocks
        addDrop(ModBlocks.RAW_IRON_PLACED,  Items.RAW_IRON);
        addDrop(ModBlocks.RAW_COPPER_PLACED, Items.RAW_COPPER);
        addDrop(ModBlocks.RAW_GOLD_PLACED, Items.RAW_GOLD);
        addDrop(ModBlocks.NETHER_BRICK_UNFIRED, ModItems.NETHER_SLUDGE);

        // TODO: Add loot tables for Broken stone blocks, converting stone blocks, slab blocks and the stair blocks.
    }

}