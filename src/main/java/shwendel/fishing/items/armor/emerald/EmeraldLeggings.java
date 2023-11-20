package shwendel.fishing.items.armor.emerald;

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

import java.util.HashMap;
import java.util.Map;

public class EmeraldLeggings extends FishingArmor {

    public EmeraldLeggings(Region region, int edition, long catchTime) {
        name = "Emerald Leggings";

        ItemStack dyedArmor = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) dyedArmor.getItemMeta();
        leatherArmorMeta.setColor(Color.fromRGB(103, 133, 61));
        dyedArmor.setItemMeta(leatherArmorMeta);

        item = dyedArmor;

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
