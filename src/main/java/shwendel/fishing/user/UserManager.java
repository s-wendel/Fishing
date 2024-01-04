package shwendel.fishing.user;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import shwendel.fishing.database.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserManager {

    private static final Map<UUID, User> USERS = new HashMap<>();

    public static User getUser(Player player) {
        return getUser(player.getUniqueId());
    }

    public static User getUser(UUID uuid) {
        //System.out.println("User now : " + USERS.getOrDefault(uuid, null));
        //return USERS.getOrDefault(uuid, loadUser(uuid));
        return USERS.containsKey(uuid) ? USERS.get(uuid) : loadUser(uuid);
    }

    /**
     * Saves a user into the database
     * @param player The player
     */
    public static void saveUser(Player player) {
        saveUser(UserManager.getUser(player));
    }

    /**
     * Saves a user into the database
     * @param user The user
     */
    public static void saveUser(User user) {

        try {

            // Could make a method to update for new values we decide ? Maybn
            PreparedStatement preparedStatement = Database.getConnection().prepareStatement("INSERT INTO " + Database.PLAYERS_TABLE + " (uuid, coins, join_date) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE coins = VALUES(coins), join_date = VALUES(join_date)");

            preparedStatement.setString(1, user.getUUID().toString());
            preparedStatement.setLong(2, user.getCoins());
            preparedStatement.setLong(3, user.getJoinDate());

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch(SQLException exception) {

            Bukkit.getLogger().warning("Failed to save user " + user.getUUID());

            exception.printStackTrace();

        }

    }

    /**
     * Load a user from the database into memory
     * @param uuid The uuid of the player
     * @return The user
     */
    private static User loadUser(UUID uuid) {

        // Default
        User user = new User(
                uuid,
                0,
                System.currentTimeMillis()
        );

        try {

            PreparedStatement preparedStatement = Database.getConnection().prepareStatement("SELECT * FROM " + Database.PLAYERS_TABLE + " WHERE uuid = ?");

            preparedStatement.setString(1, uuid.toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {

                // Not an expert in databases, I know practically nothing. I've heard this is bad practice ? I'm not sure what else to do though

                long coins = resultSet.getLong("coins");
                long joinDate = resultSet.getLong("join_date");

                preparedStatement.close();

                user = new User(
                        uuid,
                        coins,
                        joinDate
                );

            }

            //preparedStatement.close();

        } catch(SQLException exception) {

            Bukkit.getLogger().warning("Failed to load user " + uuid);

            exception.printStackTrace();

        }

        USERS.put(uuid, user);

        return user;
    }

}
