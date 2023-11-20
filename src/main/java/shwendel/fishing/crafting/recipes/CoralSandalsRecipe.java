package shwendel.fishing.crafting.recipes;

import shwendel.fishing.crafting.ShapedRecipe;
import shwendel.fishing.islands.Region;
import shwendel.fishing.items.FishingItem;
import shwendel.fishing.items.armor.water.CoralSandals;
import shwendel.fishing.items.materials.water.CoralShard;

import java.util.HashMap;
import java.util.Map;

public class CoralSandalsRecipe extends ShapedRecipe {

    @Override
    public Map<Integer, FishingItem> getRecipe() {

        Map<Integer, FishingItem> recipe = new HashMap<>();

        recipe.put(0, new CoralShard(null));
        recipe.put(2, new CoralShard(null));
        recipe.put(3, new CoralShard(null));
        recipe.put(5, new CoralShard(null));

        return recipe;
    }

    @Override
    public FishingItem getResult() {
        return new CoralSandals(Region.CORAL_COAST, 1, System.currentTimeMillis());
    }

}
