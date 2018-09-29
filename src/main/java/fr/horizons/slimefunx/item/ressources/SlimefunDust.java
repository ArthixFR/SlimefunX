package fr.horizons.slimefunx.item.ressources;

import fr.horizons.slimefunx.item.SlimefunRessource;
import fr.horizons.slimefunx.list.Categories;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.List;

public class SlimefunDust extends SlimefunRessource {

    public SlimefunDust(String id, int textureId, @Nullable String name, @Nullable List<String> lore) {
        super(id + "_dust", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), textureId, "§f" + name + " Dust", lore);
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
