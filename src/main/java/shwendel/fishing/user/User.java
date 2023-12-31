package shwendel.fishing.user;

import org.bukkit.Bukkit;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import shwendel.fishing.items.FishingItem;

import java.util.*;

public class User {

    public enum FishingState {
        NONE,
        PARRY,
        CATCH
    }

    private UUID uuid;
    private int coins;
    private Map<Stat, Double> cache;
    //private Map<Stat, Map<FishingEquipment, Double>> stats;
    private FishHook currentFishHook;
    private long joinDate;
    private FishingState fishingState;
    // Fishtiary collection
    private HashMap<FishingItem.ID, Integer> fishCaught;

    public User(UUID uuid, int coins, long joinDate) {
        this.uuid = uuid;
        this.coins = coins;

        //stats = new HashMap<>();
        cache = new HashMap<>();

        /*for(Stat stat : Stat.values()) {
            Map<FishingEquipment, Double> values = new HashMap<>();

            stats.put(stat, values);
            cache.put(stat, stat.getBase());
        }*/

        this.joinDate = joinDate;

        // TODO: implement loading "fish caught" from DB
        fishCaught = new HashMap<>();
        fishCaught.put(FishingItem.ID.SALMON, 15);
        fishCaught.put(FishingItem.ID.COD, 7);

    }

    public User(Player player, int coins, long joinDate) {
        this(player.getUniqueId(), coins, joinDate);
    }

    public UUID getUUID() {
        return uuid;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    /*public Map<Stat, Map<FishingEquipment, Double>> getStats() {
        return stats;
    }*/

    public double getStatValue(Stat stat, boolean cacheValue) {

        if(cacheValue) {
            return cache.get(stat);
        }

        double value = stat.getBase();

        /*for(FishingItem item : stats.get(stat).keySet()) {
            value += stats.get(stat).get(item);
        }*/

        if(value < 0) {
            value = 0;
        }

        return value;
    }

    /*public FishingEquipment getEquipment(String name, Stat stat) {
        for(FishingEquipment equipment : stats.get(stat).keySet()) {
            if(equipment.getName().equalsIgnoreCase(name)) {
                return equipment;
            }
        }
        return null;
    }*/

    public void updateCache(Stat stat) {
        cache.put(stat, getStatValue(stat, false));
    }

    /*public void addEquipment(FishingEquipment item) {
        for(Stat stat : item.getStats().keySet()) {
            stats.get(stat).put(item, item.getStats().get(stat));
            updateCache(stat);
            //item.onEquip(user);
        }
    }

    public void removeEquipment(FishingEquipment item) {
        for(Stat stat : Stat.values()) {

            List<FishingEquipment> toRemove = new ArrayList<>(); // Avoid concurrent modification exception

            for(FishingEquipment equipment : stats.get(stat).keySet()) {
                if(equipment.getName().equalsIgnoreCase(item.getName())) {
                    toRemove.add(equipment);
                }
            }

            for(FishingEquipment equipment : toRemove) {
                stats.get(stat).remove(equipment);
            }
            updateCache(stat);
        }
    }*/

    public boolean hasHook() {
        return currentFishHook != null;
    }

    public FishHook getFishHook() {
        return currentFishHook;
    }

    public void setFishHook(FishHook fishHook) {
        this.currentFishHook = fishHook;
    }

    public long getJoinDate() {
        return joinDate;
    }

    public FishingState getFishingState() {
        return fishingState;
    }

    public void setFishingState(FishingState fishingState) {
        this.fishingState = fishingState;
    }

    public int getFishCaught(FishingItem.ID id) {
        if (fishCaught.containsKey(id)) {
            return fishCaught.get(id);
        }
        return 0;
    }
}