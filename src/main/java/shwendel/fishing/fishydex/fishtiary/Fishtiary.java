package shwendel.fishing.fishydex.fishtiary;

import org.reflections.Reflections;
import shwendel.fishing.items.FishingItem;

import java.util.*;

public class Fishtiary {

    private static final Map<FishingItem.ID, FishtiarySet> FISHTIARY_SETS = new HashMap<>();

    static {

        Reflections reflections = new Reflections("shwendel.fishing.fishydex.fishtiary.sets");
        Set<Class<? extends FishtiarySet>> sets = reflections.getSubTypesOf(FishtiarySet.class);

        for(Class<? extends FishtiarySet> clazz : sets) {

            try {

                FishtiarySet set = clazz.getConstructor().newInstance();

                set.addEntries();

                FISHTIARY_SETS.put(set.getID(), set);

            } catch(Exception e) {
                e.printStackTrace();
            }

        }

    }

    public static FishtiarySet getSet(FishingItem.ID id) {
        return FISHTIARY_SETS.get(id);
    }

    public static FishtiarySet[] getSets() {
        return FISHTIARY_SETS.values().toArray(new FishtiarySet[0]);
    }

    // not sure if this should be logic within the class or not. Going to try it inside for now.
    // TODO: should have a more meaningful sort, for now is just by ID order
    public static FishtiarySet[] getSetsOrdered() {
        FishtiarySet[] sets = getSets();
        Arrays.sort(getSets(), Comparator.comparing(FishtiarySet::getID));
        return sets;
    }

}
