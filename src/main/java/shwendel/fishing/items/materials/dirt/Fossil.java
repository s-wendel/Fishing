package shwendel.fishing.items.materials.dirt;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import shwendel.fishing.islands.Region;
import shwendel.fishing.items.materials.FishingMaterial;

public class Fossil extends FishingMaterial {

    public Fossil(Region region) {
        name = "Fossil";
        item = new ItemStack(Material.BONE);
        description = new String[] { "Rust is better than Ark.", "I said it. Do something about it." };
        super.region = region;
        isSellable = true;
        value = 5000;
    }

}
