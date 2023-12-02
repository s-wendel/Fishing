package shwendel.fishing.fishydex;

import shwendel.fishing.fishydex.fishtiary.FishydexSet;
import shwendel.fishing.items.FishingItem;

import java.util.HashMap;
import java.util.Map;

public class Fishydex {

    private static Map<FishingItem, FishydexSet> fishydexSets = new HashMap<>();

    static {
        reload();
    }

    public static void reload() {



    }

}
