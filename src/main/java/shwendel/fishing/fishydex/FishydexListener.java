package shwendel.fishing.fishydex;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.TextDecoration.ITALIC;
import static shwendel.fishing.util.FishingUtil.getNamespacedKey;
import static shwendel.fishing.util.GuiUtil.asLoreComponent;

public class FishydexListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        ItemStack item = new ItemStack(Material.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE);
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ITEM_SPECIFICS);
        meta.displayName(text("Fishydex").decoration(ITALIC, false));
        meta.lore(asLoreComponent("The fishiest of dexes!"));
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.set(getNamespacedKey("fishydex"), PersistentDataType.BOOLEAN, true); // value of tag doesn't matter, we're just looking for existence of tag
        item.setItemMeta(meta);

        event.getPlayer().getInventory().setItem(8, item);
    }

    @EventHandler
    public void rightClickFishydex(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) return;

        ItemStack item = event.getItem();
        if (item == null || !item.hasItemMeta()) return;
        if (!item.getItemMeta().getPersistentDataContainer().has(getNamespacedKey("fishydex"))) return;

        new FishydexGUI(event.getPlayer()).open();
    }
}
