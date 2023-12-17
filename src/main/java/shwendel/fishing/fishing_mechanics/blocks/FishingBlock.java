package shwendel.fishing.fishing_mechanics.blocks;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.FishHook;
import org.bukkit.inventory.ItemStack;
import shwendel.fishing.islands.Region;
import shwendel.fishing.user.Stat;
import shwendel.fishing.user.User;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class FishingBlock {

    public double getRandomWeight(User user) {
        return Math.round((ThreadLocalRandom.current().nextDouble(0.5, 1.5) + user.getStatValue(Stat.FISH_WEIGHT, true)) * 10.0) / 10.0;
    }

    public abstract ItemStack getRandomCatch(User user, FishHook fishHook, Region region);
    public abstract List<Material> getApplicableBlocks();

    public static FishingBlock[] getFishingBlocks() {
        return new FishingBlock[] {
                //new WaterFishingBlock(),
                new DirtFishingBlock(),
                new EmeraldFishingBlock()
        };
    }

    public static FishingBlock getFishingBlock(Material material) {
        for(FishingBlock block : getFishingBlocks()) {
            if(block.getApplicableBlocks().contains(material)) {
                return block;
            }
        }
        return null;
    }


}
