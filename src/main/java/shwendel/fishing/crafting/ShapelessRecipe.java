package shwendel.fishing.crafting;

import java.util.HashMap;
import java.util.Map;

/*
public abstract class ShapelessRecipe extends Recipe {

    public static FishingItem getItemFromRecipe(Map<Integer, FishingItem> recipe) {

        Map<String, Integer> recipeQuantity = new HashMap<>();

        for(FishingItem item : recipe.values()) {
            String name = item.getName();

            recipeQuantity.put(name, recipeQuantity.containsKey(name) ? recipeQuantity.get(name) + 1 : 1);
        }

        for(Recipe compare : recipes.values()) {
            if(compare instanceof ShapelessRecipe) {
                ShapelessRecipe shapelessCompare = (ShapelessRecipe) compare;
                Map<Integer, FishingItem> shapelessRecipe = shapelessCompare.getRecipe();
                Map<String, Integer> shapelessQuantity = new HashMap<>();

                for(FishingItem item : shapelessRecipe.values()) {
                    String name = item.getName();

                    shapelessQuantity.put(name, shapelessQuantity.containsKey(name) ? shapelessQuantity.get(name) + 1 : 1);
                }

                if(recipeQuantity.equals(shapelessQuantity)) {
                    return compare.getResult();
                }

            }
        }

        return null;
    }

}
*/
