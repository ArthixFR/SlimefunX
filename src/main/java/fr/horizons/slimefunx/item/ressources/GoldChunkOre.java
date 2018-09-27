package fr.horizons.slimefunx.item.ressources;

import fr.horizons.slimefunx.item.SlimefunRessource;
import fr.horizons.slimefunx.list.Categories;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;


public class GoldChunkOre extends SlimefunRessource {
    public GoldChunkOre() {
        super("gold_chunk_ore", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 2, "§fBroken Gold Ore", null);
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
