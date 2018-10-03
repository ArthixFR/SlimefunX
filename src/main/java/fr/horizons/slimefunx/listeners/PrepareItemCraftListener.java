package fr.horizons.slimefunx.listeners;

import fr.horizons.slimefunx.SlimefunX;
import fr.horizons.slimefunx.util.CraftingType;
import fr.horizons.slimefunx.util.InventoryUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;


public class PrepareItemCraftListener implements Listener {

    private SlimefunX plugin;

    public PrepareItemCraftListener(SlimefunX plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPrepareItemCraft(PrepareItemCraftEvent e) {
        ItemStack is = InventoryUtils.getResultByRecipe(e.getInventory().getMatrix(), CraftingType.CRAFTING_TABLE);
        if (is != null) e.getInventory().setResult(is);
    }
}
