package shwendel.fishing.database;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import shwendel.fishing.Fishing;

import java.sql.*;

public class Database {

    public static String PLAYERS_TABLE = "players";

    private static Connection connection;
    private static final String HOST;
    private static final String PORT;
    private static final String DATABASE;
    private static final String USERNAME;
    private static final String PASSWORD;
    private static final String DATABASE_SECTION = "database";

    static {

        FileConfiguration config = Fishing.getInstance().getConfig();

        HOST = config.getString(DATABASE_SECTION + ".host");
        PORT = config.getString(DATABASE_SECTION + ".port");
        DATABASE = config.getString(DATABASE_SECTION + ".database");
        USERNAME = config.getString(DATABASE_SECTION + ".username");
        PASSWORD = config.getString(DATABASE_SECTION + ".password");

        final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;

        Statement statement;

        try {

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            statement = connection.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS players (uuid varchar(36) primary key, coins int, join_date long)";

            statement.execute(sql);
            statement.close();

        } catch(SQLException exception) {

            System.out.println("Database was not properly setup!");

            exception.printStackTrace();

            Bukkit.getPluginManager().disablePlugin(Fishing.getInstance());

        }

    }

    public static Connection getConnection() {
        return connection;
    }

    /**public void saveIsland(Island island) {

        String members = "";

        for(UUID member : island.getMembers()) {
            members += member + ",";
        }

        members = members.substring(0, members.length() - 1);

        for(UUID member : island.getMembers()) {

            try {

                Player player = Bukkit.getPlayer(member);

                if(player == null) {
                    PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO islands (uuid, x, y, members, welcome_message, warpable) VALUES (?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE  = ?, y = ?, members = ?, welcome_message = ?, warpable = ?");

                    preparedStatement.setString(1, member.toString());
                    preparedStatement.setInt(2, island.getX());
                    preparedStatement.setInt(3, island.getY());
                    preparedStatement.setString(4, members);
                    preparedStatement.setString(5, island.getWelcomeMessage());
                    preparedStatement.setBoolean(6, island.isWarpable());

                    preparedStatement.setInt(7, island.getX());
                    preparedStatement.setInt(8, island.getY());
                    preparedStatement.setString(9, members);
                    preparedStatement.setString(10, island.getWelcomeMessage());
                    preparedStatement.setBoolean(11, island.isWarpable());

                    preparedStatement.executeUpdate();
                    preparedStatement.close();


                } else {
                    User user = UserManager.getUser(player);

                    PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO islands (uuid, x, y, members, welcome_message, warpable, money, blocks_broken, blocks_placed) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE x = ?, y = ?, members = ?, welcome_message = ?, warpable = ?, money = ?, blocks_broken = ?, blocks_placed = ?");

                    preparedStatement.setString(1, member.toString());
                    preparedStatement.setInt(2, island.getX());
                    preparedStatement.setInt(3, island.getY());
                    preparedStatement.setString(4, members);
                    preparedStatement.setString(5, island.getWelcomeMessage());
                    preparedStatement.setBoolean(6, island.isWarpable());
                    preparedStatement.setDouble(7, user.getMoney());
                    preparedStatement.setInt(8, user.getBlocksBroken());
                    preparedStatement.setInt(9, user.getBlocksPlaced());

                    preparedStatement.setInt(10, island.getX());
                    preparedStatement.setInt(11, island.getY());
                    preparedStatement.setString(12, members);
                    preparedStatement.setString(13, island.getWelcomeMessage());
                    preparedStatement.setBoolean(14, island.isWarpable());

                    preparedStatement.setDouble(15, user.getMoney());
                    preparedStatement.setInt(16, user.getBlocksBroken());
                    preparedStatement.setInt(17, user.getBlocksPlaced());

                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                }

            } catch(Exception exception) {
                exception.printStackTrace();
            }

        }

    }

    public Island getIsland(UUID uuid) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM islands WHERE uuid = ?");
        preparedStatement.setString(1, uuid.toString());

        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()) {
            int x = resultSet.getInt("x");
            int y = resultSet.getInt("y");

            LinkedList<UUID> members = new LinkedList<>();

            String[] split = resultSet.getString("members").split(",");

            for (String member : split) {
                members.add(UUID.fromString(member));
            }


            String welcomeMessage = resultSet.getString("welcome_message");

            boolean warpable = resultSet.getBoolean("warpable");

            preparedStatement.close();

            return new Island(members, x, y, welcomeMessage, warpable);
        }

        preparedStatement.close();

        return null;
    }

    public User getUser(UUID uuid) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM islands WHERE uuid = ?");
            preparedStatement.setString(1, uuid.toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                double money = resultSet.getDouble("money");
                int blocksBroken = resultSet.getInt("blocks_broken");
                int blocksPlaced = resultSet.getInt("blocks_placed");

                preparedStatement.close();

                return new User(Bukkit.getPlayer(uuid), money, blocksBroken, blocksPlaced);
            }

            preparedStatement.close();
        } catch (SQLException exception) {
            return null;
        }

        return null;
    }

    public void deleteIsland(Island island) throws SQLException {

        for(UUID member : island.getMembers()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM islands WHERE uuid = ?");
            preparedStatement.setString(1, member.toString());

            preparedStatement.executeUpdate();
            preparedStatement.close();

        }

    }*/

}
