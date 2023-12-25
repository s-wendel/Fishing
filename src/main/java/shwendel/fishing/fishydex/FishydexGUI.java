package shwendel.fishing.fishydex;

import mc.obliviate.inventory.Gui;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.jetbrains.annotations.NotNull;
import shwendel.fishing.fishydex.fishtiary.gui.FishtiaryGUI;

import static shwendel.fishing.util.GuiUtil.*;

public class FishydexGUI extends Gui {
    public FishydexGUI(@NotNull Player player) {
        super(player, "FISHYDEX", "Fishydex", 3);
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        border(this);

        // buttons
        addItem(10, simpleButton(Material.COD, "Fishtiary", "A collection of fishy knowledge", e -> {
            openSound(player);
            new FishtiaryGUI(player).open();
        }));
        addItem(11, simpleButton(Material.OAK_BOAT, "Boat", "Manage your boat", e -> {
            openSound(player);
        }));
    }
}
