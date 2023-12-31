package shwendel.fishing.fishydex.fishtiary.gui;

import mc.obliviate.inventory.Gui;
import mc.obliviate.inventory.Icon;
import mc.obliviate.inventory.pagination.PaginationManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.jetbrains.annotations.NotNull;
import shwendel.fishing.fishydex.FishydexGUI;
import shwendel.fishing.fishydex.fishtiary.Fishtiary;
import shwendel.fishing.fishydex.fishtiary.FishtiarySet;
import shwendel.fishing.user.User;
import shwendel.fishing.user.UserManager;

import static shwendel.fishing.util.GuiUtil.*;

public class FishtiaryGUI extends Gui {
    private final PaginationManager pagination = new PaginationManager(this);
    private final User user;

    public FishtiaryGUI(@NotNull Player player) {
        super(player, "fishtiary", "Fishtiary", 6);
        user = UserManager.getUser(player);
        pagination.registerPageSlotsBetween(10, 16);
        pagination.registerPageSlotsBetween(19, 25);
        pagination.registerPageSlotsBetween(28, 34);
        pagination.registerPageSlotsBetween(37, 43);
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        border(this);

        FishtiarySet[] sets = Fishtiary.getSetsOrdered(); // TODO: actually order them? this doesn't seem to work at the moment
        for (FishtiarySet set : sets) {

            if (user.getFishCaught(set.getID()) > 0) {
                pagination.addItem(iconFromSet(set));
            }
        }
        pagination.update();

        addItem(49, backButton(e -> {
            closeSound(player);
            new FishydexGUI(player).open();
        }));
    }

    private Icon iconFromSet(FishtiarySet set) {
        Icon icon = new Icon(set.getItem().getItemStack());
        int fishCaught = UserManager.getUser(player).getFishCaught(set.getID());

        icon.setLore(ChatColor.GRAY + "Total collected: " + fishCaught, "Put info about the fish here!");

        icon.onClick(event -> {
            openSound(player);
            new FishtiarySetGUI(player, set).open();
        });

        return icon;
    }
}
