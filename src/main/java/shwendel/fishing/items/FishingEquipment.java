package shwendel.fishing.items;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import shwendel.fishing.Fishing;
import shwendel.fishing.islands.Region;
import shwendel.fishing.user.Stat;
import shwendel.fishing.user.User;
import shwendel.fishing.util.FishingUtil;
import shwendel.fishing.util.ItemBuilder;

import java.util.HashMap;
import java.util.Map;

/*
public class FishingEquipment extends FishingItem {

    protected Map<Stat, Double> stats;

    public ItemStack createItem() {
        item = super.createItem();
        ItemBuilder builder = new ItemBuilder(item);
        String concat = "";

        builder.addFlag(ItemFlag.HIDE_DYE);

        for(Stat stat : stats.keySet()) {
            double number = stats.get(stat);

            builder.addLore(stat.getColor() + stat.getIcon() + ChatColor.GRAY + " " + stat.getName() + stat.getColor() + " " + (number < 0 ? "" : "+") + FishingUtil.displayNumber(number));

            concat += stat.toString() + ":" + number + ",";
        }

        concat = concat.substring(0, concat.length() - 1);

        ItemStack item = builder.toItemStack();
        ItemMeta itemMeta = item.getItemMeta();
        PersistentDataContainer data = itemMeta.getPersistentDataContainer();

        data.set(new NamespacedKey(Fishing.getInstance(), "stats"), PersistentDataType.STRING, concat);
        data.set(new NamespacedKey(Fishing.getInstance(), "equipment"), PersistentDataType.STRING, name);

        item.setItemMeta(itemMeta);

        return item;
    }

    public Map<Stat, Double> getStats() {
        return stats;
    }

    public static FishingEquipment fromItem(ItemStack item) {

        FishingEquipment fishingItem = new FishingEquipment();

        if(item == null || !item.hasItemMeta()) {
            return null;
        }

        ItemMeta itemMeta = item.getItemMeta();
        PersistentDataContainer data = itemMeta.getPersistentDataContainer();

        if(!data.has(new NamespacedKey(Fishing.getInstance(), "item"))) {
            return null;
        }

        fishingItem.name = data.get(new NamespacedKey(Fishing.getInstance(), "item"), PersistentDataType.STRING);
        fishingItem.region = Region.valueOf(data.get(new NamespacedKey(Fishing.getInstance(), "region"), PersistentDataType.STRING));
        fishingItem.value = Integer.parseInt(data.get(new NamespacedKey(Fishing.getInstance(), "value"), PersistentDataType.STRING));
        fishingItem.edition = Integer.parseInt(data.get(new NamespacedKey(Fishing.getInstance(), "edition"), PersistentDataType.STRING));
        fishingItem.catchTime = Long.parseLong(data.get(new NamespacedKey(Fishing.getInstance(), "catch_time"), PersistentDataType.STRING));

        Map<Stat, Double> stats = new HashMap<>();

        for(String string : data.get(new NamespacedKey(Fishing.getInstance(), "stats"), PersistentDataType.STRING).split(",")) {
            String[] statSplit = string.split(":");
            stats.put(Stat.valueOf(statSplit[0]), Double.parseDouble(statSplit[1]));
        }

        fishingItem.stats = stats;

        return fishingItem;
    }

    public static boolean isFishingEquipment(ItemStack item) {
        if(item == null || !item.hasItemMeta()) {
            return false;
        }

        NamespacedKey key = new NamespacedKey(Fishing.getInstance(), "equipment");
        ItemMeta itemMeta = item.getItemMeta();
        PersistentDataContainer data = itemMeta.getPersistentDataContainer();

        return data.has(key);
    }

    public void onEquip(User user) {

    }

    public void onUnequip(User user) {

    }

}
*/
