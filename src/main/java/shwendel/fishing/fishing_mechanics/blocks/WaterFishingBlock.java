package shwendel.fishing.fishing_mechanics.blocks;

import org.bukkit.Material;
import org.bukkit.entity.FishHook;
import org.bukkit.inventory.ItemStack;
import shwendel.fishing.islands.Region;
import shwendel.fishing.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
public class WaterFishingBlock extends FishingBlock {

    @Override
    public ItemStack getRandomCatch(User user, FishHook fishHook, Region region) {
        Material material = fishHook.getLocation().clone().add(0, -0.2, 0).getBlock().getType();
        Random random = new Random();

        int number = random.nextInt(10000);
        long catchTime = System.currentTimeMillis();
        double weight = getRandomWeight(user);

        if(getApplicableBlocks().contains(material)) {

            if(number <= 4000) {
                return new Cod(region, weight, FishingItem.getNextEdition("Cod"), catchTime).createItem();
            } else if(number <= 4500) {
                return new Crab(region, weight, FishingItem.getNextEdition("Crab"), catchTime).createItem();
            } else if(number <= 5500) {
                return new Pufferfish(region, weight, FishingItem.getNextEdition("Pufferfish"), catchTime).createItem();
            } else if(number <= 5800) {
                return new Salmon(region, weight, FishingItem.getNextEdition("Salmon"), catchTime).createItem();
            } else if(number <= 8800) {
                return new Shrimp(region, weight, FishingItem.getNextEdition("Shrimp"), catchTime).createItem();
            } else if(number <= 8944) {
                return new BarrelHat(region, FishingItem.getNextEdition("Barrel Hat"), catchTime).createItem();
            } else {
                return new CoralShard(region).createItem();
            }

        }

        return null;

    }

    @Override
    public List<Material> getApplicableBlocks() {
        List<Material> blocks = new ArrayList<>();

        blocks.add(Material.WATER);

        return blocks;
    }

}
*/
