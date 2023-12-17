package shwendel.fishing;

import mc.obliviate.inventory.InventoryAPI;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import shwendel.fishing.crafting.command.CraftingCommand;
import shwendel.fishing.crafting.listener.CraftingInventoryListener;
import shwendel.fishing.fishing_mechanics.FishingCastListener;
import shwendel.fishing.fishydex.fishtiary.commands.FishtiaryCommand;
import shwendel.fishing.items.armor.ArmorEquipListener;
import shwendel.fishing.items.command.FishingItemCommand;
import shwendel.fishing.user.StatsCommand;
import shwendel.fishing.user.UserJoinListener;

public final class Fishing extends JavaPlugin {

    private static Fishing instance;

    @Override
    public void onEnable() {
        instance = this;

        new InventoryAPI(this).init();

        saveDefaultConfig();

        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new UserJoinListener(), this);
//        pluginManager.registerEvents(new FishingCastListener(), this);
        pluginManager.registerEvents(new ArmorEquipListener(), this);
        pluginManager.registerEvents(new CraftingInventoryListener(), this);
        pluginManager.registerEvents(new FishingCastListener(), this);

        getCommand("stats").setExecutor(new StatsCommand());
        getCommand("craft").setExecutor(new CraftingCommand());
        getCommand("fishingitem").setExecutor(new FishingItemCommand());
        getCommand("fishtiary").setExecutor(new FishtiaryCommand());

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
