package fr.poslovitch.slimefunx.api.base;

import fr.poslovitch.slimefunx.api.categories.Category;
import fr.poslovitch.slimefunx.api.interfaces.Hashable;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SlimefunObject implements Hashable {

    private final String id;
    private final Category category;
    private final ItemStack item;

    public SlimefunObject(String id, Category category, ItemStack item) {
        // Validation - make sure that none of the specified value is illegal.
        Validate.notNull(id, "Id cannot be null.");
        Validate.notNull(category, "Category cannot be null.");
        Validate.notNull(item, "Item cannot be null");
        Validate.isTrue(!(item.getType().equals(Material.AIR) || item.getType().equals(Material.CAVE_AIR) || item.getType().equals(Material.VOID_AIR)), "Item cannot be of type AIR: ", item.getType());
        // -------------------------------------------------------------------

        this.id = id;
        this.category = category;
        this.item = item;
    }

    @Override
    public String getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public ItemStack getItem() {
        return item;
    }
}
