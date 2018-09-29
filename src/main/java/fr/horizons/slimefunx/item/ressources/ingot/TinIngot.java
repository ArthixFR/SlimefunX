package fr.horizons.slimefunx.item.ressources.ingot;

import fr.horizons.slimefunx.base.Category;
import fr.horizons.slimefunx.item.SlimefunResource;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.List;

public class TinIngot extends SlimefunResource {
    public TinIngot(String id, Category category, ItemStack item, int textureId, @Nullable String name, @Nullable List<String> lore) {
        super(id, category, item, textureId, name, lore);
    }

    @Override
    public String[][] getRecipePattern() {
        return new String[0][];
    }

    @Override
    public int getResearchCost() {
        return 0;
    }
}
