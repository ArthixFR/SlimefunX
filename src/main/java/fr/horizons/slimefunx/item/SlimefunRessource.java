package fr.horizons.slimefunx.item;

import fr.horizons.slimefunx.base.Category;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.List;

public abstract class SlimefunRessource extends SlimefunItem {

    public SlimefunRessource(String id, Category category, ItemStack item, int textureId, @Nullable String name, @Nullable List<String> lore) {
        super(id, category, item, textureId, name, lore);
    }
}
