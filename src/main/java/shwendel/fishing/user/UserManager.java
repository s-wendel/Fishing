package shwendel.fishing.user;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserManager {

    private static Map<UUID, User> users = new HashMap<>();

    public static User getUser(User user) {

        UUID uuid = user.getPlayer().getUniqueId();;

        if(!users.containsKey(uuid)) {
            //users.put(uuid, new User());
        }

        return users.get(uuid);

    }

    public static void removeUser(Player player) {
        users.remove(player);
    }

}
