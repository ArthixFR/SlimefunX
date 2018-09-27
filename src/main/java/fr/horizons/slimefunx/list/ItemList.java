package fr.horizons.slimefunx.list;

import fr.horizons.slimefunx.item.SlimefunRessource;
import fr.horizons.slimefunx.item.SlimefunTool;
import fr.horizons.slimefunx.item.ressources.GoldChunkOre;
import fr.horizons.slimefunx.item.ressources.IronChunkOre;
import fr.horizons.slimefunx.item.tools.StoneHammer;
import fr.horizons.slimefunx.item.tools.WoodenHammer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemList {

    public static SlimefunTool WOODEN_HAMMER = new WoodenHammer("wooden_hammer", Categories.TOOLS, new ItemStack(Material.DIAMOND_HOE), 2, "§fWooden hammer", null);
    public static SlimefunTool STONE_HAMMER = new StoneHammer("stone_hammer", Categories.TOOLS, new ItemStack(Material.DIAMOND_HOE), 3, "§fStone hammer", null);

    public static SlimefunRessource IRON_CHUNK_ORE = new IronChunkOre();
    public static SlimefunRessource GOLD_CHUNK_ORE = new GoldChunkOre();
}
