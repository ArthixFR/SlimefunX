package fr.poslovitch.slimefunx.api.categories;

import fr.poslovitch.slimefunx.api.interfaces.Hashable;
import org.bukkit.inventory.ItemStack;

public class Category implements Hashable {

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

    @Override
    public String getHash() {
        return "";
    }

    public ItemStack getIcon() {
        return icon;
    }
}
