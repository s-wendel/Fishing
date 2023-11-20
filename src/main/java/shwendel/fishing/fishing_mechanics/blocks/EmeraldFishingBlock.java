package shwendel.fishing.fishing_mechanics.blocks;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.FishHook;
import org.bukkit.inventory.ItemStack;
import shwendel.fishing.islands.Region;
import shwendel.fishing.items.FishingItem;
import shwendel.fishing.items.armor.emerald.EmeraldBoots;
import shwendel.fishing.items.armor.emerald.EmeraldChestplate;
import shwendel.fishing.items.armor.emerald.EmeraldHelmet;
import shwendel.fishing.items.armor.emerald.EmeraldLeggings;
import shwendel.fishing.items.fish.dirt.MudMonstrosity;
import shwendel.fishing.items.materials.FishingMaterial;
import shwendel.fishing.items.materials.dirt.CompactDirt;
import shwendel.fishing.items.materials.dirt.Dirt;
import shwendel.fishing.items.materials.dirt.EarthDragonScale;
import shwendel.fishing.items.materials.dirt.Fossil;
import shwendel.fishing.items.materials.emerald.CompactEmerald;
import shwendel.fishing.items.materials.emerald.Emerald;
import shwendel.fishing.items.materials.emerald.EmeraldHandle;
import shwendel.fishing.items.materials.emerald.ShinyEmerald;
import shwendel.fishing.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EmeraldFishingBlock extends FishingBlock {

    @Override
    public ItemStack getRandomCatch(User user, FishHook fishHook, Region region) {
        Material material = fishHook.getLocation().clone().add(0, -0.2, 0).getBlock().getType();
        Random random = new Random();

        int number = random.nextInt(10000);
        long catchTime = System.currentTimeMillis();
        double weight = getRandomWeight(user);

        if(getApplicableBlocks().contains(material)) {

            if(number <= 9200) {
                return new Emerald(region).createItem();
            } else if(number <= 9550) {
                return new CompactEmerald(region).createItem();
            } else if(number <= 9750) {
                return new ShinyEmerald(region).createItem();
            } else if(number <= 9825) {
                return new EmeraldHelmet(region, 1, catchTime).createItem();
            } else if(number <= 9875) {
                return new EmeraldChestplate(region, 1, catchTime).createItem();
            } else if(number <= 9925) {
                return new EmeraldLeggings(region, 1, catchTime).createItem();
            } else if(number <= 9975) {
                return new EmeraldBoots(region, 1, catchTime).createItem();
            } else {
                return new EmeraldHandle(region).createItem();
            }

        }

        return null;

    }

    @Override
    public List<Material> getApplicableBlocks() {
        List<Material> blocks = new ArrayList<>();

        blocks.add(Material.EMERALD_ORE);
        blocks.add(Material.EMERALD_BLOCK);

        return blocks;
    }

}
