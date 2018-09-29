package fr.horizons.slimefunx.item;

import fr.horizons.slimefunx.list.Categories;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.List;

public abstract class SlimefunResource extends SlimefunItem {

    public SlimefunResource(String id, int textureId, @Nullable String name, @Nullable List<String> lore) {
        super(id, Categories.RESSOURCES, new ItemStack(Material.DIAMOND_SHOVEL), textureId, name, lore);
    }

    @Override
    public int stackSize() {
        return 64;
    }
}
