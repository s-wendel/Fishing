package shwendel.fishing.crafting.command;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import shwendel.fishing.crafting.inventory.CraftingInventory;

public class CraftingCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;
        player.openInventory(CraftingInventory.getInventory());
        player.playSound(player.getLocation(), Sound.UI_LOOM_SELECT_PATTERN, 1f, 1f);

        return true;
    }
}
