package shwendel.fishing.items.armor.water;

import org.bukkit.ChatColor;
import org.bukkit.entity.FishHook;
import org.bukkit.inventory.ItemStack;
import shwendel.fishing.islands.Region;
import shwendel.fishing.user.Stat;
import shwendel.fishing.user.User;
import shwendel.fishing.util.FishingUtil;

import java.util.HashMap;
import java.util.Map;

/*
public class BarrelHat extends FishingArmor {

    protected final double bonusFishingSpeed = 20;
    protected final double bonusFishWeight = 0.5;

    public BarrelHat(Region region, int edition, long catchTime) {
        name = "Barrel Hat";
        item = FishingUtil.getHead(
                "eebc9c56-f0b3-452a-a434-4c265699c12d",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTE5M2M4OWQxZGY2Nzk4NTRmMzNjMjIxNTI0N2I2NzZhMTU5ZDUzOTUzOTJkMGM2MWI4NDc2ZjgxM2Q5ZWRiMCJ9fX0=",
                "5193c89d1df679854f33c2215247b676a159d5395392d0c61b8476f813d9edb0");
        description = null;
        super.region = region;
        isSellable = true;
        value = 800;
        super.edition = edition;
        super.catchTime = catchTime;

        Map<Stat, Double> stats = new HashMap<>();

        stats.put(Stat.FISHING_SPEED, 10d);
        stats.put(Stat.FISH_WEIGHT, 0.2);
        stats.put(Stat.REEL_TIME, 0.5d);
        stats.put(Stat.MOVEMENT_SPEED, 10d);

        hasAbility = true;
        abilityDescription = new String[] {
                ChatColor.GRAY + "Gain " + Stat.FISHING_SPEED.getColor() + "+" + FishingUtil.displayNumber(bonusFishingSpeed) + " Fishing Speed " + Stat.FISHING_SPEED.getIcon() + ChatColor.GRAY + "and" ,
                Stat.FISH_WEIGHT.getColor() + "+" + FishingUtil.displayNumber(bonusFishWeight) + " Fish Weight " + Stat.FISH_WEIGHT.getIcon() + ChatColor.GRAY + " while fishing in water" };

        super.stats = stats;

    }

    @Override
    public void onCast(User user, FishHook fishHook) {

        ItemStack hat = user.getPlayer().getEquipment().getHelmet();

        user.getStats().get(Stat.FISHING_SPEED).put(user.getEquipment("Barrel Hat", Stat.FISHING_SPEED), user.getStatValue(Stat.FISHING_SPEED, true) + bonusFishingSpeed);
        user.updateCache(Stat.FISHING_SPEED);

        user.getStats().get(Stat.FISH_WEIGHT).put(user.getEquipment("Barrel Hat", Stat.FISH_WEIGHT), user.getStatValue(Stat.FISH_WEIGHT, true) + bonusFishWeight);
        user.updateCache(Stat.FISH_WEIGHT);

    }

    @Override
    public void beforeCatch(User user, FishHook fishHook, long reelTime) {
        ItemStack hat = user.getPlayer().getEquipment().getHelmet();

        if(hat != null && hat.hasItemMeta() && ChatColor.stripColor(hat.getItemMeta().getDisplayName()).equalsIgnoreCase("Barrel Hat")) {

            user.getStats().get(Stat.FISHING_SPEED).put(user.getEquipment("Barrel Hat", Stat.FISHING_SPEED), stats.get(Stat.FISHING_SPEED));
            user.updateCache(Stat.FISHING_SPEED);

            user.getStats().get(Stat.FISH_WEIGHT).put(user.getEquipment("Barrel Hat", Stat.FISH_WEIGHT), stats.get(Stat.FISH_WEIGHT));
            user.updateCache(Stat.FISH_WEIGHT);

        }
    }

}
*/
