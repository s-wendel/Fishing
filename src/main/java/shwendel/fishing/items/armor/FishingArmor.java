package shwendel.fishing.items.armor;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.FishHook;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import shwendel.fishing.Fishing;
import shwendel.fishing.islands.Region;
import shwendel.fishing.items.FishingEquipment;
import shwendel.fishing.items.FishingItem;
import shwendel.fishing.items.armor.dirt.*;
import shwendel.fishing.items.armor.emerald.EmeraldBoots;
import shwendel.fishing.items.armor.emerald.EmeraldChestplate;
import shwendel.fishing.items.armor.emerald.EmeraldHelmet;
import shwendel.fishing.items.armor.emerald.EmeraldLeggings;
import shwendel.fishing.items.armor.water.BarrelHat;
import shwendel.fishing.items.armor.water.CoralSandals;
import shwendel.fishing.user.Stat;
import shwendel.fishing.user.User;
import shwendel.fishing.util.FishingUtil;
import shwendel.fishing.util.ItemBuilder;

import java.util.HashMap;
import java.util.Map;

public class FishingArmor extends FishingEquipment {

    protected boolean hasAbility = false;
    protected String[] abilityDescription;

    public ItemStack createItem() {
        item = super.createItem();
        ItemBuilder builder = new ItemBuilder(item);

        if(hasAbility) {
            builder.addLore("");
            builder.setLore(abilityDescription);
        }

        if(isSellable) {
            builder.setLore(
                    "",
                    ChatColor.GRAY + "Sold for: " + ChatColor.YELLOW + value + "⛃"
            );
        }

        builder.setLore(
                "",
                ChatColor.DARK_GRAY + "Caught at " + region.getName(),
                ChatColor.DARK_GRAY + "on " + FishingUtil.toReadableDate(catchTime) + ". Edition #" + edition
        );

        ItemStack item = builder.toItemStack();
        ItemMeta itemMeta = item.getItemMeta();
        PersistentDataContainer data = itemMeta.getPersistentDataContainer();

        data.set(new NamespacedKey(Fishing.getInstance(), "value"), PersistentDataType.STRING, value + "");

        item.setItemMeta(itemMeta);

        return item;
    }

    public static FishingArmor fromItem(ItemStack item) {

        FishingArmor fishingItem = new FishingArmor();

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

        return getSubArmor(fishingItem);
    }

    public static FishingArmor getSubArmor(FishingArmor armor) {
        switch(armor.name.toUpperCase()) {
            case "EMERALD HELMET":
                return new EmeraldHelmet(armor.region, armor.edition, armor.catchTime);
            case "EMERALD CHESTPLATE":
                return new EmeraldChestplate(armor.region, armor.edition, armor.catchTime);
            case "EMERALD LEGGINGS":
                return new EmeraldLeggings(armor.region, armor.edition, armor.catchTime);
            case "EMERALD BOOTS":
                return new EmeraldBoots(armor.region, armor.edition, armor.catchTime);
            case "BARREL HAT":
                return new BarrelHat(armor.region, armor.edition, armor.catchTime);
            case "DIRT PANTS":
                return new DirtPants(armor.region, armor.edition, armor.catchTime);
            case "EARTH DRAGON HEAD":
                return new EarthDragonHead(armor.region, armor.edition, armor.catchTime);
            case "EARTH DRAGON CUIRASS":
                return new EarthDragonCuirass(armor.region, armor.edition, armor.catchTime);
            case "EARTH DRAGON LEGS":
                return new EarthDragonLegs(armor.region, armor.edition, armor.catchTime);
            case "EARTH DRAGON FEET":
                return new EarthDragonFeet(armor.region, armor.edition, armor.catchTime);
            case "CORAL SANDALS":
                return new CoralSandals(armor.region, armor.edition, armor.catchTime);
        }
        return null;
    }

    public void beforeCatch(User user, FishHook fishHook, long reelTime) {}

    public void afterCatch(User user, FishHook fishHook) {}

    public void onCast(User user, FishHook fishHook) {}

}
