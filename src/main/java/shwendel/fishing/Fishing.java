package shwendel.fishing;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import shwendel.fishing.crafting.command.CraftingCommand;
import shwendel.fishing.crafting.listener.CraftingInventoryListener;
import shwendel.fishing.items.armor.ArmorEquipListener;
import shwendel.fishing.items.command.FishingItemCommand;
import shwendel.fishing.user.StatsCommand;

public final class Fishing extends JavaPlugin {

    private static Fishing instance;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new ArmorEquipListener(), this);
        pluginManager.registerEvents(new CraftingInventoryListener(), this);

        getCommand("stats").setExecutor(new StatsCommand());
        getCommand("craft").setExecutor(new CraftingCommand());
        getCommand("fishingitem").setExecutor(new FishingItemCommand());
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static Fishing getInstance() {
        return instance;
    }

}
