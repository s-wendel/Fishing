package shwendel.fishing.items.armor.emerald;

import org.bukkit.ChatColor;
import org.bukkit.entity.FishHook;
import org.bukkit.inventory.ItemStack;
import shwendel.fishing.islands.Region;
import shwendel.fishing.items.armor.FishingArmor;
import shwendel.fishing.user.Stat;
import shwendel.fishing.user.User;
import shwendel.fishing.util.FishingUtil;

import java.util.HashMap;
import java.util.Map;

public class EmeraldHelmet extends FishingArmor {

    public EmeraldHelmet(Region region, int edition, long catchTime) {
        name = "Emerald Helmet";
        item = FishingUtil.getHead(
                        "5d53fcc5-600f-4621-a335-b817098e9c04",
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzQ1ODY0NzYxNzZhMjU2NDczODE4YTE4NDkxNTdlODQ0YzU2Mzg3MjRiY2Y4MzA5ZjBkOWRmMjdjYTk1OTE2MCJ9fX0=",
                        "c4586476176a256473818a1849157e844c5638724bcf8309f0d9df27ca959160");
        description = null;
        super.region = region;
        isSellable = true;
        value = 2500;
        super.edition = edition;
        super.catchTime = catchTime;

        this.hasAbility = true;
        this.abilityDescription = new String[] { ChatColor.GRAY + "Gain " + ChatColor.YELLOW + "1⛃ " + ChatColor.GRAY + "every catch!" };


        Map<Stat, Double> stats = new HashMap<>();

        stats.put(Stat.FISHING_SPEED, 25d);
        stats.put(Stat.FISHING_LUCK, 5d);

        super.stats = stats;

    }

    @Override
    public void beforeCatch(User user, FishHook fishHook, long reelTime) {
        user.setCoins(user.getCoins() + 1);
    }

}
