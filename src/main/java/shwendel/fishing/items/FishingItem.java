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
import shwendel.fishing.util.ItemBuilder;

public class FishingItem {

    protected String name;
    protected ItemStack item;
    protected String[] description;
    protected Region region;
    protected boolean isSellable;
    protected int value; // in coins
    protected int edition;
    protected long catchTime;

    public static boolean isFishingItem(ItemStack item) {
        if(item == null || !item.hasItemMeta()) {
            return false;
        }

        NamespacedKey key = new NamespacedKey(Fishing.getInstance(), "item");
        ItemMeta itemMeta = item.getItemMeta();
        PersistentDataContainer data = itemMeta.getPersistentDataContainer();

        return data.has(key);
    }

    public String getName() {
        return name;
    }

    public String[] getDescription() {
        return description;
    }

    public Region getRegionCaughtAt() {
        return region;
    }

    public boolean isSellable() {
        return isSellable;
    }

    public int getSellValue() {
        return value;
    }

    public static FishingItem fromItem(ItemStack item) {
        FishingItem fishingItem = new FishingItem();

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

        return fishingItem;
    }

    public ItemStack createItem() {
        ItemBuilder builder = new ItemBuilder(item);

        builder.addFlag(ItemFlag.HIDE_ENCHANTS);
        builder.addFlag(ItemFlag.HIDE_ATTRIBUTES);

        builder.setName(ChatColor.WHITE + name);

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

    public static int getNextEdition(String name) {
        int nextEdition = Fishing.getInstance().getConfig().isSet("editions." + name) ? Fishing.getInstance().getConfig().getInt("editions." + name) + 1 : 1;

        Fishing.getInstance().getConfig().set("editions." + name, nextEdition);
        Fishing.getInstance().saveConfig();

        return nextEdition;
    }

    public long getCatchTime() {
        return catchTime;
    }

}
