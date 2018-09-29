package fr.horizons.slimefunx.list;

import fr.horizons.slimefunx.item.SlimefunResource;
import fr.horizons.slimefunx.item.SlimefunTool;
import fr.horizons.slimefunx.item.ressources.SlimefunDust;
import fr.horizons.slimefunx.item.ressources.ingot.*;
import fr.horizons.slimefunx.item.ressources.nugget.*;
import fr.horizons.slimefunx.item.tools.StoneHammer;
import fr.horizons.slimefunx.item.tools.WoodenHammer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemList {

    // TOOLS
    public static SlimefunTool WOODEN_HAMMER = new WoodenHammer("wooden_hammer", Categories.TOOLS, new ItemStack(Material.DIAMOND_HOE), 2, "Wooden hammer", null);
    public static SlimefunTool STONE_HAMMER = new StoneHammer("stone_hammer", Categories.TOOLS, new ItemStack(Material.DIAMOND_HOE), 3, "Stone hammer", null);

    // DUSTS
    public static SlimefunDust SIFTED_DUST = new SlimefunDust("sifted", 1, "Sifted", null);
    public static SlimefunDust IRON_DUST = new SlimefunDust("iron", 2, "Iron", null);
    public static SlimefunDust GOLD_DUST = new SlimefunDust("gold", 3, "Gold", null);
    public static SlimefunDust TIN_DUST = new SlimefunDust("tin", 4, "Tin", null);
    public static SlimefunDust COPPER_DUST = new SlimefunDust("copper", 5, "Copper", null);
    public static SlimefunDust ALUMINIUM_DUST = new SlimefunDust("aluminium", 6, "Aluminium", null);
    public static SlimefunDust ZINC_DUST = new SlimefunDust("zinc", 7, "Zinc", null);
    public static SlimefunDust SILICIUM = new SlimefunDust("silicium", 8, "Silicium", null);
    public static SlimefunDust MAGNESIUM_DUST = new SlimefunDust("magnesium", 9, "Magnesium", null);
    public static SlimefunDust LEAD_DUST = new SlimefunDust("lead", 10, "Lead", null);
    public static SlimefunDust SILVER_DUST = new SlimefunDust("silver", 11, "Silver", null);

    // NUGGETS
    public static SlimefunResource TIN_NUGGET = new TinNugget("tin_nugget", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 12, "Tin Nugget", null);
    public static SlimefunResource COPPER_NUGGET = new CopperNugget("copper_nugget", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 13, "Copper Nugget", null);
    public static SlimefunResource ALUMINIUM_NUGGET = new AluminiumNugget("aluminium_nugget", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 14, "Aluminium Nugget", null);
    public static SlimefunResource ZINC_NUGGET = new ZincNugget("zinc_nugget", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 15, "Zinc Nugget", null);
    public static SlimefunResource MAGNESIUM_NUGGET = new MagnesiumNugget("magnesium_nugget", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 16, "Magnesium Nugget", null);
    public static SlimefunResource LEAD_NUGGET = new LeadNugget("lead_nugget", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 17, "Lead Nugget", null);
    public static SlimefunResource SILVER_NUGGET = new SilverNugget("silver_nugget", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 18, "Silver Nugget", null);

    // INGOTS
    public static SlimefunResource TIN_INGOT = new TinIngot("tin_ingot", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 19, "Tin Ingot", null);
    public static SlimefunResource COPPER_INGOT = new CopperIngot("copper_ingot", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 20, "Copper Ingot", null);
    public static SlimefunResource ALUMINIUM_INGOT = new AluminiumIngot("aluminium_ingot", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 21, "Aluminium Ingot", null);
    public static SlimefunResource ZINC_INGOT = new ZincIngot("zinc_ingot", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 22, "Zinc Ingot", null);
    public static SlimefunResource MAGNESIUM_INGOT = new MagnesiumIngot("magnesium_ingot", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 23, "Magnesium Ingot", null);
    public static SlimefunResource LEAD_INGOT = new LeadIngot("lead_ingot", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 24, "Lead Ingot", null);
    public static SlimefunResource SILVER_INGOT = new SilverIngot("silver_ingot", Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), 25, "Silver Ingot", null);
}
