package fr.horizons.slimefunx.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;

public class LocationUtils {

    public static Location getPlaceLocation(Location loc, BlockFace face) {
        switch (face) {
            case UP:
                return loc.add(0, 1, 0);
            case DOWN:
                return loc.add(0, -1, 0);
            case EAST:
                return loc.add(1, 0, 0);
            case WEST:
                return loc.add(-1, 0, 0);
            case NORTH:
                return loc.add(0, 0, -1);
            case SOUTH:
                return loc.add(0, 0, 1);
        }
        return loc;
    }

    public static boolean isPlaceEmpty(Location loc) {
        return loc.getBlock().getType().equals(Material.AIR) || loc.getBlock().getType().equals(Material.CAVE_AIR) || loc.getBlock().getType().equals(Material.VOID_AIR);
    }
}
