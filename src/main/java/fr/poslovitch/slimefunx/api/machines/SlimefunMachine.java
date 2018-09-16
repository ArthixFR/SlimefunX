package fr.poslovitch.slimefunx.api.machines;

import fr.poslovitch.slimefunx.api.categories.Category;
import fr.poslovitch.slimefunx.api.interfaces.CraftingAgent;
import fr.poslovitch.slimefunx.api.interfaces.Interactable;
import fr.poslovitch.slimefunx.api.interfaces.Placeable;
import fr.poslovitch.slimefunx.api.items.SlimefunItem;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class SlimefunMachine extends SlimefunItem implements Placeable, Interactable, CraftingAgent {

    private final Listener interactionListener;

    public SlimefunMachine(String id, Category category, ItemStack item, String[][] recipePattern, int researchCost) {
        super(id, category, item, recipePattern, researchCost);

        this.interactionListener = new Listener(){};
    }

    @Override
    public Listener getInteractionListener() {
        return interactionListener;
    }

    @Override
    public void processRecipe(String[][] recipe) {
        // Do nothing for now
    }
}
