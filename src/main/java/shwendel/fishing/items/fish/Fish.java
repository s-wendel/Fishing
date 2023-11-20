package shwendel.fishing.items.fish;

import com.destroystokyo.paper.Namespaced;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import shwendel.fishing.Fishing;
import shwendel.fishing.islands.Region;
import shwendel.fishing.items.FishingItem;
import shwendel.fishing.util.FishingUtil;
import shwendel.fishing.util.ItemBuilder;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Fish extends FishingItem {

    protected double weight; // How much the fish weighs, the value of the fish sold to shops goes up

    public double getWeight() {
        return weight;
    }

    public ItemStack createItem() {
        item = super.createItem();

        value = (int) (value * (this.weight / 2));

        ItemBuilder builder = new ItemBuilder(item);

        builder.setLore(
                ChatColor.GRAY + "Weight: " + ChatColor.GREEN + weight + "❉",
                ChatColor.GRAY + "Sold for: " + ChatColor.YELLOW + value + "⛃",
                "",
                ChatColor.DARK_GRAY + "Caught at " + region.getName(),
                ChatColor.DARK_GRAY + "on " + FishingUtil.toReadableDate(catchTime) + ". Edition #" + edition
                );

        ItemStack item = builder.toItemStack();
        ItemMeta itemMeta = item.getItemMeta();
        PersistentDataContainer data = itemMeta.getPersistentDataContainer();

        data.set(new NamespacedKey(Fishing.getInstance(), "weight"), PersistentDataType.STRING, weight + "");
        data.set(new NamespacedKey(Fishing.getInstance(), "value"), PersistentDataType.STRING, value + "");

        item.setItemMeta(itemMeta);

        return item;
    }

}
