package fr.horizons.slimefunx.list;

import fr.horizons.slimefunx.item.SlimefunRessource;
import fr.horizons.slimefunx.item.SlimefunTool;
import fr.horizons.slimefunx.item.ressources.dust.*;
import fr.horizons.slimefunx.item.tools.StoneHammer;
import fr.horizons.slimefunx.item.tools.WoodenHammer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemList {

    public static SlimefunTool WOODEN_HAMMER = new WoodenHammer("wooden_hammer", Categories.TOOLS, new ItemStack(Material.DIAMOND_HOE), 2, "§fWooden hammer", null);
    public static SlimefunTool STONE_HAMMER = new StoneHammer("stone_hammer", Categories.TOOLS, new ItemStack(Material.DIAMOND_HOE), 3, "§fStone hammer", null);

    public static SlimefunRessource SIFTED_DUST = new SiftedDust("sifted_dust", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 1, "§fSifted Dust", null);
    public static SlimefunRessource IRON_DUST = new IronDust("iron_dust", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 2, "§fIron Dust", null);
    public static SlimefunRessource GOLD_DUST = new GoldDust("gold_dust", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 3, "§fGold Dust", null);
    public static SlimefunRessource LAPIS_DUST = new LapisDust("lapis_dust", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 4, "§fLapis Dust", null);
    public static SlimefunRessource DIAMOND_DUST = new DiamondDust("diamond_dust", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 5, "§fDiamond Dust", null);
    public static SlimefunRessource EMERALD_DUST = new EmeraldDust("emerald_dust", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 6, "§fEmerald Dust", null);
}
