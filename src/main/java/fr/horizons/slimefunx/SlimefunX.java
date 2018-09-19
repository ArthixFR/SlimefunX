package fr.horizons.slimefunx;

import fr.horizons.slimefunx.block.BlocksManager;
import fr.horizons.slimefunx.commands.BaseCommand;
import fr.horizons.slimefunx.listeners.*;
import fr.horizons.slimefunx.item.ItemsManager;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class SlimefunX extends JavaPlugin {

    private static SlimefunX instance;
    private ItemsManager itemsManager;
    private BlocksManager blocksManager;

    @Override
    public void onEnable() {
        instance = this;

        this.itemsManager = new ItemsManager(this);
        this.itemsManager.registerItems();

        this.blocksManager = new BlocksManager(this);
        this.blocksManager.registerBlocks();

        PluginCommand command = getCommand("sfx");
        command.setExecutor(new BaseCommand(this));
        command.setTabCompleter(new BaseCommand(this));

        registerEvents();
    }

    @Override
    public void onDisable() {

    }

    public void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new PlayerInteractListener(this), this);
        pm.registerEvents(new BlockBreakListener(this), this);
        pm.registerEvents(new InventoryClickListener(this), this);
        pm.registerEvents(new EntityPickupItemListener(this), this);
        pm.registerEvents(new InventoryMoveItemListener(this), this);
    }

    public static SlimefunX getInstance() {
        return instance;
    }

    public ItemsManager getItemsManager() {
        return itemsManager;
    }

    public BlocksManager getBlocksManager() {
        return blocksManager;
    }
}
