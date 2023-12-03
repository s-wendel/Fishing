package shwendel.fishing.fishydex.fishtiary;

import org.reflections.Reflections;
import shwendel.fishing.items.FishingItem;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Fishtiary {

    public static final Map<FishingItem, FishtiarySet> FISHTIARY_SETS = new HashMap<>();

    static {

        Reflections reflections = new Reflections("shwendel.fishing.fishydex.fishtiary.sets");
        Set<Class<? extends FishtiarySet>> sets = reflections.getSubTypesOf(FishtiarySet.class);

        for(Class<? extends FishtiarySet> clazz : sets) {

            try {

                FishtiarySet set = clazz.getConstructor().newInstance();

                set.addEntries();

                FISHTIARY_SETS.put(set.getItem(), set);

            } catch(Exception exception) {}

        }

    }

}
