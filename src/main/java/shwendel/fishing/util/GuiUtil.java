package shwendel.fishing.util;

import mc.obliviate.inventory.Gui;
import mc.obliviate.inventory.Icon;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.function.Consumer;

public class GuiUtil {

    public static final Icon DOES_NOT_EXIST = new Icon(Material.GRAY_STAINED_GLASS_PANE)
            .setName(ChatColor.DARK_GRAY + "???");
//            .setLore(ChatColor.GRAY + "???") // not sure if necessary
    public static final Icon BORDER = new Icon(Material.BLACK_STAINED_GLASS_PANE)
            .setName(" ");

    // TODO: allow for formatting
    public static ArrayList<Component> asLoreComponent(String... text) {
        ArrayList<Component> lore = new ArrayList<>();

        for (String line : text) {
            lore.add(Component.text(line, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        }

        return lore;
    }

    public static Icon simpleButton(Material mat, String name, String lore, Consumer<InventoryClickEvent> clickAction) {
        return new Icon(mat)
                .setName(ChatColor.WHITE + name)
                .setLore(ChatColor.GRAY + lore)
                .onClick(clickAction);
    }

    public static Icon simpleButton(Material mat, String name, Consumer<InventoryClickEvent> clickAction) {
        return simpleButton(mat, name, "", clickAction);
    }

    public static Icon backButton(Consumer<InventoryClickEvent> clickAction) {
        return new Icon(Material.BARRIER)
                .setName(ChatColor.RED + "Back")
                .onClick(clickAction);
    }

    public static Icon blank(Material mat) {
        return new Icon(mat).setName(" ");
    }

    public static void border(Gui gui, Icon icon) {
        gui.fillRow(icon, 0);
        gui.fillRow(icon, gui.getSize()/9-1);
        gui.fillColumn(icon, 0);
        gui.fillColumn(icon, 8);
    }

    public static void border(Gui gui) {
        border(gui, BORDER);
    }

    public static void openSound(Player p) {
        p.playSound(p, Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 0.5f, 1.51f);
    }

    public static void closeSound(Player p) {
        p.playSound(p, Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 0.5f, 1f);
    }
}
