package shwendel.fishing.config;

import java.io.File;

public enum ConfigType {

    // File.separator used the wrong separator
    CONFIG("config.yml", ""),
    ARMOR("armor.yml", "items/"),
    FISH("fish.yml", "items/"),
    MATERIAL("material.yml", "items/"),
    ROD("rod.yml", "items/"),
    ;

    private String fileName;
    private String path;

    ConfigType(String fileName, String path) {
        this.fileName = fileName;
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public String getPath()  {
        return path;
    }

}