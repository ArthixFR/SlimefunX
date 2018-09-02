package fr.poslovitch.slimefunx.api;

import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Category {

    private final String id;
    private final File directory;
    private final ItemStack icon;

    private final List<SlimefunItem> items;

    private Category(String id, File directory, ItemStack icon) {
        this.id = id;
        this.directory = directory;
        this.icon = icon;
        this.items = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public File getDirectory() {
        return directory;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public List<SlimefunItem> getItems() {
        return items;
    }

    public void addItem(SlimefunItem item) {
        items.add(item);
    }

    public static class CategoryBuilder {
        private String id;
        private File directory;
        private ItemStack icon;

        public CategoryBuilder id(String id) {
            this.id = id;
            return this;
        }

        public CategoryBuilder directory(File directory) {
            this.directory = directory;
            return this;
        }

        public CategoryBuilder icon(ItemStack icon) {
            this.icon = icon;
            return this;
        }

        public Category build() {
            return new Category(id, directory, icon);
        }
    }

    @Override
    public String toString() {
        return "Category{id="
                + id
                + ",directory="
                + directory
                + ",icon="
                + icon;
    }
}
