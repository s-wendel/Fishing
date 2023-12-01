package shwendel.fishing.items.armor.dirt;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.FishHook;
import org.bukkit.inventory.ItemStack;
import shwendel.fishing.islands.Region;
import shwendel.fishing.user.Stat;
import shwendel.fishing.user.User;
import shwendel.fishing.util.FishingUtil;

import java.util.HashMap;
import java.util.Map;

/*
public class DirtPants extends FishingArmor {

    protected final double fishingAmplifier = 1.5;

    public DirtPants(Region region, int edition, long catchTime) {
        name = "Dirt Pants";
        item = new ItemStack(Material.LEATHER_LEGGINGS);
        description = null;
        super.region = region;
        isSellable = true;
        value = 300;
        super.edition = edition;
        super.catchTime = catchTime;

        Map<Stat, Double> stats = new HashMap<>();

        stats.put(Stat.FISHING_SPEED, 5d);
        stats.put(Stat.FISHING_LUCK, -5d);

        hasAbility = true;
        abilityDescription = new String[] {
                ChatColor.GRAY + "Gain " + Stat.FISHING_SPEED.getColor() + FishingUtil.displayNumber(fishingAmplifier) + "x Fishing Speed " + Stat.FISHING_SPEED.getIcon(),
                ChatColor.GRAY + "while fishing on dirt"
        };

        super.stats = stats;

    }

    @Override
    public void onCast(User user, FishHook fishHook) {

        ItemStack hat = user.getPlayer().getEquipment().getHelmet();

        user.getStats().get(Stat.FISHING_SPEED).put(user.getEquipment("Dirt Pants", Stat.FISHING_SPEED), user.getStatValue(Stat.FISHING_SPEED, true) * fishingAmplifier);
        user.updateCache(Stat.FISHING_SPEED);

    }

    @Override
    public void beforeCatch(User user, FishHook fishHook, long reelTime) {
        ItemStack pants = user.getPlayer().getEquipment().getLeggings();

        if(pants != null && pants.hasItemMeta() && ChatColor.stripColor(pants.getItemMeta().getDisplayName()).equalsIgnoreCase("Dirt Pants")) {

            user.getStats().get(Stat.FISHING_SPEED).put(user.getEquipment("Dirt Pants", Stat.FISHING_SPEED), stats.get(Stat.FISHING_SPEED));
            user.updateCache(Stat.FISHING_SPEED);

        }
    }

}
*/
