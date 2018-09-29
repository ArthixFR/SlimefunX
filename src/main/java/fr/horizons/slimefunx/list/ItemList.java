package fr.horizons.slimefunx.list;

import fr.horizons.slimefunx.item.SlimefunRessource;
import fr.horizons.slimefunx.item.SlimefunTool;
import fr.horizons.slimefunx.item.ressources.dust.*;
import fr.horizons.slimefunx.item.ressources.ingot.*;
import fr.horizons.slimefunx.item.ressources.nugget.*;
import fr.horizons.slimefunx.item.tools.StoneHammer;
import fr.horizons.slimefunx.item.tools.WoodenHammer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemList {

    // TOOLS
    public static SlimefunTool WOODEN_HAMMER = new WoodenHammer("wooden_hammer", Categories.TOOLS, new ItemStack(Material.DIAMOND_HOE), 2, "§fWooden hammer", null);
    public static SlimefunTool STONE_HAMMER = new StoneHammer("stone_hammer", Categories.TOOLS, new ItemStack(Material.DIAMOND_HOE), 3, "§fStone hammer", null);

    // DUSTS
    public static SlimefunRessource SIFTED_DUST = new SiftedDust("sifted_dust", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 1, "§fSifted Dust", null);
    public static SlimefunRessource IRON_DUST = new IronDust("iron_dust", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 2, "§fIron Dust", null);
    public static SlimefunRessource GOLD_DUST = new GoldDust("gold_dust", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 3, "§fGold Dust", null);
    public static SlimefunRessource TIN_DUST = new GoldDust("tin_dust", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 4, "§fTin Dust", null);
    public static SlimefunRessource COPPER_DUST = new GoldDust("copper_dust", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 5, "§fCopper Dust", null);
    public static SlimefunRessource ALUMINIUM_DUST = new GoldDust("aluminium_dust", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 6, "§fAluminium Dust", null);
    public static SlimefunRessource ZINC_DUST = new GoldDust("zinc_dust", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 7, "§fZinc Dust", null);
    public static SlimefunRessource SILICIUM = new GoldDust("silicium", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 8, "§fSilicium", null);
    public static SlimefunRessource MAGNESIUM_DUST = new GoldDust("magnesium_dust", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 9, "§fMagnesium Dust", null);
    public static SlimefunRessource LEAD_DUST = new GoldDust("lead_dust", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 10, "§fLead Dust", null);
    public static SlimefunRessource SILVER_DUST = new GoldDust("silver_dust", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 11, "§fSilver Dust", null);

    // NUGGETS
    public static SlimefunRessource TIN_NUGGET = new TinNugget("tin_nugget", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 12, "§fTin Nugget", null);
    public static SlimefunRessource COPPER_NUGGET = new CopperNugget("copper_nugget", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 13, "§fCopper Nugget", null);
    public static SlimefunRessource ALUMINIUM_NUGGET = new AluminiumNugget("aluminium_nugget", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 14, "§fAluminium Nugget", null);
    public static SlimefunRessource ZINC_NUGGET = new ZincNugget("zinc_nugget", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 15, "§fZinc Nugget", null);
    public static SlimefunRessource MAGNESIUM_NUGGET = new MagnesiumNugget("magnesium_nugget", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 16, "§fMagnesium Nugget", null);
    public static SlimefunRessource LEAD_NUGGET = new LeadNugget("lead_nugget", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 17, "§fLead Nugget", null);
    public static SlimefunRessource SILVER_NUGGET = new SilverNugget("silver_nugget", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 18, "§fSilver Nugget", null);

    // INGOTS
    public static SlimefunRessource TIN_INGOT = new TinIngot("tin_ingot", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 19, "§fTin Ingot", null);
    public static SlimefunRessource COPPER_INGOT = new CopperIngot("copper_ingot", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 20, "§fCopper Ingot", null);
    public static SlimefunRessource ALUMINIUM_INGOT = new AluminiumIngot("aluminium_ingot", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 21, "§fAluminium Ingot", null);
    public static SlimefunRessource ZINC_INGOT = new ZincIngot("zinc_ingot", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 22, "§fZinc Ingot", null);
    public static SlimefunRessource MAGNESIUM_INGOT = new MagnesiumIngot("magnesium_ingot", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 23, "§fMagnesium Ingot", null);
    public static SlimefunRessource LEAD_INGOT = new LeadIngot("lead_ingot", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 24, "§fLead Ingot", null);
    public static SlimefunRessource SILVER_INGOT = new SilverIngot("silver_ingot", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 25, "§fSilver Ingot", null);
}
