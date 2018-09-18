package fr.horizons.slimefunx.item.tools;

import fr.horizons.slimefunx.item.SlimefunTool;
import fr.horizons.slimefunx.list.BlockList;
import fr.horizons.slimefunx.list.Categories;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class StoneHammer extends SlimefunTool {
    public StoneHammer() {
        super("stone_hammer", Categories.TOOLS, new ItemStack(Material.DIAMOND_AXE), 3, "§fStone hammer", null);
    }

    @Override
    public String[][] getRecipePattern() {
        return new String[0][];
    }

    @Override
    public int getResearchCost() {
        return 5;
    }

    @Override
    public int getResearchTime(Player player) {
        return 0;
    }

    @Override
    public void interactEvent(PlayerInteractEvent e) {

    }

    @Override
    public void breakBlockEvent(BlockBreakEvent e) {
        Block b = e.getBlock();
        Location loc = b.getLocation();
        switch (b.getType()) {
            case COBBLESTONE:
                loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.GRAVEL, 1));
                e.getBlock().setType(Material.AIR);
                break;
            case GRAVEL:
                loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.SAND, 1));
                e.getBlock().setType(Material.AIR);
                break;
            case SAND:
                loc.getWorld().dropItemNaturally(loc, BlockList.DUST_BLOCK.getItem());
                e.getBlock().setType(Material.AIR);
                break;
        }
    }

    @Override
    public boolean hasDurability() {
        return true;
    }

    @Override
    public int maxDurability() {
        return 50;
    }

    @Override
    public int stackSize() {
        return 1;
    }
}
