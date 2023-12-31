package shwendel.fishing.fishydex.fishtiary.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import shwendel.fishing.fishydex.fishtiary.gui.FishtiaryGUI;

public class FishtiaryCommand implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        new FishtiaryGUI((Player) sender).open();

        return true;
    }
}
