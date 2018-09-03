package fr.poslovitch.slimefunx;

import org.bukkit.plugin.java.JavaPlugin;

public class SlimefunX extends JavaPlugin {
    private static SlimefunX instance;

    @Override
    public void onEnable() {
        setInstance(this);
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