package shwendel.fishing.items;

import org.bukkit.inventory.ItemStack;
import shwendel.fishing.user.Stat;

import java.util.List;
import java.util.Map;

// I didn't know what to name it I'm sorry
public class FishingItemMemory {

    private FishingItem id;
    private ItemStack item; // The base material
    private String name; // Name displayed on item
    private long sellValue; // Sell value, -1 if unsellable
    private List<String> description;
    //private FishingAbility ability;
    //private List<String> abilityDescription;
    private Map<Stat, Double> stats;

    public FishingItemMemory(FishingItem id, ItemStack item, String name, long sellValue, List<String> description, Map<Stat, Double> stats) {
        this.id = id;
        this.item = item;
        this.name = name;
        this.sellValue = sellValue;
        this.description = description;
        this.stats = stats;
    }

    public FishingItem getID() {
        return id;
    }

    /**
     * Get the base item without any other attributes
     * @return The base item without any other attributes
     */
    public ItemStack getBaseItem() {
        return item;
    }

    public String getName() {
        return name;
    }

    public long getSellValue() {
        return sellValue;
    }

    public boolean isSellable() {
        return sellValue != -1;
    }

    public List<String> getDescription() {
        return description;
    }

    public Map<Stat, Double> getStats() {
        return stats;
    }

}
