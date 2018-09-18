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
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

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
        if ((e.getAction().equals(InventoryAction.NOTHING) || e.getAction().equals(InventoryAction.PICKUP_SOME)) && e.getClick() != null && e.getCurrentItem() != null && (e.getClick().equals(ClickType.LEFT) || e.getClick().equals(ClickType.RIGHT))) {
            int slot = e.getSlot();
            SlimefunObject sfCurrent = itemsManager.getItemByTag(e.getCurrentItem());
            SlimefunObject sfCursor = itemsManager.getItemByTag(e.getCursor());
            if (sfCurrent == null) sfCurrent = blocksManager.getBlockByTag(e.getCurrentItem());
            if (sfCursor == null) sfCursor = blocksManager.getBlockByTag(e.getCursor());

            if (sfCurrent == null && sfCursor == null) return;
            if (sfCurrent.equals(sfCursor)) {
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
                    ((Player)e.getWhoClicked()).updateInventory();
                } else {
                    if (e.getCurrentItem().getAmount() < ((IStackable) sfCurrent).stackSize()) {
                        ItemStack is = e.getCurrentItem();
                        is.setAmount(e.getCurrentItem().getAmount() + 1);
                        e.setCurrentItem(is);
                        e.getCursor().setAmount(e.getCursor().getAmount() - 1);
                        ((Player)e.getWhoClicked()).updateInventory();
                    }
                }
            }
        }
        /*System.out.println(" ");
        System.out.println("Action : " + e.getAction());
        System.out.println("Click : " + e.getClick());
        System.out.println("CurrentItem : " + e.getCurrentItem().getType());
        System.out.println("Cursor : " + e.getCursor().getType());
        System.out.println("Slot : " + e.getSlot());
        System.out.println(" ");*/
    }
}
