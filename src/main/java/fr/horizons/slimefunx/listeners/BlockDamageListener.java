package fr.horizons.slimefunx.listeners;

import fr.horizons.slimefunx.SlimefunX;
import fr.horizons.slimefunx.block.SlimefunBlock;
import fr.horizons.slimefunx.item.ItemsManager;
import fr.horizons.slimefunx.item.SlimefunItem;
import fr.horizons.slimefunx.util.BlockDamager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;

public class BlockDamageListener implements Listener {

    private SlimefunX plugin;
    private ItemsManager itemsManager;

    public BlockDamageListener(SlimefunX plugin) {
        this.plugin = plugin;
        this.itemsManager = plugin.getItemsManager();
    }

    @EventHandler
    public void onBlockDamage(BlockDamageEvent e) {
        SlimefunBlock sfBlock = SlimefunX.getInstance().getBlocksManager().getBlockByTag(e.getBlock());
        if (sfBlock == null) return;
        SlimefunItem slimefunItem = itemsManager.getItemByTag(e.getItemInHand());
        if (slimefunItem != null) BlockDamager.breakingList.put(e.getPlayer().getUniqueId(), new BlockDamager(e.getPlayer(), e.getBlock(), BlockDamager.getBlockDurability(sfBlock, slimefunItem)));
        else BlockDamager.breakingList.put(e.getPlayer().getUniqueId(), new BlockDamager(e.getPlayer(), e.getBlock(), BlockDamager.getBlockDurability(sfBlock, e.getItemInHand())));
    }
}
