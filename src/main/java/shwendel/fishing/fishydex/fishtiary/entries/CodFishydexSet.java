package shwendel.fishing.fishydex.fishtiary.entries;

import shwendel.fishing.items.FishingItem;

import java.util.Arrays;

public class CodFishydexSet extends FishydexSet {

    @Override
    public FishingItem getItem() {
        return FishingItem.COD;
    }

    @Override
    public void addEntries() {

        addEntry(new FishydexEntry(
                getItem(),
                5,
                Arrays.asList(
                        "Example Stat"
                ),
                user -> {
                    // Give the stat to the player
                }
        ));

    }

}
