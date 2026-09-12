## v0.21
+ Added a new config option to toggle whether lava pillows should be created when water touches flowing lava
+ Added custom sound events for sounds like stone cracking when broken, applying mortar and unfired brick being destroyed
+ Fixed hardcore block breaking speed de-syncing in SMP (fizzexual)
+ Fixed some block loot tables to drop the correct items and replaced with correct tools/tags where necessary (fizzexual)
+ Changed version scheme. The mod will now use the (x.xx) scheme again until I prepare it for a bigger (v1.0) release soon. The previous version (v0.2) caused some issues with older version names which are matching that pattern
+ Updated the mod to Fabric API 0.116.15, Fabric Loader 0.19.5 & BTWR: Shared Library 0.8.5

## v0.2
+ The mod is officially in Beta now!
+ Added new blocks:
  - Lava Filled Loose Cobblestone
  - Lava Filled Loose Deepslate
  - Lava Filled Loose Stone Bricks
  - Lava Filled Loose Deepslate Bricks
  - Lava Pillow
+ Added missing text translations for the names of the placed ore block (copper, iron, gold)
+ Changed how stone is created from lava. Water must now float on top of the aforementioned lava-filled blocks to create the corresponding stone type (stone and deepslate)
+ Changed lava to no longer generate cobblestone when water flows on top of it and to instead create a Lava Pillow block.
+ Changed the mod versioning scheme to [semver](https://semver.org/).
+ Fixed some pathfinding issues for loose slab and stairs blocks
+ Updated the mod to Fabric API 0.116.11, Fabric Loader 0.19.2 & BTWR: Shared Library 0.8.4

## v0.13.7
+ Fixed some unintended behavior with chisel items mining speeds
+ Updated the mod to Fabric API 0.116.9, Fabric Loader 0.18.4 & BTWR: Shared Library 0.8.1

## v0.13.6
+ Changed primitive chisels to not have damage applied when used in crafting
+ Updated the mod to BTWR: Shared Library 0.8

## v0.13.5
+ Changed directional drops to be handled with the DirectionalDropsConditions class from BTWR: SL 0.7
+ Updated the mod to BTWR: Shared Library 0.7

## v0.13.4
+ Fixed another bug with some blocks having hardcore speed applied in a wrong way. With this change it reverts back some of the intended changes I wanted to make since the 0.13 update, but at least there shouldn't be problems instead

## v0.13.3
+ Fixed a bug with replaceable blocks like grass, tall grass, etc having hardcore speed since last update's changes

## v0.13.2
+ Added missing recipes for cooking and blasting all loose variant blocks (stone, deepslate, diorite, bricks, etc.)
+ Added a check for tools which are not suitable for a certain block to apply slowed breaking speed. This is configurable with the Stratification Toughness setting
+ Reworked all loot tables for ores to better match retail BTW CE
+ Fixed a bug with chisels not having applied hardcore breaking speed
+ Changed the default breaking speed of primitive chisels to match those of vanilla wood and stone materials in the mod (1.01f)

## v0.13.1
+ Changed all configuration options in the mod to use the custom config library added by BTWR: Shared Library. This fixes the bug from last version that crashed the game without any warnings of the missing library that created the configurations
+ Removed Supermartijn642's config lib as the one creating configuration setting as it requires itself as a dependency to work properly
+ Updated the mod to BTWR: Shared Library 0.6.5

## v0.13
+ Added default recipes for items that would be crafted with nuggets instead of replacing the vanilla recipes.
+ Added authors(ivangeevo) to the mod files, so they should display properly in places where they should be displayed
+ Fixed a bug where stone bricks from stone crafting with chisel would consume the chisels
+ Fixed the Loose Diorite's item name displaying wrong as being "Granite"
+ Changed/Generally improved Mod Menu configuration option screens to better show how configuration options work
+ Changed all configuration options setting to be handled with Supermartijn642's Config Lib internally instead of Cloth Config API, which is used only for client side config options.
+ Changed the "Block Breaking Restriction" configuration option to be called "Strata Based Block Breaking Restrictions" to better reflect what it does
+ Reworked breaking speed modifications on how they are calculated and how configuration options interact between each other. They are now handled with Fabric Entity Atrributes API instead of mixins. Generally, the feeling of the game should stay the same, but it should be closer to how it feels in BTW CE
+ Refactored pretty much the whole code; mainly for readability and cleaning up, but also so it's more in order with other mods from the BTWR project
+ Removed the "TURNED_TO_FALLING_BLOCKS" block tag and associated functionality to make any block a falling block, because it was a bad modification and caused other problems. It will be pushed back until a better solution is found
+ Updated the mod to Fabric Loader 0.17.3 BTWR: Shared Library 0.6.4

## v0.12.1
+ Updated mod recipes to use the new ExtendedShapelessRecipe from BTWR: Shared Library 0.60-61
+ Fixed a bug with loose stone and deepslate bricks not being mortarable
+ Updated the mod to Fabric API 0.116.7 & BTWR: Shared Library 0.61

## v0.12
+ Added compatibility for BWT's aggregate slab blocks like(dirt, grass, etc.) to also loosen up compatible neighbouring dirt.
+ Improved the dirt loosening mechanic to work exactly like it does in BTW.
+ Refactored a bunch of code to make block mortaring logic work much better. It's now stored in the datapack of the mod, and should generally work much better.
+ Fixed stone block flickering when breaking to other break levels to not happen anymore.
+ Fixed loot tables for loose stone slabs to properly drop for both double slab breaks and explosion breaks.
+ Fixed loot tables for diamond and emerald ore blocks to have fortune applied again.
+ Fixed loot tables for stone and stone alternative type blocks to properly drop their loot for modern and advanced pickaxes.
+ Changed block breaking modifications to use Fabric events instead of directly mixing into the Block class.
+ Changed depleted(broken) stone blocks to not have break level anymore. This change will remain until I figure out how to properly implement the blockstate changing logic for converting blocks into their classes instead of it being called through the Fabric Block Break events.
+ Updated the mod to Fabric API 0.116.6, Fabric Loader 0.17.2 & BTWR: Shared Library 0.58

## v0.11.1
+ Added a new LootConditionType "DESTROYED_BY_EXPLOSION" that explicitly checks whether a block was destroyed by an explosion.
+ Fixed loot tables for all stone block types to properly drop loot for primitive chisels since the bug from last update's loot table code refactoring.
+ Updated the mod to BTWR: Shared Library 0.56

## v0.11
+ Changed(and hopefully fixed) some clay blocks in the world generation that weren't getting properly replaced with Tough Environment's "Dirt Clay" block.
+ Changed the shield recipe to require 4 nuggets and only one plank, so it's more easily accessible.
+ Reworked all loot tables for stone type blocks to flow better and be more readable, but also fixed them to work with silk touch tools.
+ Fixed cobblestone and cobbled deepslate stairs to properly drop their loose block counterparts when broken.
+ Fixed (removed)Packed Earth, Dirt and Packed Earth slabs from the DIRT Block tags which caused some problems with planting sugar cane from Vegehenna
+ Fixed Loose Dirt to be a valid tillable block when right-clicked with a hoe 
+ Fixed/removed old code that turned loose dirt to farmland when broken with a hoe and removed it's associated efficient blocks
+ Fixed clock to actually require quartz as the original intention was.
+ Fixed names & item models for inaccessible blocks like depleted & converting stone types to properly display, mainly for proper name to show up when looked with mods like WAILA.
+ Removed the logic that added extra exhaustion on breaking/placing blocks and axes not taking damage on replaceable blocks and moved it to be a mod pack change only.
+ Updated the mod to Fabric API 0.116.4, Fabric Loader 0.16.14 & BTWR: Shared Library 0.55

## v0.10.1 Alpha
+ Fixed a bug where aggregate blocks like dirt, grass, sand, etc weren't dropping when broken with a hoe tool (leftover functionality from previous versions)
+ Fixed all aggregate blocks to drop the proper amount of pile items for each block (applied explosion decay to the loot tables)

## v0.10 Alpha
+ Fixed a bug that made dirt & grass blocks not update neighbouring blocks to make them loose dirt on the client side
+ Removed the code that made hoes not work via right click and moved that functionality to BTWR: Core 
+ Updated the mod to Fabric API 0.116.0, Fabric Loader 0.16.14 & BTWR: Shared Library 0.53

## v0.9 Alpha
### ! World breaking update - This update will replace some blocks in your world/ make some items disappear from your inventory. 
+ Added a new block for the "Clay Ore" and made it replace normal Clay blocks in world generation. This allows for regular clay blocks to be crafted as normal and fixes incompatibility with mods that might use the clay block in whatever way.
+ Improved the mortaring logic for blocks to be more responsive and not fixed a bug with it bugging out when having a shield equipped when trying to mortar blocks
+ Fixed dirt path to drop dirt piles when broken with a lesser shovel/hand
+ Removed the "Block of Clay" block added by the mod in previous updates as it's no longer needed.
+ Reverted the clay block recipe to be made out of 4 clay ball instead of 9 (as originally planned - this will be a specific change in the BTWR modpack instead)
+ Updated the mod to Fabric API 0.115.3 & BTWR: Shared Library 0.49

## v0.8 Alpha
+ Added compatibility with "Better With Time" Battle Axe item to not consume hunger when breaking grass blocks
+ Fixed a bug where loose dirt was acting like a block that can be mortared.
+ Fixed a bug where all ore blocks broken with any enchanted pickaxe would drop as if they were mined by Silk Touch
+ Fixed nether brick cooking recipes being reverted
+ Changed/fixed the COBBLESTONE_CRAFTING_MATERIALS tag from adding Cobblestone as material and moved it to the BTWR-SL mod.
+ Added missing block tags to newly added nether brick blocks which made them take too long to break.
+ Removed Fortune enchantment working on Diamond & Emerald ores. It will only work on metalic and non-gemstone ores like Redstone/Lapis
+ Updated the mod to Fabric API 0.115.0, Fabric Loader 0.16.10 & BTWR Shared Library 0.47

## v0.7.3 Alpha
+ Added Packed Earth and Packed Earth Slab blocks!

## v0.7.2 Alpha
+ Fixed a bug where netherrack was still a falling block, but lacked proper block updates and that's why I didn't notice it earlier.

## v0.7.1 Alpha
+ Fixed a critical bug and removed the ability to make blocks falling per dimension & removed falling netherrack.
This will be pushed back for a later update.

## v0.7 Alpha
+ !!!Block/Item breaking update -> Rewrote the names of some stone/stone brick blocks items which will cause them to 
disappear from the world/inventories. Please back up your worlds if you don't want to lose them. With all of that being
said, keep in mind the project is still in alpha. The mod will likely get a solid rewrite once
both before beta & release, then everything should remain relatively untouched to not mess up already existing worlds of players.

+ Added recipe for making stone brick items from crafting smooth stone blocks together with a modern/advanced chisel in a crafting bench.
+ Changed "turned falling blocks" so that now the dimensions they can fall in has to be defined
with the "CAN_FALL_IN_X" (x for dimension) when adding blocks in the "TURNED_TO_FALLING_BLOCKS" block tag.
+ Netherrack is now a falling block in the overworld by default.
+ Added White stone & cobblestone slabs & stairs.
+ Added Nether bricks, stairs and slab blocks.
+ Added Unfired Nether Brick item and it's placeable block counterpart.
+ Added "very compact" loose stair recipes for all stone brick type blocks. (3 items in L shape to singular stair recipe)
+ Improved the conditions for breaking blocks that have their speed modified (still, we probably need a better modification for those...)
+ Improved logic for playing sound when crafting chisel tool items.
+ Improved code for mortaring blocks. It should work slightly better now.
+ Fixed the breaking block sound group for deepslate (stone) converting block
+ Fixed recipes for stone brick blocks to give the proper amount of bricks on conversion recipes (block-item recipes)

+ Fixed bricks slab vanilla loot table to drop its loose counterpart
+ Fixed some missing recipes & loot tables for the stone/deepslate stone brick blocks.
+ Changed the mod block tag "Loose Stone Blocks" to "Loose Full Blocks"
+ Removed (brought back) the ability to break fire blocks by hand
+ Updated the mod to Fabric API 0.114.0 & BTWR-SL 0.40

## v0.6 Alpha
+ Added Nether Sludge item. It's sludge time!
+ Added Loose Stone Bricks and Loose Cobbled Deepslate Bricks blocks & all of their variants.
+ Added some missing stairs and loose slab blocks variants for some blocks.
+ Added some extra block tags for loose blocks
+ Made Fire block inextinguishable by hand
+ Made hoe items have different breaking speeds when HC breaking speed is enabled
+ Changed (rebalanced) the strength and blast resistance values for some loose stone blocks slightly
+ Fixed Loose Bricks block & slab texture to be more "loosy". lol

## v0.5.1 Alpha
+ Added a missing recipe for converting loose bricks block to 8 bricks
+ Fixed deepslate ores loot tables to drop ore with any pickaxe
+ Fixed deepslate ores turning to regular converting stone on partial break
+ Fixed the name of "Loose Cobblestone" block to display properly
+ Fixed wrong "Loose Dirt Slab" & "Loose Calcite" names 
+ Fixed loose aggregate slabs to turn to their full counterparts instead of becoming "double slab"
+ Fixed a bug that caused andesite/granite/diorite blocks to drop stone brick on a pickaxe break (should drop only with chisel)
+ Fixed a bug where diamond chisel broke stone type blocks one by one stage instead of incrementing by 2 break levels.

## v0.5 Alpha
+ Added BTWR Shared Library as dependency + moved the item to block placement logic there
+ Added missing blocks to their appropriate tags
+ Added Clay Block (made out of 8 clay balls)
+ Added a block tag TURNED_FALLING_BLOCKS that makes blocks in it falling ones
+ Added Copper Nugget & Netherite Nugget items
+ Added Stone Brick item -> (moved from BTWR: Core)
+ Changed Primitive tools like stone axe, chisel, shovel, etc. to be slightly faster when mining blocks
+ Changed the exhaustion caused by breaking blocks to half of what it was
+ Changed the mod logo with a more high quality one
+ Improved the logic for hoe tools breaking blocks
+ Fixed loose slab blocks from being non-opaque (letting light go through them);
+ Fixed multiple block loot tables to drop properly
+ Fixed blocks to instantly change state without redrawing of the block to prevent phasing through converting blocks
+ Fixed cobblestone and cobbled deepslate slabs from being unable to be mortared
+ Fixed a bug that turned grass instantly to farmland by hoe breaking (it should go to dirt/loose dirt first)
+ Fixed a bug in breaking clay ore blocks dropped the wrong item
+ Fixed bug where breaking grass and dirt with hoe dropped dirt piles
+ Temporary fix for a bug that caused a slab placed above another one to turn the bottom block to double slab (a duplication bug)
+ Rounded the damage of all chisel items to be 1
+ Block Tag changes; added new mod tag "SIMPLE_DIRT_BLOCKS"
+ Removed the Andesite, Granite & Diorite stairs blocks added by the mod as they were redundant
+ Removed the collision of raw ore placed items, so entities like players and mobs won't collide with them
+ Updated the mod to require Fabric API 0.110.0 & Fabric Loader 0.16.9


## v0.4.1 Alpha
+ Added BTWR: Core v0.26 as a dependency as it was removed by mistake during one of the last updates.
+ Added another recipe for Furnace made from four loose cobblestone slabs.
  This makes getting furnace in the very early game much 
  more accessible because the previous recipe required eight whole loose blocks.
+ Fixed iron chisel being too slow when breaking stump blocks from Sturdy Trees.
+ Updated the mod to Fabric Loader 0.16.7 & Fabric API 0.107.0


## v0.4 Alpha

+ Added 2 new mod menu configuration options:
 1. Block Breaking Restrictions - Makes blocks unbreakable if the player is not using the correct tool.

 2. Stratification Toughness - Makes some blocks that are considered higher strata than stone level blocks require higher level pickaxes to mine effectively.
+ Improved code for breaking dirt into loose dirt and also added other dirt-like blocks to break in the same way.
+ General improvements of loot tables for blocks to drop the proper items and remove/fix wrong drops.
+ Brought back the ability to combine tools to repair them. (will only be modified in the btwr modpack)
+ Fixed placement code for ore chunk blocks on the ground to work as intended.
+ Fixed a bug that caused falling(loose) block stairs to turn to their full block counterpart when falling.
+ Fixed the hardcore mining speed configuration setting in Mod Menu to work properly.
+ Fixed mining speed of primitive chisels for higher tier ores to be slower.
+ Fixed mod icon in the creative mod menu to display properly instead of the "missing" texture.
+ Fixed some chisels making a ding sound on the wrong block breaks.
+ Changed diamond chisel's crafting recipe to use a diamond instead of diamond ingot by default.
+ Removed the new texture for copper ore chunk and reverted it to the vanilla original one for raw copper.
+ Other minor fixes/improvements which should've worked as intended.
+ Updated the mod to Minecraft 1.21.1, Fabric Loader 0.16.5 & Fabric API 0.104.0

## v0.3 Alpha
+ The mod now requires BTWR:Core v0.25. It didn't require it before, which caused some blocks to not drop correctly when mined.
+ Added Pile of Gold Dust and Pile of Copper Dust items.
+ Added Pile of Coal Dust and Wooden Chisel as viable fuel items.
+ Changed Anvil's recipe to only require ingots.
+ Changed Coarse Dirt, Farmland & Mycelium to drop loose dirt and piles when they should.
+ Changed names of Sharp Stick and Sharp Stone to be called "Chisels". Hi Zhil ;)
+ Changed (improved) code for falling of loose blocks.
+ Changed the mod to require Fabric API 0.92.2
+ Changes to conventional tags.
+ Fixed most loot tables for ore blocks to drop the correct items.
+ Fixed code to work as intended on the server side.

