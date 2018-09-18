package fr.horizons.slimefunx.listeners;

import fr.horizons.slimefunx.SlimefunX;
import fr.horizons.slimefunx.item.SlimefunItem;
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
        if (slimefunItem == null) return;
        if (slimefunItem instanceof SlimefunTool) {
            e.setCancelled(true);
            ((SlimefunTool) slimefunItem).breakBlockEvent(e);
            if (((SlimefunTool) slimefunItem).hasDurability()) {
                playerInventory.setItemInMainHand(Durability.setDurability(playerInventory.getItemInMainHand(), Durability.getDurability(playerInventory.getItemInMainHand()) - 1));
            }
        }
    }
}
