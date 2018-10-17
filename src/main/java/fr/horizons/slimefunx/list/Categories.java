package fr.horizons.slimefunx.list;

import fr.horizons.slimefunx.base.Category;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class Categories {
    public static final Category TOOLS = new Category("TOOLS", new ItemStack(Material.WOODEN_AXE), "Outils", null);
    public static final Category STATIC_BLOCKS = new Category("STATIC_BLOCKS", new ItemStack(Material.WOODEN_AXE), "Blocs", null);
    public static final Category MACHINES = new Category("MACHINES", new ItemStack(Material.IRON_BLOCK), "Machines", null);
    public static final Category RESSOURCES = new Category("RESSOURCES", new ItemStack(Material.REDSTONE), "Ressources", null);

    public static Category getCategoryById(String id) {
        final Category[] category_ = new Category[1];
        Arrays.stream(Categories.class.getDeclaredFields())
                .filter(field -> Category.class.isAssignableFrom(field.getType()))
                .forEach(field -> {
                    try {
                        Category category = (Category) field.get(null);
                        if (category.getId().equalsIgnoreCase(id)) category_[0] = category;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        return category_[0];
    }
}
