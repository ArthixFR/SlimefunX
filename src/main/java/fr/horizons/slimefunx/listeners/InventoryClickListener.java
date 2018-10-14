package fr.horizons.slimefunx.listeners;

import fr.horizons.slimefunx.SlimefunX;
import fr.horizons.slimefunx.base.SlimefunObject;
import fr.horizons.slimefunx.block.BlocksManager;
import fr.horizons.slimefunx.block.SlimefunBlock;
import fr.horizons.slimefunx.interfaces.IStackable;
import fr.horizons.slimefunx.item.ItemsManager;
import fr.horizons.slimefunx.item.SlimefunItem;
import fr.horizons.slimefunx.util.CraftingType;
import fr.horizons.slimefunx.util.InventoryUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
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
         if (e.getClickedInventory() != null && e.getClick() != null && e.getClickedInventory().getType().equals(InventoryType.WORKBENCH) && e.getSlotType().equals(InventoryType.SlotType.RESULT)) {
            SlimefunObject slimefunObject = InventoryUtils.getSlimefunObject(e.getCurrentItem());
            Player p = (Player) e.getWhoClicked();
            if (slimefunObject != null) {
                e.setCancelled(true);
                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    Inventory inv = e.getClickedInventory();
                    ItemStack[] content = inv.getContents();
                    if (!e.getClick().equals(ClickType.SHIFT_LEFT)) {
                        for (int i = 1; i < content.length; i++) {
                            if (content[i] == null) continue;
                            if (content[i].getType() == Material.AIR) continue;
                            if (content[i].getAmount() == 1) inv.setItem(i, null);
                            else {
                                ItemStack is = content[i].clone();
                                is.setAmount(content[i].getAmount() - 1);
                                inv.setItem(i, is);
                            }
                        }
                        e.setCursor(slimefunObject.getItem());
                    } else {
                        ArrayList<Integer> countList = new ArrayList<>();
                        ItemStack[] gridContent = new ItemStack[9];
                        for (int i = 1; i < content.length; i++) {
                            gridContent[i - 1] = content[i];
                            if (content[i] == null) continue;
                            if (content[i].getType() == Material.AIR) continue;
                            countList.add(content[i].getAmount());
                        }
                        int min = Collections.min(countList);
                        final int[] countPerCraft = {1};
                        int stackSize = e.getCurrentItem().getMaxStackSize();

                        if (slimefunObject instanceof SlimefunBlock) {
                            stackSize = ((SlimefunBlock) slimefunObject).stackSize();
                            ((SlimefunBlock) slimefunObject).getRecipesPatterns().row(CraftingType.CRAFTING_TABLE).forEach((integer, itemStacks) -> {
                                if (InventoryUtils.isValidRecipe(gridContent, itemStacks)) countPerCraft[0] = integer;
                            });
                        } else if (slimefunObject instanceof SlimefunItem) {
                            stackSize = ((SlimefunItem) slimefunObject).stackSize();
                            ((SlimefunItem) slimefunObject).getRecipesPatterns().row(CraftingType.CRAFTING_TABLE).forEach((integer, itemStacks) -> {
                                if (InventoryUtils.isValidRecipe(gridContent, itemStacks)) countPerCraft[0] = integer;
                            });
                        }

                        int maxPlaceable = InventoryUtils.getNumberOfItemPlaceable(slimefunObject.getItem(), p.getInventory().getStorageContents());
                        int maxCraftPlaceable = (int)Math.floor((double)maxPlaceable / countPerCraft[0]);
                        if (min > maxCraftPlaceable) min = maxCraftPlaceable;

                        if (maxCraftPlaceable == 0) return;

                        List<ItemStack> list = new ArrayList<>();

                        for (ItemStack is : p.getInventory().getStorageContents()) {
                            if (is == null) continue;
                            if (!is.hasItemMeta()) continue;
                            if (InventoryUtils.isEqual(slimefunObject, is)) list.add(is);
                        }

                        int count = min * countPerCraft[0];

                        for (ItemStack is : list) {
                            if (count > 0) {
                                if ((is.getAmount() + count) <= stackSize) {
                                    is.setAmount(count + is.getAmount());
                                    p.updateInventory();
                                    return;
                                }
                                if ((is.getAmount() + count) > stackSize) {
                                    int difference = (is.getAmount() + count) - stackSize;
                                    is.setAmount(stackSize);
                                    count = difference;
                                }
                            }
                        }
                        for (int i = count; i > 0; i -= stackSize) {
                            if (p.getInventory().firstEmpty() != -1) {
                                ItemStack is = e.getCurrentItem().clone();
                                is.setAmount(stackSize);
                                p.getInventory().addItem(is);
                            }
                        }

                        for (int i = 1; i < content.length; i++) {
                            if (content[i] == null) continue;
                            if (content[i].getType() == Material.AIR) continue;
                            if (content[i].getAmount() == 1) inv.setItem(i, null);
                            else {
                                ItemStack is = content[i].clone();
                                is.setAmount(content[i].getAmount() - min);
                                inv.setItem(i, is);
                            }
                        }
                    }
                }, 1L);
            }
        } else if ((e.getAction().equals(InventoryAction.NOTHING) || e.getAction().equals(InventoryAction.PICKUP_SOME) || e.getAction().equals(InventoryAction.PICKUP_ONE) || e.getAction().equals(InventoryAction.SWAP_WITH_CURSOR)) && e.getClick() != null && e.getCurrentItem() != null && (e.getClick().equals(ClickType.LEFT) || e.getClick().equals(ClickType.RIGHT))) {
            SlimefunObject sfCurrent = itemsManager.getItemByTag(e.getCurrentItem());
            SlimefunObject sfCursor = itemsManager.getItemByTag(e.getCursor());
            if (sfCurrent == null) sfCurrent = blocksManager.getBlockByTag(e.getCurrentItem());
            if (sfCursor == null) sfCursor = blocksManager.getBlockByTag(e.getCursor());

            if (sfCurrent == null && sfCursor == null) return;
            if (InventoryUtils.isEqual(sfCurrent, sfCursor.getItem()) && !e.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY)) {
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
                    items = IntStream.rangeClosed(9, 35).boxed().map(p.getInventory()::getItem).toArray(ItemStack[]::new);
                    slotStart = 9;
                    slotEnd = 35;
                } else if (e.getSlotType().equals(InventoryType.SlotType.CONTAINER)) {
                    items = IntStream.rangeClosed(0, 8).boxed().map(p.getInventory()::getItem).toArray(ItemStack[]::new);
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

                for (ItemStack is : p.getInventory().getStorageContents()) {
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
