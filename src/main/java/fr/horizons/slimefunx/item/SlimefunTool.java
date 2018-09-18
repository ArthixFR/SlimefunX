package fr.horizons.slimefunx.item;

import fr.horizons.slimefunx.base.Category;
import fr.horizons.slimefunx.interfaces.IUsable;
import fr.horizons.slimefunx.util.Durability;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.List;

public abstract class SlimefunTool extends SlimefunItem implements IUsable {

    public SlimefunTool(String id, Category category, ItemStack item, int textureId, @Nullable String name, @Nullable List<String> lore) {
        super(id, category, item, textureId, name, lore);

        if (hasDurability()) this.setItem(Durability.init(this.getItem(), maxDurability()));
    }

    public boolean hasDurability() {
        return false;
    }

    public int maxDurability() {
        return 0;
    }
}
