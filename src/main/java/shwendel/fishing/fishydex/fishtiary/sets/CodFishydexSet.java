package shwendel.fishing.fishydex.fishtiary.sets;

import org.bukkit.ChatColor;
import shwendel.fishing.fishydex.fishtiary.FishtiaryEntry;
import shwendel.fishing.fishydex.fishtiary.FishtiarySet;
import shwendel.fishing.items.FishingItem;

import java.util.Arrays;

public class CodFishydexSet extends FishtiarySet {

    @Override
    public FishingItem getItem() {
        return FishingItem.COD;
    }

    @Override
    public void addEntries() {

        FishingItem item = getItem();

        addEntry(new FishtiaryEntry(
                item,
                5,
                Arrays.asList(
                        "1x Men"
                ),
                user -> {
                    // Consumer
                }
        ));

        addEntry(new FishtiaryEntry(
                item,
                15,
                Arrays.asList(
                        "2x Men"
                ),
                user -> {
                    // Consumer, Make sure to implement the 2x Men.
                }
        ));

        addEntry(new FishtiaryEntry(
                item,
                25,
                Arrays.asList(
                        "2x Men"
                ),
                user -> {
                    // Consumer, Make sure to implement the 2x Men.
                }
        ));

        addEntry(new FishtiaryEntry(
                item,
                45,
                Arrays.asList(
                        "2x Men"
                ),
                user -> {
                    // Consumer, Make sure to implement the 2x Men.
                }
        ));

        addEntry(new FishtiaryEntry(
                item,
                75,
                Arrays.asList(
                        "2x Men"
                ),
                user -> {
                    // Consumer, Make sure to implement the 2x Men.
                }
        ));

        addEntry(new FishtiaryEntry(
                item,
                125,
                Arrays.asList(
                        "2x Men"
                ),
                user -> {
                    // Consumer, Make sure to implement the 2x Men.
                }
        ));



    }

}
