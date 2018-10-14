package fr.horizons.slimefunx.list;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import fr.horizons.slimefunx.util.CraftingType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.AbstractMap;
import java.util.Map;

public class CraftingList {

    public static Table<CraftingType, ItemStack, Map.Entry<Integer, ItemStack[]>> craftingTable = HashBasedTable.create();

    public static void initCrafting() {
        craftingTable.put(CraftingType.CRAFTING_TABLE, new ItemStack(Material.IRON_NUGGET), new AbstractMap.SimpleEntry<>(1, new ItemStack[] {
                ItemList.IRON_DUST.getItem(), ItemList.IRON_DUST.getItem(), ItemList.IRON_DUST.getItem(),
                ItemList.IRON_DUST.getItem(), ItemList.IRON_DUST.getItem(), ItemList.IRON_DUST.getItem(),
                ItemList.IRON_DUST.getItem(), ItemList.IRON_DUST.getItem(), ItemList.IRON_DUST.getItem()
        }));
        craftingTable.put(CraftingType.CRAFTING_TABLE, new ItemStack(Material.GOLD_NUGGET), new AbstractMap.SimpleEntry<>(1, new ItemStack[] {
                ItemList.GOLD_DUST.getItem(), ItemList.GOLD_DUST.getItem(), ItemList.GOLD_DUST.getItem(),
                ItemList.GOLD_DUST.getItem(), ItemList.GOLD_DUST.getItem(), ItemList.GOLD_DUST.getItem(),
                ItemList.GOLD_DUST.getItem(), ItemList.GOLD_DUST.getItem(), ItemList.GOLD_DUST.getItem()
        }));
    }
}
