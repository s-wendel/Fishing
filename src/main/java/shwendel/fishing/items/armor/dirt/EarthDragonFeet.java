package shwendel.fishing.items.armor.dirt;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.FishHook;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import shwendel.fishing.islands.Region;
import shwendel.fishing.items.armor.FishingArmor;
import shwendel.fishing.user.Stat;
import shwendel.fishing.user.User;
import shwendel.fishing.util.FishingUtil;

import java.util.HashMap;
import java.util.Map;

public class EarthDragonFeet extends FishingArmor {

    protected final double bonusFishingLuck = 6;

    public EarthDragonFeet(Region region, int edition, long catchTime) {
        name = "Earth Dragon Feet";

        ItemStack dyedArmor = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) dyedArmor.getItemMeta();
        leatherArmorMeta.setColor(Color.fromRGB(79,83, 50));
        dyedArmor.setItemMeta(leatherArmorMeta);
        item = dyedArmor;

        description = null;
        super.region = region;
        isSellable = false;
        value = 0;
        super.edition = edition;
        super.catchTime = catchTime;

        this.hasAbility = true;
        this.abilityDescription = new String[] {
                ChatColor.GRAY + "Gain " + Stat.FISHING_LUCK.getColor() + "+" + FishingUtil.displayNumber(bonusFishingLuck) + " Fishing Luck " + Stat.FISHING_LUCK.getIcon() + ChatColor.GRAY + " per",
                ChatColor.GRAY + "second that you don't reel in."
        };

        Map<Stat, Double> stats = new HashMap<>();

        stats.put(Stat.FISHING_SPEED, 45d);
        stats.put(Stat.FISHING_LUCK, 20d);
        stats.put(Stat.FISH_WEIGHT, 0.4d);
        stats.put(Stat.REEL_TIME, 1d);
        stats.put(Stat.MOVEMENT_SPEED, 30d);

        super.stats = stats;

    }

    @Override
    public void beforeCatch(User user, FishHook fishHook, long reelTime) {
        int luck = (int) (bonusFishingLuck * ((System.currentTimeMillis() - reelTime) / 1000));

        user.getStats().get(Stat.FISHING_LUCK).put(user.getEquipment("Earth Dragon Feet", Stat.FISHING_LUCK), user.getStatValue(Stat.FISHING_LUCK, true) + luck);
        user.updateCache(Stat.FISHING_LUCK);
    }

    @Override
    public void afterCatch(User user, FishHook fishHook) {
        ItemStack armor = user.getPlayer().getEquipment().getLeggings();

        if(armor != null && armor.hasItemMeta() && ChatColor.stripColor(armor.getItemMeta().getDisplayName()).equalsIgnoreCase("Earth Dragon Feet")) {

            user.getStats().get(Stat.FISHING_LUCK).put(user.getEquipment("Earth Dragon Feet", Stat.FISHING_LUCK), stats.get(Stat.FISHING_LUCK));
            user.updateCache(Stat.FISHING_LUCK);

        }
    }

}
