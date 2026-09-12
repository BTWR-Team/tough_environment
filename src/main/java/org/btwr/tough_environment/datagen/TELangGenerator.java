package org.btwr.tough_environment.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import org.btwr.tough_environment.ToughEnvironmentMod;
import org.btwr.tough_environment.block.ModBlocks;
import org.btwr.tough_environment.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class TELangGenerator extends FabricLanguageProvider {

    public TELangGenerator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder tb) {
        // ItemGroup
        this.addItemGroup("group_te","Tough Environment!", tb);
        this.generateConfigTranslations(tb);
        this.generateBlockTranslations(tb);
        this.generateItemTranslations(tb);

        tb.add("subtitles.tough_environment.stone_cracking", "Stone cracks");
        tb.add("subtitles.tough_environment.apply_mortar", "Mortar applied");
        tb.add("subtitles.tough_environment.unfired_brick_destroyed", "Unfired brick destroyed");
    }

    private void generateConfigTranslations(TranslationBuilder tb) {
        this.addConfigMenuDefaults(tb);
        this.addConfigMenuTitle("Tough Environment Configuration Menu", tb);
        this.addConfigCategory("general", "General", tb);
        //this.addConfig("hcPlayerMiningSpeed", "Hardcore Mining Speed", tb);
        //this.addConfig("stratificationToughness", "Stratification Toughness", tb);
        //this.addConfig("strataBasedBlockBreakingRestrictions", "Strata Breaking Restrictions", tb);
        //this.addConfigTooltip("hcPlayerMiningSpeed", "Makes most blocks take longer to break overall", tb);
        //this.addConfigTooltip("stratificationToughness", "Stratification toughness disallows breaking of tougher stones with lower-tier pickaxes", tb);
        //this.addConfigTooltip("strataBasedBlockBreakingRestrictions", "Highly discourages breaking of stone type 'strata' blocks which you don't have the correct tool for.\nThis also includes other stone-like blocks like Obsidian, etc..", tb);
    }

    private void addConfigMenuDefaults(TranslationBuilder tb) {
        this.addSimpleText("clientSettingsText", "Client Settings:", tb);
        this.addSimpleText("emptyClientConfigText", "§eNote:§r There are currently no client config settings.", tb);
        this.addSimpleText("serverSettingsText", "Server Settings:", tb);
        this.addSimpleText("serverSettingsNoAccessText", "§eNote:§r Server settings are not accessible in menus." +
                "\nThey can only be changed by editing the config file manually and require a world reload to take effect.", tb
        );
    }

    private void generateBlockTranslations(TranslationBuilder tb) {
        // Non-loose blocks
        tb.add(ModBlocks.SLAB_SAND, "Sand Slab");
        tb.add(ModBlocks.SLAB_RED_SAND, "Red Sand Slab");
        tb.add(ModBlocks.SLAB_DIRT, "Loose Dirt Slab");
        tb.add(ModBlocks.SLAB_GRAVEL, "Gravel Slab");
        tb.add(ModBlocks.SLAB_COBBLESTONE_LOOSE, "Loose Cobblestone Slab");
        tb.add(ModBlocks.SLAB_COBBLED_DEEPSLATE_LOOSE, "Loose Cobbled Deepslate Slab");
        tb.add(ModBlocks.SLAB_GRANITE_LOOSE, "Loose Granite Slab");
        tb.add(ModBlocks.SLAB_ANDESITE_LOOSE, "Loose Andesite Slab");
        tb.add(ModBlocks.SLAB_DIORITE_LOOSE, "Loose Diorite Slab");
        tb.add(ModBlocks.SLAB_BRICKS_LOOSE, "Loose Bricks Slab");
        tb.add(ModBlocks.SLAB_STONE_BRICKS_LOOSE, "Loose Stone Bricks Slab");
        tb.add(ModBlocks.SLAB_DEEPSLATE_BRICKS_LOOSE, "Loose Deepslate Bricks Slab");
        tb.add(ModBlocks.SLAB_NETHER_BRICKS_LOOSE, "Loose Nether Bricks Slab");

        tb.add(ModBlocks.SLAB_WHITE_STONE, "White Stone Slab");
        tb.add(ModBlocks.SLAB_WHITE_COBBLESTONE, "White Cobblestone Slab");
        tb.add(ModBlocks.SLAB_DIRT_PACKED, "Packed Earth Slab");

        tb.add(ModBlocks.CLAY_ORE, "Clay Ore");
        tb.add(ModBlocks.WHITE_STONE, "White Stone");
        tb.add(ModBlocks.WHITE_COBBLESTONE, "White Cobblestone");
        tb.add(ModBlocks.DIRT_PACKED, "Packed Earth");
        tb.add(ModBlocks.LAVA_PILLOW, "Lava Pillow");

        // Loose blocks
        tb.add(ModBlocks.DIRT_LOOSE, "Loose Dirt");
        tb.add(ModBlocks.COBBLESTONE_LOOSE, "Loose Cobblestone");
        tb.add(ModBlocks.LAVA_FILLED_COBBLESTONE_LOOSE, "Lava Filled Loose Cobblestone");
        tb.add(ModBlocks.COBBLED_DEEPSLATE_LOOSE, "Loose Cobbled Deepslate");
        tb.add(ModBlocks.LAVA_FILLED_COBBLED_DEEPSLATE_LOOSE, "Lava Filled Loose Cobbled Deepslate");
        tb.add(ModBlocks.GRANITE_LOOSE, "Loose Granite");
        tb.add(ModBlocks.ANDESITE_LOOSE, "Loose Andesite");
        tb.add(ModBlocks.DIORITE_LOOSE, "Loose Diorite");
        tb.add(ModBlocks.CALCITE_LOOSE, "Loose Calcite");
        tb.add(ModBlocks.TUFF_LOOSE, "Loose Tuff");
        tb.add(ModBlocks.BLACKSTONE_LOOSE, "Loose Blackstone");
        tb.add(ModBlocks.BASALT_LOOSE, "Loose Basalt");
        tb.add(ModBlocks.END_STONE_LOOSE, "Loose End Stone");
        tb.add(ModBlocks.BRICKS_LOOSE, "Loose Bricks");
        tb.add(ModBlocks.STONE_BRICKS_LOOSE, "Loose Stone Bricks");
        tb.add(ModBlocks.LAVA_FILLED_STONE_BRICKS_LOOSE, "Lava Filled Loose Stone Bricks");
        tb.add(ModBlocks.DEEPSLATE_BRICKS_LOOSE, "Loose Deepslate Bricks");
        tb.add(ModBlocks.LAVA_FILLED_DEEPSLATE_BRICKS_LOOSE, "Lava Filled Loose Deepslate Bricks");
        tb.add(ModBlocks.NETHER_BRICKS_LOOSE, "Loose Nether Bricks");

        // Stairs blocks
        tb.add(ModBlocks.COBBLESTONE_LOOSE_STAIRS, "Loose Cobblestone Stairs");
        tb.add(ModBlocks.COBBLED_DEEPSLATE_LOOSE_STAIRS, "Loose Cobbled Deepslate Stairs");
        tb.add(ModBlocks.ANDESITE_LOOSE_STAIRS, "Loose Andesite Stairs");
        tb.add(ModBlocks.GRANITE_LOOSE_STAIRS, "Loose Granite Stairs");
        tb.add(ModBlocks.DIORITE_LOOSE_STAIRS, "Loose Diorite Stairs");
        tb.add(ModBlocks.BRICKS_LOOSE_STAIRS, "Loose Bricks Stairs");
        tb.add(ModBlocks.STONE_BRICKS_LOOSE_STAIRS, "Loose Stone Bricks Stairs");
        tb.add(ModBlocks.DEEPSLATE_BRICKS_LOOSE_STAIRS, "Loose Deepslate Bricks Stairs");
        tb.add(ModBlocks.NETHER_BRICKS_LOOSE_STAIRS, "Loose Nether Bricks Stairs");

        tb.add(ModBlocks.WHITE_STONE_STAIRS, "White Stone Stairs");
        tb.add(ModBlocks.WHITE_COBBLESTONE_STAIRS, "White Cobblestone Stairs");

        tb.add(ModBlocks.RAW_COPPER_PLACED, "Raw Copper");
        tb.add(ModBlocks.RAW_IRON_PLACED, "Raw Iron");
        tb.add(ModBlocks.RAW_GOLD_PLACED, "Raw Gold");

        // Non-item blocks (blocks that aren't usually accessible by the player, but still need names)
        tb.add(ModBlocks.STONE_CONVERTING, "Cracked Stone");
        tb.add(ModBlocks.DEEPSLATE_CONVERTING, "Cracked Deepslate");
        tb.add(ModBlocks.ANDESITE_CONVERTING, "Cracked Andesite");
        tb.add(ModBlocks.GRANITE_CONVERTING, "Cracked Granite");
        tb.add(ModBlocks.DIORITE_CONVERTING, "Cracked Diorite");

        tb.add(ModBlocks.STONE_BROKEN, "Depleted Stone");
        tb.add(ModBlocks.DEEPSLATE_BROKEN, "Depleted Deepslate");
        tb.add(ModBlocks.ANDESITE_BROKEN, "Depleted Andesite");
        tb.add(ModBlocks.GRANITE_BROKEN, "Depleted Granite");
        tb.add(ModBlocks.DIORITE_BROKEN, "Depleted Diorite");
    }

    private void generateItemTranslations(TranslationBuilder tb) {
        tb.add(ModItems.PILE_DIRT, "Pile of Dirt");
        tb.add(ModItems.PILE_GRAVEL, "Pile of Gravel");
        tb.add(ModItems.PILE_SAND, "Pile of Sand");
        tb.add(ModItems.PILE_RED_SAND, "Pile of Red Sand");
        tb.add(ModItems.PILE_CLAY, "Pile of Clay");

        tb.add(ModItems.SMALL_STONE, "Small Stone");
        tb.add(ModItems.SMALL_STONE_1, "Mantle Small Stone");
        tb.add(ModItems.SMALL_STONE_2, "Deepslate Small Stone");
        tb.add(ModItems.SHARD_GRANITE, "Granite Shard");
        tb.add(ModItems.SHARD_ANDESITE, "Andesite Shard");
        tb.add(ModItems.SHARD_DIORITE, "Diorite Shard");

        tb.add(ModItems.DUST_COAL, "Pile of Coal Dust");
        tb.add(ModItems.DUST_IRON, "Pile of Iron Dust");
        tb.add(ModItems.DUST_GOLD, "Pile of Gold Dust");
        tb.add(ModItems.DUST_COPPER, "Pile of Copper Dust");

        tb.add(ModItems.CHISEL_WOOD, "Wooden Chisel");
        tb.add(ModItems.CHISEL_STONE, "Stone Chisel");
        tb.add(ModItems.CHISEL_IRON, "Iron Chisel");
        tb.add(ModItems.CHISEL_DIAMOND, "Diamond Chisel");

        tb.add(ModItems.COPPER_NUGGET, "Copper Nugget");
        tb.add(ModItems.NETHERITE_NUGGET, "Netherite Nugget");

        tb.add(ModItems.STONE_BRICK, "Stone Brick");
        tb.add(ModItems.STONE_BRICK_1, "Mantlestone Brick");
        tb.add(ModItems.STONE_BRICK_2, "Deepslate Brick");

        tb.add(ModItems.NETHER_BRICK_UNFIRED, "Wet Nether Brick");

        tb.add(ModItems.NETHER_SLUDGE, "Nether Sludge");
    }

    private void addItemGroup(String entryPath, String translation, TranslationBuilder tb) {
        tb.add("itemgroup." + entryPath, translation);
    }

    private void addConfigMenuTitle(String translation, TranslationBuilder tb) {
        tb.add("title." + ToughEnvironmentMod.MOD_ID + ".config", translation);
    }

    private void addConfigCategory(String path, String translation, TranslationBuilder tb) {
        tb.add(configBasePath() + "category." + path, translation);
    }

    private void addSimpleText(String path, String translation, TranslationBuilder tb) {
        tb.add(configBasePath() + "text." + path, translation);
    }

    private void addConfig(String path, String translation, TranslationBuilder tb) {
        tb.add(configBasePath() + path, translation);
    }

    private void addConfigTooltip(String path, String translation, TranslationBuilder tb) {
        tb.add(configBasePath() + "tooltip." + path, translation);
    }

    private String configBasePath() {
        return "config." + ToughEnvironmentMod.MOD_ID + ".";
    }

}