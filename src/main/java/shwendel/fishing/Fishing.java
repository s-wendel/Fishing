package shwendel.fishing;

import org.bukkit.plugin.java.JavaPlugin;
import shwendel.fishing.crafting.command.CraftingCommand;
import shwendel.fishing.crafting.listener.CraftingInventoryListener;
import shwendel.fishing.fishing_mechanics.FishingCastListener;
import shwendel.fishing.items.armor.ArmorEquipListener;
import shwendel.fishing.user.StatsCommand;
import shwendel.fishing.user.UserJoinListener;

public final class Fishing extends JavaPlugin {

    private static Fishing instance;

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new FishingCastListener(), this);
        getServer().getPluginManager().registerEvents(new UserJoinListener(), this);
        getServer().getPluginManager().registerEvents(new ArmorEquipListener(), this);
        getServer().getPluginManager().registerEvents(new CraftingInventoryListener(), this);

        getCommand("stats").setExecutor(new StatsCommand());
        getCommand("craft").setExecutor(new CraftingCommand());
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    // CHANGE MADE

    public static Fishing getInstance() {
        return instance;
    }

}
