package shwendel.fishing.crafting.recipes;

import shwendel.fishing.crafting.ShapedRecipe;
import shwendel.fishing.islands.Region;
import shwendel.fishing.items.armor.emerald.EmeraldHelmet;
import shwendel.fishing.items.materials.emerald.Emerald;

import java.util.HashMap;
import java.util.Map;

public class EmeraldHelmetRecipe extends ShapedRecipe {

    @Override
    public Map<Integer, FishingItem> getRecipe() {

        Map<Integer, FishingItem> recipe = new HashMap<>();

        recipe.put(0, new Emerald(null));
        recipe.put(1, new Emerald(null));
        recipe.put(2, new Emerald(null));
        recipe.put(3, new Emerald(null));
        recipe.put(5, new Emerald(null));

        return recipe;
    }

    @Override
    public FishingItem getResult() {
        return new EmeraldHelmet(Region.CORAL_COAST, 1, System.currentTimeMillis());
    }

}
