package shwendel.fishing.items.armor.dirt;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.FishHook;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import shwendel.fishing.islands.Region;
import shwendel.fishing.user.Stat;
import shwendel.fishing.user.User;
import shwendel.fishing.util.FishingUtil;

import java.util.HashMap;
import java.util.Map;

/*
public class EarthDragonHead extends FishingArmor {

    protected final double bonusFishingLuck = 7;

    public EarthDragonHead(Region region, int edition, long catchTime) {
        name = "Earth Dragon Head";

        item = FishingUtil.getHead(
                "735b4d1d-0341-4ea9-b1eb-05eb9be2e0e1",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjZjZWU0NTY1ZjdiYjI4N2RjMzkxYmE3ZmE1M2ExZmNiMDc4YThjMTk2N2RiZWJkMThkNjUxOTY0OGJlYjNkZCJ9fX0=",
                "66cee4565f7bb287dc391ba7fa53a1fcb078a8c1967dbebd18d6519648beb3dd");

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

        stats.put(Stat.FISHING_SPEED, 70d);
        stats.put(Stat.FISHING_LUCK, 20d);
        stats.put(Stat.FISH_WEIGHT, 0.35d);
        stats.put(Stat.REEL_TIME, 1.25d);
        stats.put(Stat.MOVEMENT_SPEED, 20d);
        stats.put(Stat.BACKLASH, 0.1d);

        super.stats = stats;

    }

    @Override
    public void beforeCatch(User user, FishHook fishHook, long reelTime) {
        int luck = (int) (bonusFishingLuck * ((System.currentTimeMillis() - reelTime) / 1000));

        user.getStats().get(Stat.FISHING_LUCK).put(user.getEquipment("Earth Dragon Head", Stat.FISHING_LUCK), user.getStatValue(Stat.FISHING_LUCK, true) + luck);
        user.updateCache(Stat.FISHING_LUCK);
    }

    @Override
    public void afterCatch(User user, FishHook fishHook) {
        ItemStack armor = user.getPlayer().getEquipment().getLeggings();

        if(armor != null && armor.hasItemMeta() && ChatColor.stripColor(armor.getItemMeta().getDisplayName()).equalsIgnoreCase("Earth Dragon Head")) {

            user.getStats().get(Stat.FISHING_LUCK).put(user.getEquipment("Earth Dragon Head", Stat.FISHING_LUCK), stats.get(Stat.FISHING_LUCK));
            user.updateCache(Stat.FISHING_LUCK);

        }
    }

}
*/
