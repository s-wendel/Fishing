package shwendel.fishing.crafting.recipes;

import shwendel.fishing.crafting.ShapelessRecipe;
import shwendel.fishing.islands.Region;
import shwendel.fishing.items.FishingItem;
import shwendel.fishing.items.materials.dirt.Dirt;

import java.util.HashMap;
import java.util.Map;

public class DudeRecipe extends ShapelessRecipe {

    @Override
    public Map<Integer, FishingItem> getRecipe() {

        Map<Integer, FishingItem> recipe = new HashMap<>();

        recipe.put(0, new Dirt(Region.CORAL_COAST));
        recipe.put(1, new Dirt(Region.CORAL_COAST));
        recipe.put(2, new Dirt(Region.CORAL_COAST));
        recipe.put(3, new Dirt(Region.CORAL_COAST));

        return recipe;
    }

    @Override
    public FishingItem getResult() {
        return new Dirt(Region.CORAL_COAST);
    }

}
