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

public class SlimefunNugget extends SlimefunResource {

    public SlimefunNugget(String id, int textureId, @Nullable String name, @Nullable List<String> lore) {
        super(id + "_nugget", textureId, name + " Nugget", lore);
    }

    @Override
    public Table<CraftingType, Integer, ItemStack[]> getRecipesPatterns() {
        String nuggetId = getId().split("_")[0];
        SlimefunItem slimefunItem = SlimefunX.getInstance().getItemsManager().getItemById(nuggetId + "_dust");
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
