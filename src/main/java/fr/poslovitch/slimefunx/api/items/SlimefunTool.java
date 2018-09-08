package fr.poslovitch.slimefunx.api.items;

import fr.poslovitch.slimefunx.api.categories.Category;
import org.bukkit.inventory.ItemStack;

public class SlimefunTool extends SlimefunItem {

    public SlimefunTool(String id, Category category, ItemStack item, String[][] recipePattern, int researchCost) {
        super(id, category, item, recipePattern, researchCost);
    }
}
