package shwendel.fishing.items.materials.emerald;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import shwendel.fishing.islands.Region;
import shwendel.fishing.items.materials.FishingMaterial;

public class CompactEmerald extends FishingMaterial {

    public CompactEmerald(Region region) {
        name = "Compact Emerald";
        item = new ItemStack(Material.EMERALD_BLOCK);
        description = new String[] { "A compact form of an emerald", "It looks like your mom" };
        super.region = region;
        isSellable = true;
        value = 200;
    }

}
