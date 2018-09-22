package fr.horizons.slimefunx.interfaces;

import fr.horizons.slimefunx.block.SlimefunBlock;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Map;

public interface IUsable {

    void interactEvent(PlayerInteractEvent e);
    void breakBlockEvent(Player p, Block block);
    Map<Material, Integer> vanillaBlockBreakTime();
    Map<SlimefunBlock, Integer> slimefunBlockBreakTime();
}
