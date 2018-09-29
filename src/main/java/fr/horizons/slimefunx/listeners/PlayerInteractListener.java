package fr.horizons.slimefunx.listeners;

import fr.horizons.slimefunx.SlimefunX;
import fr.horizons.slimefunx.base.SlimefunObject;
import fr.horizons.slimefunx.block.SlimefunBlock;
import fr.horizons.slimefunx.block.SlimefunMachine;
import fr.horizons.slimefunx.block.SlimefunStaticBlock;
import fr.horizons.slimefunx.item.SlimefunRessource;
import fr.horizons.slimefunx.item.SlimefunTool;
import fr.horizons.slimefunx.util.LocationUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerInteractListener implements Listener {
    private SlimefunX plugin;

    public PlayerInteractListener(SlimefunX plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getClickedBlock() == null) return;

        SlimefunObject slimefunObjectItem = plugin.getItemsManager().getItemByTag(e.getItem()) == null ? plugin.getBlocksManager().getBlockByTag(e.getItem()) : plugin.getItemsManager().getItemByTag(e.getItem());
        SlimefunBlock slimefunBlock = plugin.getBlocksManager().getBlockByTag(e.getClickedBlock());

        if (slimefunBlock instanceof SlimefunStaticBlock || slimefunBlock instanceof SlimefunMachine) {
            if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getHand().equals(EquipmentSlot.HAND) && !e.getPlayer().isSneaking()) {
                e.setCancelled(true);
                slimefunBlock.interactEvent(e);
                return;
            }
        }

        if (slimefunObjectItem instanceof SlimefunTool) {
            if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && (e.getClickedBlock().getType().equals(Material.DIRT) || e.getClickedBlock().getType().equals(Material.COARSE_DIRT) || e.getClickedBlock().getType().equals(Material.GRASS_BLOCK) || e.getClickedBlock().getType().equals(Material.GRASS_PATH))) e.setCancelled(true);
            ((SlimefunTool) slimefunObjectItem).interactEvent(e);
        } else if (slimefunObjectItem instanceof SlimefunStaticBlock || slimefunObjectItem instanceof SlimefunMachine) {
            if ((e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && !e.getClickedBlock().getType().isInteractable()) || (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock().getType().isInteractable() && e.getPlayer().isSneaking())) {
                e.setCancelled(true);
                Location newLoc = LocationUtils.getPlaceLocation(e.getClickedBlock().getLocation(), e.getBlockFace());
                if (LocationUtils.isPlaceAir(newLoc) && LocationUtils.isPlaceEmpty(newLoc)) ((SlimefunBlock) slimefunObjectItem).placeBlock(e.getItem(), newLoc);
            }
        } else if (slimefunObjectItem instanceof SlimefunRessource) {
            if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock().getType().equals(Material.GRASS_BLOCK)) e.setCancelled(true);
        }
    }
}