## v0.2 Alpha
+ Added missing "brick to loose brick" slab recipe.
+ Added dirt piles drop when clay blocks get broken.
+ Added wooden chisel to be usable as a spit for campfires from the Self-Sustainable mod.
+ Added missing loot table for Podzol breaking into loose dirt/dirt piles.
+ Added missing recipe for loose bricks block from loose brick slabs.
+ Made Raw ore items(chunks) to be placeable in the world as blocks.

+ Reworked breaking speeds logic and fixed a bug that made haste, efficiency and mining fatigue not apply to items.
+ Changed Stone and Iron pickaxes to no longer break strata 3 stones (deepslate & similar).
+ Changed Raw Iron, Raw Gold & Raw Copper item's display names to "Chunks of ore".
+ Changed Compass, Clock & Flint & Steel and Bucket recipes to use nuggets instead of ingots.

+ Fixed a bug with wrong recipe for loose bricks from item.
+ Fixed missing loose cobbled Deepslate drop from breaking Deepslate.

+ Temporary disabled config for hardcore speed until future update.


## v0.1.1 Alpha

+ Changed version numbering from 1.0 to 0.1
+ Fixed a bug where dirt wasn't dropping dirt piles when broken with hand.
+ Increased mining speed for pickaxes on depleted(broken stone) blocks.
+ Updated mod to Fabric Loader 0.5.11

# 0.1 Alpha

+ Initial release