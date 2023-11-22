package shwendel.fishing.crafting;

import java.util.Map;

public abstract class ShapedRecipe extends Recipe {

    public static FishingItem getItemFromRecipe(Map<Integer, FishingItem> recipe) {
        String recipeShape = "";

        for(int i = 0; i < 9; i++) {
            recipeShape += recipe.containsKey(i) ? "[" + recipe.get(i).getName() + "]" : "|";
        }

        if(recipeShape.isEmpty() || !recipeShape.contains("[") || !recipeShape.contains("]")) {
            return null;
        }

        recipeShape = recipeShape.substring(recipeShape.indexOf("["));
        recipeShape = recipeShape.substring(0, 1 + recipeShape.lastIndexOf("]"));

        for(Recipe compare : recipes.values()) {
            if(compare instanceof ShapedRecipe) {
                ShapedRecipe shapedCompare = (ShapedRecipe) compare;
                String compareShape = shapedCompare.getShape();
                if(recipeShape.equals(compareShape)) {
                    return shapedCompare.getResult();
                }
            }
        }

        return null;
    }

    public String getShape(Map<Integer, FishingItem> recipe) {
        String shape = "";

        for(int i = 0; i < 9; i++) {
            shape += recipe.containsKey(i) ? "[" + recipe.get(i).getName() + "]" : "|";
        }

        // Turn the recipe into a "shape". | represents air while anything else is [X]. X represents item name
        shape = shape.substring(shape.indexOf("["));
        shape = shape.substring(0, 1 + shape.lastIndexOf("]"));

        return shape;
    }

    public String getShape() {
        return getShape(getRecipe());
    }

}
