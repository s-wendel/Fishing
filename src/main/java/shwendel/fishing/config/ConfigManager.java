package shwendel.fishing.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigManager {

    private static Map<ConfigType, Config> configs = new HashMap<>();

    static {
        for(ConfigType configType : ConfigType.values()) {
            configs.put(configType, new Config(configType));
        }
    }

    public static Map<ConfigType, Config> getConfigs() {
        return configs;
    }

}