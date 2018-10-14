package fr.horizons.slimefunx.item.ressources;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import fr.horizons.slimefunx.SlimefunX;
import fr.horizons.slimefunx.item.SlimefunItem;
import fr.horizons.slimefunx.item.SlimefunResource;
import fr.horizons.slimefunx.util.CraftingType;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.List;
import java.util.logging.Level;

public class SlimefunIngot extends SlimefunResource {

    public SlimefunIngot(String id, int textureId, @Nullable String name, @Nullable List<String> lore) {
        super(id + "_ingot", textureId, name + " Ingot", lore);
    }

    @Override
    public Table<CraftingType, Integer, ItemStack[]> getRecipesPatterns() {
        String dustId = getId().split("_")[0];
        SlimefunItem slimefunItem = SlimefunX.getInstance().getItemsManager().getItemById(dustId + "_nugget");
        if (slimefunItem == null) {
            SlimefunX.getInstance().getLogger().log(Level.SEVERE, "Failed to register craft for " + getId() + " !");
            return null;
        } else {
            return ImmutableTable.<CraftingType, Integer, ItemStack[]>builder().put(CraftingType.CRAFTING_TABLE, 1,
                    new ItemStack[]{
                            slimefunItem.getItem(),slimefunItem.getItem(),slimefunItem.getItem(),
                            slimefunItem.getItem(),slimefunItem.getItem(),slimefunItem.getItem(),
                            slimefunItem.getItem(),slimefunItem.getItem(),slimefunItem.getItem(),
                    }).build();
        }
    }

    @Override
    public int getResearchCost() {
        return 0;
    }
}
