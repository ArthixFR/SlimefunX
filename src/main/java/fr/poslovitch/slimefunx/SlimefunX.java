package fr.poslovitch.slimefunx;

import fr.poslovitch.slimefunx.managers.CategoriesManager;
import fr.poslovitch.slimefunx.managers.ItemsManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class SlimefunX extends JavaPlugin {
    private static SlimefunX instance;

    // Managers
    private CategoriesManager categoriesManager;
    private ItemsManager itemsManager;

    @Override
    public void onEnable() {
        setInstance(this);

        categoriesManager = new CategoriesManager(this);
        categoriesManager.loadCategories();

        itemsManager = new ItemsManager(this);
        itemsManager.loadItems();
    }

    @Override
    public void onDisable() {

    }

    private static void setInstance(SlimefunX plugin) {
        SlimefunX.instance = plugin;
    }

    public static SlimefunX getInstance() {
        return instance;
    }

    // Folders

    public File getItemsFolder() {
        return new File(getDataFolder(), "items");
    }

    // Managers

    public CategoriesManager getCategoriesManager() {
        return categoriesManager;
    }

    public ItemsManager getItemsManager() {
        return itemsManager;
    }
}