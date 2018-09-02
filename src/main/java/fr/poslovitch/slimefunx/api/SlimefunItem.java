package fr.poslovitch.slimefunx.api;

import fr.poslovitch.slimefunx.api.recipes.Recipe;
import org.bukkit.inventory.ItemStack;

public class SlimefunItem {

    private final String id;
    private final ItemStack item;
    private final Category category;

    private final Recipe recipe;

    private final int researchCost;

    protected SlimefunItem(String id, ItemStack item, Category category, Recipe recipe, int researchCost){
        this.id = id;
        this.item = item;
        this.category = category;
        this.recipe = recipe;
        this.researchCost = researchCost;
    }

    public String getId() {
        return id;
    }

    public ItemStack getItem() {
        return item;
    }

    public Category getCategory() {
        return category;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public int getResearchCost() {
        return researchCost;
    }

    public static class Builder {
        private String id;
        private ItemStack item;
        private Category category;

        private Recipe recipe;

        private int researchCost;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder item(ItemStack item) {
            this.item = item;
            return this;
        }

        public Builder category(Category category) {
            this.category = category;
            return this;
        }

        public Builder recipe(Recipe recipe) {
            this.recipe = recipe;
            return this;
        }

        public Builder researchCost(int researchCost) {
            this.researchCost = researchCost;
            return this;
        }

        public SlimefunItem build() {
            return new SlimefunItem(id, item, category, recipe, researchCost);
        }
    }
}
