package fr.poslovitch.slimefunx.api.categories;

import fr.poslovitch.slimefunx.api.interfaces.Identifiable;
import org.bukkit.inventory.ItemStack;

public class Category implements Identifiable {

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
