package fr.poslovitch.slimefunx.api.base;

import fr.poslovitch.slimefunx.api.categories.Category;
import fr.poslovitch.slimefunx.api.interfaces.Hashable;
import org.bukkit.inventory.ItemStack;

public class SlimefunObject implements Hashable {

    private String id;
    private Category category;
    private ItemStack item;

    public SlimefunObject(String id, Category category, ItemStack item) {
        this.id = id;
        this.category = category;
        this.item = item;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getHash() {
        return "";
    }

    public Category getCategory() {
        return category;
    }

    public ItemStack getItem() {
        return item;
    }
}
