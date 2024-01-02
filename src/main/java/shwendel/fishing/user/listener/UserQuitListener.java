package shwendel.fishing.user.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import shwendel.fishing.user.UserManager;

public class UserQuitListener implements Listener {

    @EventHandler
    public void playerQuit(PlayerQuitEvent event) {

        UserManager.saveUser(event.getPlayer());

    }

}
