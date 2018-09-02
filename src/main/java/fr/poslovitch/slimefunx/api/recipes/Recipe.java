package fr.poslovitch.slimefunx.api.recipes;

public class Recipe {

    private final Type type;
    private final String[][] recipe;

    protected Recipe(Type type, String[][] recipe) {
        this.type = type;
        this.recipe = recipe;
    }

    public Type getType() {
        return type;
    }

    public String[][] getRecipe() {
        return recipe;
    }

    public static class Builder {
        private Type type;
        private String[][] recipe;
        private String recipeHolderId;

        public Builder type(Type type){
            this.type = type;
            return this;
        }

        public Builder recipe(String[][] recipe){
            this.recipe = recipe;
            return this;
        }

        public Builder recipeHolderId(String recipeHolderId) {
            this.recipeHolderId = recipeHolderId;
            return this;
        }

        public Recipe build() {
            switch(type) {
                case MACHINE:
                    return new MachineRecipe(recipeHolderId, recipe);
                case MULTIBLOCK:
                    return new MultiBlockRecipe(recipe);
                case GADGET:
                    return new GadgetRecipe(recipe);
                default:
                    return new Recipe(type, recipe);
            }
        }
    }

    public enum Type {
        MACHINE,
        MULTIBLOCK,
        GADGET
    }
}
