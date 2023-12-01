package shwendel.fishing.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import shwendel.fishing.Fishing;

import java.io.File;
import java.io.IOException;

public class Config {

    private File file;
    private FileConfiguration fileConfiguration;
    private ConfigType configType;

    public Config(ConfigType configType) {
        this.configType = configType;

        final String PATH = configType.getPath() + configType.getFileName();

        this.file = new File(Fishing.getInstance().getDataFolder().getPath() + File.separator + PATH);

        if(!file.exists()) {
            Fishing.getInstance().saveResource(PATH, false);
        }

        this.fileConfiguration = new YamlConfiguration();

        try {

            fileConfiguration.load(file);

        } catch (IOException | InvalidConfigurationException exception) {

            exception.printStackTrace();

        }
    }

    public File getFile() {
        return file;
    }

    public FileConfiguration getConfig() {
        return fileConfiguration;
    }

    public ConfigType getConfigType() {
        return configType;
    }

    public void saveConfig() {
        try {
            fileConfiguration.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void reloadConfig() {
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

}