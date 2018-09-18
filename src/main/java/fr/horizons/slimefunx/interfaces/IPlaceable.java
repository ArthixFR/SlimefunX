package fr.horizons.slimefunx.interfaces;

import org.bukkit.event.player.PlayerInteractEvent;

public interface IPlaceable {

    boolean isPlaceable();
    void interactEvent(PlayerInteractEvent e);
}
