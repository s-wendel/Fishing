package shwendel.fishing.islands;

import org.bukkit.ChatColor;

public enum Region {

    CORAL_COAST("☂", "Coral Coast", ChatColor.LIGHT_PURPLE, "There's coral maybe"),
    ;

    private String icon;
    private String name;
    private ChatColor color;
    private String[] description;

    Region(String icon, String name, ChatColor color, String... description) {
        this.icon = icon;
        this.name = name;
        this.color = color;
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public ChatColor getColor() {
        return color;
    }

    public String[] getDescription() {
        return description;
    }

}
