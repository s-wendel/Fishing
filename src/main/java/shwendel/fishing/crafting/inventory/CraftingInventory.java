package shwendel.fishing.crafting.inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import shwendel.fishing.util.ItemBuilder;

public class CraftingInventory {

    public final static String TITLE;

    static {
        TITLE = "Crafting ";
    }

    public static Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(null, 54, TITLE);

        for(int i = 0; i < 45; i++) {

            // Every slot except the crafting slots
            if(((i % 9 == 1 || i % 9 == 2 || i % 9 == 3) && i >= 9 * 1 && i <= 9 * 4) || i == 24) {
                continue;
            }

            inventory.setItem(i,
                    new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)
                            .setName("")
                            .toItemStack()
            );

        }

        for(int i = 45; i < 54; i++) {

            inventory.setItem(i,
                    new ItemBuilder(Material.ORANGE_STAINED_GLASS_PANE)
                            .setName("")
                            .toItemStack()
            );

        }

        return inventory;
    }

}
