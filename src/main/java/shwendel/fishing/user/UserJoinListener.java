package shwendel.fishing.user;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import shwendel.fishing.items.FishingEquipment;

import java.util.HashMap;
import java.util.Map;

public class UserJoinListener implements Listener {

    @EventHandler
    public void userJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        User user = new User(player, 0, System.currentTimeMillis());

        for(ItemStack armor : player.getEquipment().getArmorContents()) {
            if(FishingEquipment.isFishingEquipment(armor)) {
                user.addEquipment(FishingEquipment.fromItem(armor));
            }
        }

        UserManager.addUser(user);

    }

}
