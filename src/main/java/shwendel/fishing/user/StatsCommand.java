package shwendel.fishing.user;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import shwendel.fishing.util.FishingUtil;

public class StatsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if(args.length != 0) {

            try {

                player = Bukkit.getPlayer(args[0]);

            } catch(Exception exception) {}

        }

        User user = UserManager.getUser(player);

        sender.sendMessage("");
        sender.sendMessage(ChatColor.BOLD + "Stats of " + player.getName());
        sender.sendMessage("");

        sender.sendMessage(ChatColor.GRAY + "Joined on " + ChatColor.WHITE + FishingUtil.toReadableDate(user.getJoinDate()) + ChatColor.GRAY + " and has played for " + ChatColor.WHITE + FishingUtil.toTime(player.getStatistic(Statistic.PLAY_ONE_MINUTE) * 50));

        sender.sendMessage("");

        sender.sendMessage(ChatColor.GRAY + "Coins " + ChatColor.YELLOW + "" + user.getCoins() + "⛃");

        sender.sendMessage("");

        /*for(Stat stat : Stat.values()) {
            sender.sendMessage(stat.getColor() + stat.getIcon() + " " + ChatColor.GRAY + stat.getName() + stat.getColor() + " " + FishingUtil.displayNumber(user.getStatValue(stat, true)) + ChatColor.DARK_GRAY + " " + stat.getDescription());
        }*/

        sender.sendMessage("");

        return true;

    }

}
