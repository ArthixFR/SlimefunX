package fr.horizons.slimefunx.listeners;

import fr.horizons.slimefunx.SlimefunX;
import fr.horizons.slimefunx.base.SlimefunObject;
import fr.horizons.slimefunx.block.BlocksManager;
import fr.horizons.slimefunx.interfaces.IStackable;
import fr.horizons.slimefunx.item.ItemsManager;
import fr.horizons.slimefunx.util.InventoryUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class InventoryClickListener implements Listener {

    private SlimefunX plugin;
    private ItemsManager itemsManager;
    private BlocksManager blocksManager;

    public InventoryClickListener(SlimefunX plugin) {
        this.plugin = plugin;
        this.itemsManager = plugin.getItemsManager();
        this.blocksManager = plugin.getBlocksManager();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if ((e.getAction().equals(InventoryAction.NOTHING) || e.getAction().equals(InventoryAction.PICKUP_SOME) || e.getAction().equals(InventoryAction.PICKUP_ONE)) && e.getClick() != null && e.getCurrentItem() != null && (e.getClick().equals(ClickType.LEFT) || e.getClick().equals(ClickType.RIGHT))) {
            SlimefunObject sfCurrent = itemsManager.getItemByTag(e.getCurrentItem());
            SlimefunObject sfCursor = itemsManager.getItemByTag(e.getCursor());
            if (sfCurrent == null) sfCurrent = blocksManager.getBlockByTag(e.getCurrentItem());
            if (sfCursor == null) sfCursor = blocksManager.getBlockByTag(e.getCursor());

            if (sfCurrent == null && sfCursor == null) return;
            if (sfCurrent.equals(sfCursor) && !e.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY)) {
                e.setCancelled(true);
                if (e.getClick().equals(ClickType.LEFT)) {
                    ItemStack is = e.getCurrentItem();
                    if ((e.getCurrentItem().getAmount() + e.getCursor().getAmount()) <= ((IStackable) sfCurrent).stackSize()) {
                        is.setAmount(e.getCurrentItem().getAmount() + e.getCursor().getAmount());
                        e.setCurrentItem(is);
                        e.getCursor().setType(Material.AIR);
                        e.getCursor().setAmount(0);
                    } else {
                        int difference = (e.getCurrentItem().getAmount() + e.getCursor().getAmount()) - ((IStackable) sfCurrent).stackSize();
                        is.setAmount(((IStackable) sfCurrent).stackSize());
                        e.setCurrentItem(is);
                        e.getCursor().setAmount(difference);
                    }
                    ((Player) e.getWhoClicked()).updateInventory();
                } else {
                    if (e.getCurrentItem().getAmount() < ((IStackable) sfCurrent).stackSize()) {
                        ItemStack is = e.getCurrentItem();
                        is.setAmount(e.getCurrentItem().getAmount() + 1);
                        e.setCurrentItem(is);
                        e.getCursor().setAmount(e.getCursor().getAmount() - 1);
                        ((Player) e.getWhoClicked()).updateInventory();
                    }
                }
            }
        } else if (e.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY) && e.getClick().equals(ClickType.SHIFT_LEFT)) {
            Player p = (Player) e.getWhoClicked();
            SlimefunObject sfCurrent = itemsManager.getItemByTag(e.getCurrentItem());
            if (sfCurrent == null) sfCurrent = blocksManager.getBlockByTag(e.getCurrentItem());
            if (sfCurrent == null) return;

            e.setCancelled(true);

            if (e.getClickedInventory().getType().equals(InventoryType.PLAYER) && p.getOpenInventory().getType().equals(InventoryType.CRAFTING)) {
                ItemStack[] items = new ItemStack[0];
                int slotStart = -1, slotEnd = -1;
                if (e.getSlotType().equals(InventoryType.SlotType.QUICKBAR)) {
                    items = IntStream.range(9, 35).boxed().map(p.getInventory()::getItem).toArray(ItemStack[]::new);
                    slotStart = 9;
                    slotEnd = 35;
                } else if (e.getSlotType().equals(InventoryType.SlotType.CONTAINER)) {
                    items = IntStream.range(0, 8).boxed().map(p.getInventory()::getItem).toArray(ItemStack[]::new);
                    slotStart = 0;
                    slotEnd = 8;
                }
                List<ItemStack> list = new ArrayList<>();

                for (ItemStack is : items) {
                    if (is == null) continue;
                    if (!is.hasItemMeta()) continue;
                    if (InventoryUtils.isEqual(sfCurrent, is)) list.add(is);
                }

                int count = e.getCurrentItem().getAmount();

                for (ItemStack is : list) {
                    if (count > 0) {
                        if ((is.getAmount() + e.getCurrentItem().getAmount()) <= ((IStackable) sfCurrent).stackSize()) {
                            is.setAmount(e.getCurrentItem().getAmount() + is.getAmount());
                            p.getInventory().setItem(e.getSlot(), null);
                            p.updateInventory();
                            return;
                        }
                        if ((is.getAmount() + e.getCurrentItem().getAmount()) > ((IStackable) sfCurrent).stackSize()) {
                            int difference = (is.getAmount() + e.getCurrentItem().getAmount()) - ((IStackable) sfCurrent).stackSize();
                            is.setAmount(((IStackable) sfCurrent).stackSize());
                            e.getCurrentItem().setAmount(difference);
                            count = difference;
                        }
                    }
                }
                int empty = InventoryUtils.checkFirstEmpty(slotStart, slotEnd, p.getInventory());
                if (empty != -1 && count > 0) {
                    ItemStack is = e.getCurrentItem();
                    is.setAmount(count);
                    p.getInventory().setItem(empty, is);
                    p.getInventory().setItem(e.getSlot(), null);
                }

            } else if (!e.getClickedInventory().getType().equals(InventoryType.PLAYER) && !p.getOpenInventory().getType().equals(InventoryType.CRAFTING)) {
                List<ItemStack> list = new ArrayList<>();

                for (ItemStack is : p.getInventory().getContents()) {
                    if (is == null) continue;
                    if (!is.hasItemMeta()) continue;
                    if (InventoryUtils.isEqual(sfCurrent, is)) list.add(is);
                }

                int count = e.getCurrentItem().getAmount();

                for (ItemStack is : list) {
                    if (count > 0) {
                        if ((is.getAmount() + e.getCurrentItem().getAmount()) <= ((IStackable) sfCurrent).stackSize()) {
                            is.setAmount(e.getCurrentItem().getAmount() + is.getAmount());
                            e.getClickedInventory().setItem(e.getSlot(), null);
                            p.updateInventory();
                            return;
                        }
                        if ((is.getAmount() + e.getCurrentItem().getAmount()) > ((IStackable) sfCurrent).stackSize()) {
                            int difference = (is.getAmount() + e.getCurrentItem().getAmount()) - ((IStackable) sfCurrent).stackSize();
                            is.setAmount(((IStackable) sfCurrent).stackSize());
                            e.getCurrentItem().setAmount(difference);
                            count = difference;
                        }
                    }
                }
                if (p.getInventory().firstEmpty() != -1 && count > 0) {
                    ItemStack is = e.getCurrentItem();
                    is.setAmount(count);
                    p.getInventory().addItem(is);
                    e.getClickedInventory().setItem(e.getSlot(), null);
                }
            } else if (e.getClickedInventory().getType().equals(InventoryType.PLAYER) && !p.getOpenInventory().getType().equals(InventoryType.CRAFTING)) {
                List<ItemStack> list = new ArrayList<>();

                for (ItemStack is : p.getOpenInventory().getTopInventory().getContents()) {
                    if (is == null) continue;
                    if (!is.hasItemMeta()) continue;
                    if (InventoryUtils.isEqual(sfCurrent, is)) list.add(is);
                }

                int count = e.getCurrentItem().getAmount();

                for (ItemStack is : list) {
                    if (count > 0) {
                        if ((is.getAmount() + e.getCurrentItem().getAmount()) <= ((IStackable) sfCurrent).stackSize()) {
                            is.setAmount(e.getCurrentItem().getAmount() + is.getAmount());
                            p.getInventory().setItem(e.getSlot(), null);
                            p.updateInventory();
                            return;
                        }
                        if ((is.getAmount() + e.getCurrentItem().getAmount()) > ((IStackable) sfCurrent).stackSize()) {
                            int difference = (is.getAmount() + e.getCurrentItem().getAmount()) - ((IStackable) sfCurrent).stackSize();
                            is.setAmount(((IStackable) sfCurrent).stackSize());
                            e.getCurrentItem().setAmount(difference);
                            count = difference;
                        }
                    }
                }
                if (p.getOpenInventory().getTopInventory().firstEmpty() != -1 && count > 0) {
                    ItemStack is = e.getCurrentItem();
                    is.setAmount(count);
                    p.getOpenInventory().getTopInventory().addItem(is);
                    p.getInventory().setItem(e.getSlot(), null);
                }
            }
        }
    }
}
