package shwendel.fishing.items.armor.water;

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

public class CoralSandals extends FishingArmor {
    protected final double movementSpeedAmplifier = 1.75;

    public CoralSandals(Region region, int edition, long catchTime) {
        name = "Coral Sandals";

        ItemStack dyedArmor = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) dyedArmor.getItemMeta();
        leatherArmorMeta.setColor(Color.fromRGB(203, 68, 189));
        dyedArmor.setItemMeta(leatherArmorMeta);

        super.item = dyedArmor;
        description = null;
        super.region = region;
        isSellable = true;
        value = 64;
        super.edition = edition;
        super.catchTime = catchTime;

        Map<Stat, Double> stats = new HashMap<>();

        stats.put(Stat.FISHING_SPEED, 5d);
        stats.put(Stat.FISH_WEIGHT, 0.05);
        stats.put(Stat.MOVEMENT_SPEED, 4d);

        hasAbility = true;
        abilityDescription = new String[] {
                ChatColor.GRAY + "Gain " + Stat.MOVEMENT_SPEED.getColor() + "+" + movementSpeedAmplifier + "x Movement Speed " + Stat.MOVEMENT_SPEED.getIcon() + ChatColor.GRAY + " while",
                ChatColor.GRAY + "walking in " + Region.CORAL_COAST.getColor() + Region.CORAL_COAST.getIcon() + " Coral Coast"
        };

        super.stats = stats;

    }

    @Override
    public void onCast(User user, FishHook fishHook) {

        /*ItemStack hat = user.getPlayer().getEquipment().getHelmet();

        user.getStats().get(Stat.FISHING_SPEED).put(user.getEquipment("Barrel Hat", Stat.FISHING_SPEED), user.getStatValue(Stat.FISHING_SPEED, true) + bonusFishingSpeed);
        user.updateCache(Stat.FISHING_SPEED);

        user.getStats().get(Stat.FISH_WEIGHT).put(user.getEquipment("Barrel Hat", Stat.FISH_WEIGHT), user.getStatValue(Stat.FISH_WEIGHT, true) + bonusFishWeight);
        user.updateCache(Stat.FISH_WEIGHT);*/

    }

    @Override
    public void beforeCatch(User user, FishHook fishHook, long reelTime) {
        /*ItemStack hat = user.getPlayer().getEquipment().getHelmet();

        if(hat != null && hat.hasItemMeta() && ChatColor.stripColor(hat.getItemMeta().getDisplayName()).equalsIgnoreCase("Barrel Hat")) {

            user.getStats().get(Stat.FISHING_SPEED).put(user.getEquipment("Barrel Hat", Stat.FISHING_SPEED), stats.get(Stat.FISHING_SPEED));
            user.updateCache(Stat.FISHING_SPEED);

            user.getStats().get(Stat.FISH_WEIGHT).put(user.getEquipment("Barrel Hat", Stat.FISH_WEIGHT), stats.get(Stat.FISH_WEIGHT));
            user.updateCache(Stat.FISH_WEIGHT);

        }*/
    }

}
