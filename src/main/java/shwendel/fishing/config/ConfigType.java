package shwendel.fishing.config;

import java.io.File;

public enum ConfigType {

    CONFIG("config.yml", ""),
    ARMOR("armor.yml", File.separator + "items" + File.separator),
    FISH("fish.yml", File.separator + "items" + File.separator),
    MATERIAL("material.yml", File.separator + "items" + File.separator),
    ROD("rod.yml", File.separator + "items" + File.separator),
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