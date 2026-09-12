package org.btwr.tough_environment.config;

import org.btwr.shared_library.api.config.ConfigBuilder;
import org.btwr.shared_library.api.config.ConfigGroup;
import org.btwr.shared_library.api.config.ConfigSetting;
import org.btwr.shared_library.api.config.TomlConfigManager;
import org.btwr.tough_environment.ToughEnvironmentMod;

public class TEModConfig {

    /** Replace with your MOD_ID for easy adaptation **/
    private static final String MOD_ID = ToughEnvironmentMod.MOD_ID;

    public static final ConfigGroup CONFIG;

    /** Call this method in your mod initializer so the class can initialize **/
    public static void register() {}

    public static final ConfigSetting<Boolean> hcPlayerMiningSpeed =
            ConfigBuilder.booleanSetting("hcPlayerMiningSpeed")
                    .defaultValue(true)
                    .comment("Makes most blocks take longer to break overall")
                    .build();

    public static final ConfigSetting<Boolean> stratificationToughness =
            ConfigBuilder.booleanSetting("stratificationToughness")
                    .defaultValue(true)
                    .comment("Stratification toughness disallows breaking of tougher stones/ores with lower-tier pickaxes")
                    .build();

    public static final ConfigSetting<Boolean> strataBasedBlockBreakingRestrictions =
            ConfigBuilder.booleanSetting("strataBasedBlockBreakingRestrictions")
                    .defaultValue(true)
                    .comment("Highly discourages breaking of stone type 'strata' blocks which you don't have the correct tool for.\nThis also includes other stone-like blocks like Obsidian, etc..")
                    .build();

    public static final ConfigSetting<Boolean> lavaPillowsEnabled =
            ConfigBuilder.booleanSetting("lavaPillowsEnabled")
                    .defaultValue(true)
                    .comment("Toggles water creating lava pillows instead of cobblestone when touching flowing lava")
                    .build();

    static {
        CONFIG = new ConfigGroup(String.format("%s/%s_common.toml", MOD_ID, MOD_ID));

        CONFIG.add(hcPlayerMiningSpeed);
        CONFIG.add(stratificationToughness);
        CONFIG.add(strataBasedBlockBreakingRestrictions);
        CONFIG.add(lavaPillowsEnabled);

        TomlConfigManager.registerGroup(CONFIG); // auto init/load/save
    }

}