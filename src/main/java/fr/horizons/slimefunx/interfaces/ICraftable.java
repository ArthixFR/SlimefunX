package fr.horizons.slimefunx.interfaces;

import com.google.common.collect.Table;
import fr.horizons.slimefunx.util.CraftingType;
import org.bukkit.inventory.ItemStack;

public interface ICraftable {
    Table<CraftingType, Integer, ItemStack[]> getRecipesPatterns();
}
