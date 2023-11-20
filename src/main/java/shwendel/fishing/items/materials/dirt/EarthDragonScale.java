package shwendel.fishing.items.materials.dirt;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import shwendel.fishing.islands.Region;
import shwendel.fishing.items.materials.FishingMaterial;

public class EarthDragonScale extends FishingMaterial {

    public EarthDragonScale(Region region) {
        name = "Earth Dragon Scale";
        item = new ItemStack(Material.BROWN_DYE);
        description = new String[] { "A scale of an ancient dragon", "that typically roamed in the Jungle" };
        super.region = region;
        isSellable = true;
        value = 10000;
    }

}
