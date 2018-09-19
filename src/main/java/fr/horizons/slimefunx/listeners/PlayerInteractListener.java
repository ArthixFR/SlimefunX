package fr.horizons.slimefunx.listeners;

import fr.horizons.slimefunx.SlimefunX;
import fr.horizons.slimefunx.block.SlimefunBlock;
import fr.horizons.slimefunx.block.SlimefunStaticBlock;
import fr.horizons.slimefunx.item.SlimefunItem;
import fr.horizons.slimefunx.item.SlimefunTool;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {
    private SlimefunX plugin;

    public PlayerInteractListener(SlimefunX plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        SlimefunItem slimefunItem = plugin.getItemsManager().getItemByTag(e.getItem());
        SlimefunBlock slimefunBlock = plugin.getBlocksManager().getBlockByTag(e.getItem());
        if (slimefunItem instanceof SlimefunTool) {
            ((SlimefunTool) slimefunItem).interactEvent(e);
        }
        if (slimefunBlock instanceof SlimefunStaticBlock) {
            slimefunBlock.interactEvent(e);
        }
    }
}