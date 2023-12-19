package shwendel.fishing.items;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import shwendel.fishing.Fishing;
import shwendel.fishing.user.Stat;
import shwendel.fishing.util.FishingUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// I didn't know what to name it I'm sorry
public class FishingItem {

    public enum ID {

        DIRT,
        EMERALD_CHESTPLATE,
        COD,
        SALMON,
        EXAMPLE_ROD,
        ;
    }

    private ID id;
    private ItemStack baseItem; // The base material
    private String name; // Name displayed on item
    private long sellValue; // Sell value, -1 if unsellable
    private List<String> description;
    //private FishingAbility ability;
    //private List<String> abilityDescription;
    private Map<Stat, Double> stats;

    public FishingItem(ID id, ItemStack baseItem, String name, long sellValue, List<String> description, Map<Stat, Double> stats) {
        this.id = id;
        this.baseItem = baseItem;
        this.name = name;
        this.sellValue = sellValue;
        this.description = description;
        this.stats = stats;
    }

    public static FishingItem fromID(ID id) {
        return FishingItemManager.getItem(id);
    }

    public ID getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getSellValue() {
        return sellValue;
    }

    public boolean isSellable() {
        return sellValue != -1;
    }

    public List<String> getDescription() {
        return description;
    }

    public Map<Stat, Double> getStats() {
        return stats;
    }

    public ItemStack getItem() {

        ItemStack item = baseItem.clone();
        ItemMeta itemMeta = item.getItemMeta();

        List<String> lore = new ArrayList<>();

        String concat = "";

        for(Stat stat : stats.keySet()) {
            double number = stats.get(stat);

            lore.add(stat.getColor() + stat.getIcon() + ChatColor.GRAY + " " + stat.getName() + stat.getColor() + " " + (number < 0 ? "" : "+") + FishingUtil.displayNumber(number));

            concat += stat + ":" + number + ",";
        }

        if(!stats.isEmpty()) {
            lore.add("");
        }

        int concatLength = concat.length();

        concat = concat.substring(0, concatLength > 0 ? concatLength - 1 : 0);

        PersistentDataContainer data = itemMeta.getPersistentDataContainer();

        data.set(new NamespacedKey(Fishing.getInstance(), "item"), PersistentDataType.STRING, name);
        data.set(new NamespacedKey(Fishing.getInstance(), "value"), PersistentDataType.LONG, sellValue);
        //data.set(new NamespacedKey(Fishing.getInstance(), "edition"), PersistentDataType.STRING, edition + "");
        //data.set(new NamespacedKey(Fishing.getInstance(), "catch_time"), PersistentDataType.STRING, catchTime + "");
        //TODO Edition
        data.set(new NamespacedKey(Fishing.getInstance(), "stats"), PersistentDataType.STRING, concat);
        data.set(new NamespacedKey(Fishing.getInstance(), "equipment"), PersistentDataType.STRING, name);

        if(isSellable()) {
            lore.add(ChatColor.GRAY + "Sold for: " + ChatColor.YELLOW + sellValue + "⛃");
            lore.add("");
        }

        for(String line : description) {
            lore.add(ChatColor.DARK_GRAY + line);
        }

        itemMeta.setDisplayName(ChatColor.WHITE + name);
        itemMeta.setLore(lore);
        itemMeta.addItemFlags(new ItemFlag[] {
                ItemFlag.HIDE_ENCHANTS,
                ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_DYE
        });

        item.setItemMeta(itemMeta);

        return item;

    }

}
