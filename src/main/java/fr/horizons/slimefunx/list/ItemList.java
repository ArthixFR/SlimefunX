package fr.horizons.slimefunx.list;

import fr.horizons.slimefunx.item.SlimefunRessource;
import fr.horizons.slimefunx.item.SlimefunTool;
import fr.horizons.slimefunx.item.ressources.GoldChunkOre;
import fr.horizons.slimefunx.item.ressources.IronChunkOre;
import fr.horizons.slimefunx.item.tools.StoneHammer;
import fr.horizons.slimefunx.item.tools.WoodenHammer;

public class ItemList {

    public static SlimefunTool WOODEN_HAMMER = new WoodenHammer();
    public static SlimefunTool STONE_HAMMER = new StoneHammer();

    public static SlimefunRessource IRON_CHUNK_ORE = new IronChunkOre();
    public static SlimefunRessource GOLD_CHUNK_ORE = new GoldChunkOre();
}
