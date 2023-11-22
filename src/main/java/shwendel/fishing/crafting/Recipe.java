package shwendel.fishing.crafting;

import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class Recipe {

    public static HashMap<String, Recipe> recipes = new HashMap<>();

    static {
        Set<Class<? extends Recipe>> classList = new Reflections("shwendel.fishing.crafting.recipes").getSubTypesOf(Recipe.class);
        for(Class<? extends Recipe> clazz : classList) {
            try {
                Recipe recipe = clazz.getConstructor().newInstance();
                recipes.put(recipe.getResult().getName(), recipe);
            } catch(Exception exception) {}
        }

    }

    public abstract Map<Integer, FishingItem> getRecipe();

    public abstract FishingItem getResult();

}
