package shwendel.fishing.fishydex.fishtiary;

import mc.obliviate.inventory.Icon;
import shwendel.fishing.items.FishingItem;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class FishtiarySet {
    private final List<FishtiaryEntry> entries = new ArrayList<>();
    private final FishingItem.ID id;
    private final int[] required;
    private final String[][] rewards;
    private final Consumer[] consumers;

    protected FishtiarySet(FishingItem.ID id, int[] required, String[][] rewards, Consumer[] consumers) {
        this.id = id;
        this.required = required;
        this.rewards = rewards;
        this.consumers = consumers;
    }

    public FishingItem.ID getID() {
        return id;
    }

    public FishingItem getItem() {
        return FishingItem.fromID(id);
    }

    /**
     * Inherited classes must implement, intended to be used to add multiple entries from one method
     */
    protected void addEntries() {
        for (int i = 0; i < required.length; i++) {
            entries.add(new FishtiaryEntry(required[i], rewards[i], consumers[i]));
        }
    }

//    /**
//     * Helper method to add entries easily through inherited classes
//     * @param entry The FishtiaryEntry being added
//     */
//    protected void addEntry(FishtiaryEntry entry) {
//        entries.add(entry);
//    }

    public FishtiaryEntry getEntry(int index) {
        return entries.get(index);
    }

    public int size() {
        return entries.size();
    }

}
