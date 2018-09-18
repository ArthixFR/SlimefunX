package fr.horizons.slimefunx.block;

import fr.horizons.slimefunx.base.Category;
import fr.horizons.slimefunx.interfaces.IInteractable;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.List;

public abstract class SlimefunMachine extends SlimefunBlock implements IInteractable {
    public SlimefunMachine(String id, Category category, ItemStack item, int textureId, @Nullable String name, @Nullable List<String> lore) {
        super(id, category, item, textureId, name, lore);
    }
}
