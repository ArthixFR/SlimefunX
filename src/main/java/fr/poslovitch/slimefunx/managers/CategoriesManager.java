package fr.poslovitch.slimefunx.managers;

import fr.poslovitch.slimefunx.SlimefunX;
import fr.poslovitch.slimefunx.api.Category;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoriesManager {

    private final SlimefunX plugin;
    private final List<Category> categories;

    public CategoriesManager(SlimefunX plugin) {
        this.plugin = plugin;
        this.categories = new ArrayList<>();
    }

    public void loadCategories() {
        for (File categoryFolder : Objects.requireNonNull(plugin.getItemsFolder().listFiles())) {
            File categoryDescriptor = new File(categoryFolder, "category_descriptor.yml");
            if (categoryDescriptor.exists()) {
                loadCategory(categoryDescriptor);
            } else {
                System.out.println("Folder '" + categoryFolder + "' does not contain a descriptor file.");
            }
        }

        plugin.getLogger().info("Registered " + categories.size() + " categories.");
    }

    private void loadCategory(File file) {
        Category.CategoryBuilder builder = new Category.CategoryBuilder();

        try {
            YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);

            builder.id(file.getParent().substring(file.getParent().lastIndexOf(File.separator) + 1))
                .directory(file.getParentFile());

            if (yaml.contains("icon")) {
                ItemStack itemStack = new ItemStack(Material.getMaterial(yaml.getString("icon.material")));

                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setLocalizedName(yaml.getString("icon.name"));

                itemStack.setItemMeta(itemMeta);

                builder.icon(itemStack);
            } else {
                plugin.getLogger().warning("Category descriptor '" + file.getPath() + "' uses an invalid format or does not contain all the needed information in 'icon'.");
                return;
            }

            categories.add(builder.build());
        } catch (Exception e) {
            plugin.getLogger().warning("Category descriptor '" + file.getPath() + "' uses an invalid format or does not contain all the needed information.");
        }
    }

    public List<Category> getCategories() {
        return categories;
    }

    public Category getCategoryById(String id) {
        return categories.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }
}
