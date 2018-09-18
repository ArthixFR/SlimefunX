package fr.horizons.slimefunx.interfaces;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public interface IUsable {

    void interactEvent(PlayerInteractEvent e);
    void breakBlockEvent(BlockBreakEvent e);
}
