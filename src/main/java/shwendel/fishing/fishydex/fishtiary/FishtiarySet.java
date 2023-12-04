package shwendel.fishing.fishydex.fishtiary;

import mc.obliviate.inventory.Icon;
import shwendel.fishing.items.FishingItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class FishtiarySet {

    private List<FishtiaryEntry> entries = new ArrayList<>();

    /**
     * Return the item used in the FishtiarySet
     * @return The item used in the FishtiarySet
     */
    public abstract FishingItem getItem();

    /**
     * Inherited classes must implement, intended to be used to add multiple entries from one method
     */
    public abstract void addEntries();

    /**
     * Helper method to add entries easily through inherited classes
     * @param entry The FishtiaryEntry being added
     */
    protected void addEntry(FishtiaryEntry entry) {
        entries.add(entry);
    }

    public FishtiaryEntry getEntry(int index) {
        return index >= entries.size() ? null : entries.get(index);
    }

    public Icon getIcon() {
        //TODO iconf or top of  yeyeyeyyeyyye
        return null;
    }

}
