import shwendel.fishing.crafting.recipes.CoralSandalsRecipe;
import shwendel.fishing.crafting.recipes.EmeraldBootsRecipe;
import shwendel.fishing.islands.Region;
import shwendel.fishing.items.materials.dirt.Dirt;

import java.util.HashMap;
import java.util.Map;

public class RecipeTest {

    public static void main(String[] args) {
        CoralSandalsRecipe coralSandals = new CoralSandalsRecipe();
        EmeraldBootsRecipe emeraldBoots = new EmeraldBootsRecipe();

        Map<Integer, FishingItem> recipe = new HashMap<>();

        recipe.put(0, new Dirt(Region.CORAL_COAST));
        recipe.put(2, new Dirt(Region.CORAL_COAST));
        recipe.put(3, new Dirt(Region.CORAL_COAST));
        recipe.put(5, new Dirt(Region.CORAL_COAST));

        System.out.println("-=-=-=-=--= SHAEPS -=-=-=");
        System.out.println(coralSandals.getShape());
        System.out.println(emeraldBoots.getShape());

    }

}
