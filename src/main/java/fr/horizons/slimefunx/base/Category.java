package fr.horizons.slimefunx.base;

import fr.horizons.slimefunx.interfaces.IIdentifiable;
import org.bukkit.inventory.ItemStack;

public class Category implements IIdentifiable {

    private final String id;
    private final ItemStack icon;

    public Category(String id, ItemStack icon) {
        this.id = id;
        this.icon = icon;
    }

    @Override
    public String getId() {
        return id;
    }

    public ItemStack getIcon() {
        return icon;
    }
}
