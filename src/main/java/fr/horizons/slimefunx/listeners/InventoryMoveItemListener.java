package fr.horizons.slimefunx.listeners;

import fr.horizons.slimefunx.SlimefunX;
import fr.horizons.slimefunx.base.SlimefunObject;
import fr.horizons.slimefunx.block.BlocksManager;
import fr.horizons.slimefunx.interfaces.IStackable;
import fr.horizons.slimefunx.item.ItemsManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class InventoryMoveItemListener implements Listener {

    private BlocksManager blocksManager;
    private ItemsManager itemsManager;
    private SlimefunX plugin;

    public InventoryMoveItemListener(SlimefunX plugin) {
        this.plugin = plugin;
        this.itemsManager = plugin.getItemsManager();
        this.blocksManager = plugin.getBlocksManager();
    }

    @EventHandler
    public void onInventoryMoveItem(InventoryMoveItemEvent e) {
        Inventory inv = e.getDestination();
        ItemStack is = e.getItem().clone();

        SlimefunObject sfItem = itemsManager.getItemByTag(is);
        if (sfItem == null) sfItem = blocksManager.getBlockByTag(is);
        if (sfItem == null) return;
        SlimefunObject finalSfItem = sfItem;

        List<ItemStack> list = new ArrayList<>();

        e.setCancelled(true);

        for (ItemStack item : inv.getContents()) {
            if (item == null) continue;
            if (!item.hasItemMeta()) continue;
            if (item.getItemMeta().equals(sfItem.getItem().getItemMeta()) && item.getType().equals(sfItem.getItem().getType()))
                list.add(item);
        }

        int count = is.getAmount();
        System.out.println(count);
        int difference = count;

        for (ItemStack item : list) {
            if (count > 0) {
                if ((item.getAmount() + count) <= ((IStackable) sfItem).stackSize()) {
                    item.setAmount(item.getAmount() + count);

                    Bukkit.getScheduler().runTaskLater(plugin, () -> {
                        int first = getFirst(finalSfItem.getItem(), e.getSource());
                        if (first != -1) {
                            ItemStack firstIs = e.getSource().getItem(first);
                            System.out.println(firstIs.getAmount());
                            firstIs.setAmount(firstIs.getAmount() - count);
                            e.getSource().setItem(first, firstIs);
                        }
                    }, 1L);
                    return;
                }
                if (item.getAmount() >= ((IStackable) sfItem).stackSize()) continue;
                if ((item.getAmount() + count) > ((IStackable) sfItem).stackSize()) {
                    difference = (item.getAmount() + count) - ((IStackable) sfItem).stackSize();
                    item.setAmount(((IStackable) sfItem).stackSize());

                    Bukkit.getScheduler().runTaskLater(plugin, () -> {
                        int first = getFirst(finalSfItem.getItem(), e.getSource());
                        if (first != -1) {
                            ItemStack firstIs = e.getSource().getItem(first);
                            System.out.println(firstIs.getAmount());
                            firstIs.setAmount(firstIs.getAmount() - count);
                            e.getSource().setItem(first, firstIs);
                        }
                    }, 1L);
                }
            }
        }

        if (inv.firstEmpty() != -1 && difference > 0) {
            ItemStack item = is;
            item.setAmount(difference);
            inv.addItem(item);
            int finalDifference = difference;

            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                int first = getFirst(finalSfItem.getItem(), e.getSource());
                if (first != -1) {
                    ItemStack firstIs = e.getSource().getItem(first);
                    firstIs.setAmount(firstIs.getAmount() - finalDifference);
                    e.getSource().setItem(first, firstIs);
                }
            }, 1L);
        }
    }

    public int getFirst(ItemStack is, Inventory inv) {
        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack item = inv.getItem(i);
            if (item == null) continue;
            if (!item.hasItemMeta()) continue;
            if (item.getItemMeta().equals(is.getItemMeta()) && item.getType().equals(is.getType())) return i;
        }
        return -1;
    }
}
