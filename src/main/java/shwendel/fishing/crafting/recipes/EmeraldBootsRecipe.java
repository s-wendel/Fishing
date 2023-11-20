package shwendel.fishing.crafting.recipes;

import shwendel.fishing.crafting.ShapedRecipe;
import shwendel.fishing.islands.Region;
import shwendel.fishing.items.FishingItem;
import shwendel.fishing.items.armor.emerald.EmeraldBoots;
import shwendel.fishing.items.materials.emerald.CompactEmerald;
import shwendel.fishing.items.materials.emerald.Emerald;

import java.util.HashMap;
import java.util.Map;

public class EmeraldBootsRecipe extends ShapedRecipe {

    @Override
    public Map<Integer, FishingItem> getRecipe() {

        Map<Integer, FishingItem> recipe = new HashMap<>();

        recipe.put(0, new CompactEmerald(null));
        recipe.put(2, new CompactEmerald(null));
        recipe.put(3, new CompactEmerald(null));
        recipe.put(5, new CompactEmerald(null));

        return recipe;
    }

    @Override
    public FishingItem getResult() {
        return new EmeraldBoots(Region.CORAL_COAST, 1, System.currentTimeMillis());
    }

}
