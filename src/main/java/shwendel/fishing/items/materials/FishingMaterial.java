package shwendel.fishing.items.materials;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import shwendel.fishing.Fishing;
import shwendel.fishing.util.ItemBuilder;

public class FishingMaterial extends FishingItem {

    public ItemStack createItem() {
        ItemBuilder builder = new ItemBuilder(item);

        builder.setName(ChatColor.WHITE + name);

        builder.setLore(
                ChatColor.GRAY + "Sold for: " + ChatColor.YELLOW + value + "⛃",
                ""
        );

        for(String line : description) {
            builder.addLore(ChatColor.DARK_GRAY + line);
        }

        NamespacedKey key = new NamespacedKey(Fishing.getInstance(), "item");
        ItemStack item = builder.toItemStack();
        ItemMeta itemMeta = item.getItemMeta();

        PersistentDataContainer data = itemMeta.getPersistentDataContainer();

        data.set(new NamespacedKey(Fishing.getInstance(), "item"), PersistentDataType.STRING, name);
        data.set(new NamespacedKey(Fishing.getInstance(), "region"), PersistentDataType.STRING, region.toString());
        data.set(new NamespacedKey(Fishing.getInstance(), "value"), PersistentDataType.STRING, value + "");
        data.set(new NamespacedKey(Fishing.getInstance(), "edition"), PersistentDataType.STRING, edition + "");
        data.set(new NamespacedKey(Fishing.getInstance(), "catch_time"), PersistentDataType.STRING, catchTime + "");

        item.setItemMeta(itemMeta);

        return item;
    }

}
