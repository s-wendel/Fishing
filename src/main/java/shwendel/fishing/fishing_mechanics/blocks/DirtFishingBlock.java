package shwendel.fishing.fishing_mechanics.blocks;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.FishHook;
import org.bukkit.inventory.ItemStack;
import shwendel.fishing.islands.Region;
import shwendel.fishing.items.FishingItem;
import shwendel.fishing.items.armor.dirt.*;
import shwendel.fishing.items.armor.water.BarrelHat;
import shwendel.fishing.items.fish.dirt.MudMonstrosity;
import shwendel.fishing.items.fish.water.*;
import shwendel.fishing.items.materials.dirt.CompactDirt;
import shwendel.fishing.items.materials.dirt.Dirt;
import shwendel.fishing.items.materials.dirt.EarthDragonScale;
import shwendel.fishing.items.materials.dirt.Fossil;
import shwendel.fishing.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DirtFishingBlock extends FishingBlock {

    @Override
    public ItemStack getRandomCatch(User user, FishHook fishHook, Region region) {
        Material material = fishHook.getLocation().clone().add(0, -0.2, 0).getBlock().getType();
        Random random = new Random();

        int number = random.nextInt(10000);
        long catchTime = System.currentTimeMillis();
        double weight = getRandomWeight(user);

        if(getApplicableBlocks().contains(material)) {

           if(number <= 7800) {
               return new Dirt(region).createItem();
           } else if(number <= 9200) {
               return new CompactDirt(region).createItem();
           } else if(number <= 9750) {
               return new MudMonstrosity(region, weight, FishingItem.getNextEdition("Mud Monstrosity"), catchTime).createItem();
           } else if(number <= 9980) {
               return new Fossil(region).createItem();
           } else {
               return new EarthDragonScale(region).createItem();
           }

        }

        return null;

    }

    @Override
    public List<Material> getApplicableBlocks() {
        List<Material> blocks = new ArrayList<>();

        blocks.add(Material.DIRT);
        blocks.add(Material.GRASS_BLOCK);
        blocks.add(Material.COARSE_DIRT);

        return blocks;
    }

}
