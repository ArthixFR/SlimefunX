package fr.poslovitch.slimefunx.api.items;

import fr.poslovitch.slimefunx.api.categories.Category;
import fr.poslovitch.slimefunx.api.interfaces.Interactable;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class SlimefunTool extends SlimefunItem implements Interactable {

    private final Listener interactionListener;

    public SlimefunTool(String id, Category category, ItemStack item, String[][] recipePattern, int researchCost, Listener interactionListener) {
        super(id, category, item, recipePattern, researchCost);

        this.interactionListener = interactionListener;
    }

    @Override
    public Listener getInteractionListener() {
        return interactionListener;
    }
}
