package fr.poslovitch.slimefunx.api.items;

import fr.poslovitch.slimefunx.api.base.SlimefunObject;
import fr.poslovitch.slimefunx.api.categories.Category;
import fr.poslovitch.slimefunx.api.interfaces.Craftable;
import fr.poslovitch.slimefunx.api.interfaces.Registerable;
import fr.poslovitch.slimefunx.api.interfaces.Researchable;
import org.apache.commons.lang.Validate;
import org.bukkit.inventory.ItemStack;

public class SlimefunItem extends SlimefunObject implements Craftable, Researchable, Registerable {

    private final String[][] recipePattern;
    private final int researchCost;

    public SlimefunItem(String id, Category category, ItemStack item, String[][] recipePattern, int researchCost) {
        super(id, category, item);

        // Validation - make sure that none of the specified value is illegal.
        Validate.notEmpty(recipePattern, "Recipe pattern must not be empty nor null.");
        Validate.isTrue(recipePattern.length <= 6, "Recipe pattern y size must be less or equal to 6: ", recipePattern.length);
        Validate.isTrue(recipePattern[0].length <= 6, "Recipe pattern x size must be less or equal to 6: ", recipePattern[0].length);
        Validate.isTrue(researchCost > 0, "Research cost must be greater than 0: ", researchCost);
        // -------------------------------------------------------------------

        this.recipePattern = recipePattern;
        this.researchCost = researchCost;
    }

    @Override
    public String[][] getRecipePattern() {
        return recipePattern;
    }

    @Override
    public int getResearchCost() {
        return researchCost;
    }

    @Override
    public void register() {

    }
}
