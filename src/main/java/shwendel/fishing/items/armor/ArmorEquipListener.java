package shwendel.fishing.items.armor;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import shwendel.fishing.user.User;
import shwendel.fishing.user.UserManager;

public class ArmorEquipListener implements Listener {

    @EventHandler
    public void armorSwap(PlayerArmorChangeEvent event) {

        Player player = event.getPlayer();
        ItemStack newItem = event.getNewItem();
        ItemStack oldItem = event.getOldItem();

        User user = UserManager.getUser(player);

        FishingArmor newEquipment = FishingArmor.fromItem(newItem);
        FishingArmor oldEquipment = FishingArmor.fromItem(oldItem);

        if(newEquipment != null) {
            user.addEquipment(newEquipment);
        }

        if(oldEquipment != null) {
            user.removeEquipment(oldEquipment);
        }

    }

}
