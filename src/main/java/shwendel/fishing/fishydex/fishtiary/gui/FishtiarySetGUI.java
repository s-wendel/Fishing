package shwendel.fishing.fishydex.fishtiary.gui;

import mc.obliviate.inventory.Gui;
import mc.obliviate.inventory.Icon;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;
import shwendel.fishing.fishydex.fishtiary.FishtiaryEntry;
import shwendel.fishing.fishydex.fishtiary.FishtiarySet;
import shwendel.fishing.items.FishingItem;
import shwendel.fishing.util.ProgressBar;

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

    private final FishtiarySet set;

    public FishtiarySetGUI(Player player, FishtiarySet set) {
        super(
                player,
                "fishtiary-" + set.getID(),
                "Fishtiary ➡ " + set.getItem().getName(),
                6 // number of rows
        );
        this.set = set;
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {

        FishingItem fishingItem = set.getItem();
        String fishingItemName = fishingItem.getName();

        int total = 7; // TODO: The amount the player has collected

        for(int i = 0; i < 54; i++) { // iterate over each inv slot

            int snakeIndex = getSnakeIndex(i);

            if(snakeIndex >= 0) {

                Icon icon;

                if (i >= set.size()) {
                    icon = DOES_NOT_EXIST;
                } else {
                    FishtiaryEntry entry = set.getEntry(snakeIndex);
                    boolean hasEnough = entry.getRequired() <= total;
                    Material mat = hasEnough ? Material.LIME_STAINED_GLASS_PANE : Material.RED_STAINED_GLASS_PANE;

                    icon = new Icon(mat)
                            .setName((hasEnough ? ChatColor.GREEN : ChatColor.RED) + fishingItemName + " " + (snakeIndex + 1))
                            .setLore(getEntryLore(entry, total));
                }

                addItem(i, icon);
                continue;
            }

            switch(i) {

                case 4:
                    addItem(i, new Icon(fishingItem.getItem())
                            .setName("this is fish :)")
                            .setLore("put some lore here or something, lol!")
                    );
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

        String fishingItemName = set.getItem().getName();

        int required = entry.getRequired();
        double progress = (double) total / required;

        List<String> lore = new ArrayList<>();

        lore.add("");
        lore.add(ChatColor.WHITE + "Collect " + fishingItemName + " " + ChatColor.GREEN + total + "/" + required);
        lore.add(ProgressBar.getProgressBar(progress));
        lore.add("");

        String[] rewards = entry.getRewards();
        int rewardsSize = rewards.length;

        if(rewardsSize > 0) {

            lore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "REWARDS");

            for(int i = 0; i < rewardsSize; i++) {
                lore.add(ChatColor.DARK_GRAY + "➡ " + ChatColor.WHITE + rewards[i]);
            }

            lore.add("");

        }

        lore.add(ChatColor.DARK_GRAY + "Click to make a cool sound!");

        return lore;
    }

}
