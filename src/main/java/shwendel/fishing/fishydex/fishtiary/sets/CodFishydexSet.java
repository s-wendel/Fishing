package shwendel.fishing.fishydex.fishtiary.sets;

import shwendel.fishing.fishydex.fishtiary.FishtiarySet;
import shwendel.fishing.items.FishingItem;

import java.util.function.Consumer;

public class CodFishydexSet extends FishtiarySet {

    public CodFishydexSet() {
        super(
                FishingItem.ID.COD,
                new int[]{
                        5,
                        15,
                        25,
                        45,
                        75,
                        125
                },
                new String[][]{
                        {"1x Men"},
                        {"2x Men"},
                        {"2x Men"},
                        {"2x Men"},
                        {"2x Men"},
                        {"2x Men","1x Burly Man"}
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
