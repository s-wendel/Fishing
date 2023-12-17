package shwendel.fishing.fishydex.fishtiary.gui;

import mc.obliviate.inventory.Gui;
import mc.obliviate.inventory.Icon;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import shwendel.fishing.fishydex.fishtiary.FishtiaryEntry;
import shwendel.fishing.fishydex.fishtiary.FishtiarySet;
import shwendel.fishing.items.FishingItem;
import shwendel.fishing.items.FishingItemManager;
import shwendel.fishing.items.FishingItemMemory;
import shwendel.fishing.util.ProgressBar;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class FishtiarySetGUI extends Gui {

    private static final Icon DOES_NOT_EXIST = new Icon(Material.GRAY_STAINED_GLASS_PANE)
            .setName(ChatColor.DARK_GRAY + "???")
            .setLore(
                    ChatColor.GRAY + "???"
            );
    private static final Icon BLANK = new Icon(Material.BLACK_STAINED_GLASS_PANE)
            .setName(" ");

    private FishtiarySet set;

    public FishtiarySetGUI(Player player, FishtiarySet set) {
        super(
                player,
                set.getItem().toString() + "_FISHTIARYSET",
                "Fishtiary ➡ " + FishingItemManager.getItem(set.getItem()).getName(),
                6
        );
        this.set = set;
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {

        FishingItemMemory fishingItemMemory = set.getItem().getFishingItemMemory();
        String fishingItemName = fishingItemMemory.getName();

        int total = 1; // The amount the player has collected TODO

        for(int i = 0; i < 54; i++) {

            int snakeIndex = getSnakeIndex(i);

            if(snakeIndex >= 0) {

                FishtiaryEntry entry = set.getEntry(snakeIndex);
                Icon icon;

                if(entry == null) {

                    icon = DOES_NOT_EXIST;

                } else {

                    icon = new Icon(Material.LIME_STAINED_GLASS_PANE)
                            .setName(ChatColor.GREEN + fishingItemName + " " + (snakeIndex + 1))
                            .setLore(getEntryLore(entry, total));

                }

                addItem(i, icon);
                continue;
            }

            switch(i) {

                case 4:
                    // Mat
                    break;

                case 49:
                    // Back button;
                    break;

                default:

                    addItem(i, BLANK);

                    break;

            }

        }

    }

    /**
     * Returns the snake index from an index in the GUI
     * @param slot The slot
     * @return The snake index
     */
    private int getSnakeIndex(int slot) {

        int column = slot % 9;
        int row = slot / 9;

        int columnRemainder = column % 4;

        // Snake body column
        if(column % 2 == 1 && row >= 1 && row <= 4) {

            // I don't know the right term for this but for example:
            // The pattern goes down 0, 1, 2, 3 on every other odd column starting from 1 but goes
            // up 5, 6, 7, 8 on the others, this is a way to flip the direction the equation is going
            int additive = 0;
            int amplifier = 0;
            int remainder = 0;

            switch(columnRemainder) {

                // It's going up, e.g. 6->9
                case 3:

                    additive = 3;
                    amplifier = -1;
                    remainder = 5;

                    break;

                // It's going down, e.g. 1->4
                case 1:

                    additive = 0;
                    amplifier = 1;

                    break;


            }

            return (additive + amplifier * (((slot - column) / 9) - 1) + remainder) + (10 * (column / 4));

        }

        if(column == 8) {
            return -1;
        }

        int nonBodyIndex = 5 * (column / 2) - 1;

        switch(columnRemainder) {

            case 2:

                if(row == 4) {
                    return nonBodyIndex;
                }

                break;

            case 0:

                if(row == 1) {
                    return nonBodyIndex;
                }

                break;

        }

        return -1;

    }

    private List<String> getEntryLore(FishtiaryEntry entry, int total) {

        String fishingItemName = entry.getItem().getFishingItemMemory().getName();

        int required = entry.getRequired();
        double progress = (double) total / required;

        List<String> lore = new ArrayList<>();

        lore.add("");
        lore.add(ChatColor.WHITE + "Collect " + fishingItemName + " " + ChatColor.GREEN + total + "/" + required);
        lore.add(ProgressBar.getProgressBar(progress));
        lore.add("");

        List<String> rewards = entry.getRewards();
        int rewardsSize = rewards.size();
        boolean hasRewards = rewardsSize > 0;

        if(hasRewards) {

            lore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "REWARDS");

            for(int i = 0; i < rewardsSize; i++) {
                lore.add(ChatColor.DARK_GRAY + "➡ " + ChatColor.WHITE + rewards.get(i));
            }

            lore.add("");

        }

        lore.add(ChatColor.DARK_GRAY + "Click to make a cool sound!");

        return lore;
    }

}
