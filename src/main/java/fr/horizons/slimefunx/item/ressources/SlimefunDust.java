package fr.horizons.slimefunx.item.ressources;

import com.google.common.collect.Table;
import fr.horizons.slimefunx.item.SlimefunResource;
import fr.horizons.slimefunx.util.CraftingType;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.List;

public class SlimefunDust extends SlimefunResource {

    public SlimefunDust(String id, int textureId, @Nullable String name, @Nullable List<String> lore) {
        super(id + "_dust", textureId, name + " Dust", lore);
    }

    @Override
    public Table<CraftingType, Integer, ItemStack[]> getRecipesPatterns() {
        return null;
    }

    @Override
    public int getResearchCost() {
        return 0;
    }

}
