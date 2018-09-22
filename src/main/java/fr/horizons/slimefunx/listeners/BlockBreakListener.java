package fr.horizons.slimefunx.listeners;

import fr.horizons.slimefunx.SlimefunX;
import fr.horizons.slimefunx.block.SlimefunBlock;
import fr.horizons.slimefunx.item.SlimefunItem;
import fr.horizons.slimefunx.item.SlimefunRessource;
import fr.horizons.slimefunx.item.SlimefunTool;
import fr.horizons.slimefunx.util.Durability;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.PlayerInventory;

public class BlockBreakListener implements Listener {
    private SlimefunX plugin;

    public BlockBreakListener(SlimefunX plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        PlayerInventory playerInventory = e.getPlayer().getInventory();
        SlimefunItem slimefunItem = plugin.getItemsManager().getItemByTag(playerInventory.getItemInMainHand());
        SlimefunBlock slimefunBlock = plugin.getBlocksManager().getBlockByTag(playerInventory.getItemInMainHand());
        if (slimefunBlock != null) {
            e.setCancelled(true);
            return;
        }
        if (slimefunItem instanceof SlimefunTool) {
            ((SlimefunTool) slimefunItem).breakBlockEvent(e.getPlayer(), e.getBlock());
        } else if (slimefunItem instanceof SlimefunRessource) e.setCancelled(true);
    }
}
