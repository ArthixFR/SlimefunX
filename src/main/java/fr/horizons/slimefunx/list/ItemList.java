package fr.horizons.slimefunx.list;

import fr.horizons.slimefunx.item.SlimefunResource;
import fr.horizons.slimefunx.item.SlimefunTool;
import fr.horizons.slimefunx.item.ressources.SlimefunDust;
import fr.horizons.slimefunx.item.ressources.SlimefunIngot;
import fr.horizons.slimefunx.item.ressources.SlimefunNugget;
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
    public static SlimefunNugget TIN_NUGGET = new SlimefunNugget("tin", 12, "Tin", null);
    public static SlimefunNugget COPPER_NUGGET = new SlimefunNugget("copper", 13, "Copper", null);
    public static SlimefunNugget ALUMINIUM_NUGGET = new SlimefunNugget("aluminium", 14, "Aluminium", null);
    public static SlimefunNugget ZINC_NUGGET = new SlimefunNugget("zinc", 15, "Zinc", null);
    public static SlimefunNugget MAGNESIUM_NUGGET = new SlimefunNugget("magnesium", 16, "Magnesium", null);
    public static SlimefunNugget LEAD_NUGGET = new SlimefunNugget("lead", 17, "Lead", null);
    public static SlimefunNugget SILVER_NUGGET = new SlimefunNugget("silver", 18, "Silver", null);

    // INGOTS
    public static SlimefunIngot TIN_INGOT = new SlimefunIngot("tin", 19, "Tin", null);
    public static SlimefunIngot COPPER_INGOT = new SlimefunIngot("copper", 20, "Copper", null);
    public static SlimefunIngot ALUMINIUM_INGOT = new SlimefunIngot("aluminium", 21, "Aluminium", null);
    public static SlimefunIngot ZINC_INGOT = new SlimefunIngot("zinc", 22, "Zinc", null);
    public static SlimefunIngot MAGNESIUM_INGOT = new SlimefunIngot("magnesium", 23, "Magnesium", null);
    public static SlimefunIngot LEAD_INGOT = new SlimefunIngot("lead", 24, "Lead", null);
    public static SlimefunIngot SILVER_INGOT = new SlimefunIngot("silver", 25, "Silver", null);
}
