package shwendel.fishing.fishydex.fishtiary.sets;

import shwendel.fishing.fishydex.fishtiary.FishtiarySet;
import shwendel.fishing.items.FishingItem;

import java.util.function.Consumer;

public class SalmonFishydexSet extends FishtiarySet {

    public SalmonFishydexSet() {
        super(
                FishingItem.ID.SALMON,
                new int[]{
                        5,
                        15,
                        25,
                        45,
                        75,
                        125
                },
                new String[][]{
                        {"10x Men"},
                        {"2x Men"},
                        {"2x Men"},
                        {"2x Men"},
                        {"2x Men"},
                        {"2x Men","52,000,000x Burly Men"}
                },
                new Consumer[]{
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                }
        );
    }
}
