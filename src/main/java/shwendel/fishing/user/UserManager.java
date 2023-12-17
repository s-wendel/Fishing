package shwendel.fishing.user;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserManager {

    public static Map<Player, User> users = new HashMap<>();

    public static void addUser(User user) {
        users.put(user.getPlayer(), user);
    }

    public static void removeUser(Player player) {
        users.remove(player);
    }

    public static User getUser(Player player) {
        return users.get(player);
    }

}
