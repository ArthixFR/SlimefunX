package fr.horizons.slimefunx.item.ressources.dust;

import fr.horizons.slimefunx.base.Category;
import fr.horizons.slimefunx.item.SlimefunRessource;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.List;

public class CopperDust extends SlimefunRessource {
    public CopperDust(String id, Category category, ItemStack item, int textureId, @Nullable String name, @Nullable List<String> lore) {
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