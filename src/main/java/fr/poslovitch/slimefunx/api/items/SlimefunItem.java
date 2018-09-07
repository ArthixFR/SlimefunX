package fr.poslovitch.slimefunx.api.items;

import fr.poslovitch.slimefunx.api.base.SlimefunObject;
import fr.poslovitch.slimefunx.api.categories.Category;
import fr.poslovitch.slimefunx.api.interfaces.Craftable;
import fr.poslovitch.slimefunx.api.interfaces.Researchable;
import org.bukkit.inventory.ItemStack;

public class SlimefunItem extends SlimefunObject implements Craftable, Researchable {

    private int researchCost;

    public SlimefunItem(String id, Category category, ItemStack item, int researchCost) {
        super(id, category, item);
        this.researchCost = researchCost;
    }

    @Override
    public int getResearchCost() {
        return researchCost;
    }
}
