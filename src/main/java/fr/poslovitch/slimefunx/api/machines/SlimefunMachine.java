package fr.poslovitch.slimefunx.api.machines;

import fr.poslovitch.slimefunx.api.categories.Category;
import fr.poslovitch.slimefunx.api.interfaces.Placeable;
import fr.poslovitch.slimefunx.api.items.SlimefunItem;
import org.bukkit.inventory.ItemStack;

public class SlimefunMachine extends SlimefunItem implements Placeable {

    public SlimefunMachine(String id, Category category, ItemStack item, String[][] recipePattern, int researchCost) {
        super(id, category, item, recipePattern, researchCost);
    }
}
