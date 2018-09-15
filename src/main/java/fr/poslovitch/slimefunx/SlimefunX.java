package fr.poslovitch.slimefunx;

import fr.poslovitch.slimefunx.managers.ItemsManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SlimefunX extends JavaPlugin {
    private static SlimefunX instance;

    private ItemsManager itemsManager;

    @Override
    public void onEnable() {
        setInstance(this);
        this.itemsManager = new ItemsManager(this);
        this.itemsManager.registerItems();
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
}