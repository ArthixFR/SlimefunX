package fr.poslovitch.slimefunx.api.machines;

import fr.poslovitch.slimefunx.api.Category;
import fr.poslovitch.slimefunx.api.SlimefunItem;
import fr.poslovitch.slimefunx.api.recipes.Recipe;
import org.bukkit.inventory.ItemStack;

public class SlimefunMachine extends SlimefunItem {

    protected SlimefunMachine(String id, ItemStack item, Category category, Recipe recipe, int researchCost) {
        super(id, item, category, recipe, researchCost);
    }

    public static class Builder extends SlimefunItem.Builder {

        @Override
        public SlimefunItem build() {
            return super.build();
        }
    }
}
