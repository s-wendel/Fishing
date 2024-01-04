package shwendel.fishing.items.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import shwendel.fishing.items.FishingItem;
import shwendel.fishing.items.FishingItemManager;
import shwendel.fishing.items.FishingItemMemory;
import shwendel.fishing.open_world.Tornado;
import shwendel.fishing.open_world.Tornado;

public class FishingItemCommand implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Tornado tornado = new Tornado(((Player) sender).getLocation());

        tornado.run();

        // /fishingitem <item> <player>
        if(!sender.isOp()) {
            return true;
        }

        FishingItem.ID item = null;
        Player target = null;

        try {

            item = FishingItem.ID.valueOf(args[0]);
            target = Bukkit.getPlayer(args[1]);

        } catch(Exception exception) {

            sendHelpMessage(sender);

        }

        if(target == null) {

            if(!(sender instanceof Player)) {
                return true;
            }

            target = (Player) sender;

        }

        target.getInventory().addItem(FishingItemManager.getItem(item).getItemStack());

        return true;
    }

    private void sendHelpMessage(CommandSender sender) {
        sender.sendMessage("/fishingitem <item> [player]");
    }

}
