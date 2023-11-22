package shwendel.fishing.crafting.listener;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import shwendel.fishing.Fishing;
import shwendel.fishing.crafting.ShapedRecipe;
import shwendel.fishing.crafting.ShapelessRecipe;
import shwendel.fishing.crafting.inventory.CraftingInventory;

import java.util.HashMap;
import java.util.Map;

public class CraftingInventoryListener implements Listener {

    @EventHandler
    public void inventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if(event.getView() == null || event.getView().getTitle() == null || !event.getView().getTitle().equals(CraftingInventory.TITLE) || event.getView().getTopInventory() == null || !event.getClickedInventory().equals(event.getView().getTopInventory())) {
            return;
        }

        int[] slots = new int[] { 10, 11, 12, 19, 20, 21, 28, 29, 30 };
        InventoryView inventory = event.getView();

        switch(event.getSlot()) {
            case 10: case 11: case 12: case 19: case 20: case 21: case 28: case 29: case 30:

                click(inventory);
                break;

            case 24:

                ItemStack crafted = inventory.getItem(24);

                if(crafted != null) {
                    for(int slot : slots) {
                        ItemStack item = event.getView().getItem(slot);

                        if(item != null) {
                            item.setAmount(item.getAmount() - 1);
                        }

                    }
                    player.playSound(player, Sound.UI_LOOM_TAKE_RESULT, 0.75f, 1.5f);

                }

                click(inventory);

                break;
            default:
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1f, 0.5f);
                event.setCancelled(true);
                break;
        }

    }

    @EventHandler
    public void inventoryDrag(InventoryDragEvent event) {
        Player player = (Player) event.getWhoClicked();

        if(event.getView() == null || event.getView().getTitle() == null || !event.getView().getTitle().equals(CraftingInventory.TITLE)) {
            return;
        }

        click(event.getView());
    }

    public void click(InventoryView inventory) {
        new BukkitRunnable() {
            @Override
            public void run() {
                int[] slots = new int[] { 10, 11, 12, 19, 20, 21, 28, 29, 30 };
                int counter = 0;
                Map<Integer, FishingItem> items = new HashMap<>();

                for(int slot : slots) {
                    FishingItem item = FishingItem.fromItem(inventory.getItem(slot));

                    if(item != null) {
                        items.put(counter, item);
                    }

                    counter++;
                }

                FishingItem item = ShapedRecipe.getItemFromRecipe(items);

                if(item == null) {
                    item = ShapelessRecipe.getItemFromRecipe(items);
                }

                inventory.setItem(24, item == null ? null : item.createItem());
            }
        }.runTaskLater(Fishing.getInstance(), 1);
    }

}
