package shwendel.fishing.user;

import org.bukkit.ChatColor;

public enum Stat {

    MOVEMENT_SPEED("☄", "Movement Speed", ChatColor.GOLD, 100, "The speed at which you walk. You moron."),
    BOAT_SPEED("☂", "Boat Speed", ChatColor.BLUE, 50, "The speed at which you boat. You fool."),
    REEL_TIME("®", "Reel Time", ChatColor.DARK_GREEN, 2, "The seconds before a fish leaves after a reel."),
    FISH_WEIGHT("❉", "Fish Weight", ChatColor.LIGHT_PURPLE, 0, "The added weight to the default weight of a fish."),
    FISHING_LUCK("☘", "Fishing Luck", ChatColor.GREEN, 0, "Increases the chance of items below 5% by X%"),
    FISHING_SPEED("🎣", "Fishing Speed", ChatColor.AQUA, 0, "Increases the speed at which you fish. Yea."),
    BACKLASH("☠", "Backlash", ChatColor.RED, 0, "Knockback taken after every catch."),
    ;
//◎ rune, enchants like gemstones

    // fish hunter quest, hunt certain fish for ar eward
    //
    private String icon;
    private String displayName;
    private ChatColor color;
    private double base; // The starting base stat
    private String description;

    Stat(String icon, String displayName, ChatColor color, double base, String description) {
       this.icon = icon;
       this.displayName = displayName;
       this.color = color;
       this.base = base;
       this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public String getName() {
        return displayName;
    }

    public ChatColor getColor() {
        return color;
    }

    public double getBase() {
        return base;
    }

    public String getDescription() {
        return description;
    }

}
