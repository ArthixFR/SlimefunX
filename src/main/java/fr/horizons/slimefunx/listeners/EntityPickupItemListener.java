package fr.horizons.slimefunx.listeners;

import fr.horizons.slimefunx.SlimefunX;
import fr.horizons.slimefunx.base.SlimefunObject;
import fr.horizons.slimefunx.block.BlocksManager;
import fr.horizons.slimefunx.interfaces.IStackable;
import fr.horizons.slimefunx.item.ItemsManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EntityPickupItemListener implements Listener {

    private SlimefunX plugin;
    private ItemsManager itemsManager;
    private BlocksManager blocksManager;

    public EntityPickupItemListener(SlimefunX plugin) {
        this.plugin = plugin;
        this.itemsManager = plugin.getItemsManager();
        this.blocksManager = plugin.getBlocksManager();
    }

    @EventHandler
    public void onPickupItem(EntityPickupItemEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            SlimefunObject sfPickup = itemsManager.getItemByTag(e.getItem().getItemStack());
            if (sfPickup == null) sfPickup = blocksManager.getBlockByTag(e.getItem().getItemStack());
            if (sfPickup == null) return;

            e.setCancelled(true);
            List<ItemStack> list = new ArrayList<>();

            for (ItemStack is : p.getInventory().getContents()) {
                if (is == null) continue;
                if (!is.hasItemMeta()) continue;
                if (is.getItemMeta().equals(sfPickup.getItem().getItemMeta()) && is.getType().equals(sfPickup.getItem().getType())) list.add(is);
            }

            int count = e.getItem().getItemStack().getAmount();

            for (ItemStack is : list) {
                if (count > 0) {
                    if ((is.getAmount() + e.getItem().getItemStack().getAmount()) <= ((IStackable) sfPickup).stackSize()) {
                        is.setAmount(e.getItem().getItemStack().getAmount() + is.getAmount());
                        e.getItem().remove();
                        return;
                    }
                    if ((is.getAmount() + e.getItem().getItemStack().getAmount()) > ((IStackable) sfPickup).stackSize()) {
                        int difference = (is.getAmount() + e.getItem().getItemStack().getAmount()) - ((IStackable) sfPickup).stackSize();
                        is.setAmount(((IStackable) sfPickup).stackSize());
                        e.getItem().getItemStack().setAmount(difference);
                        count = difference;
                    }
                }
            }
            if (p.getInventory().firstEmpty() != -1 && count > 0) {
                ItemStack is = e.getItem().getItemStack();
                is.setAmount(count);
                p.getInventory().addItem(is);
                e.getItem().remove();
            }
        }
    }
}
