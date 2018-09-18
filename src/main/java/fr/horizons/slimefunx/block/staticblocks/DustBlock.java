package fr.horizons.slimefunx.block.staticblocks;

import fr.horizons.slimefunx.block.SlimefunStaticBlock;
import fr.horizons.slimefunx.list.Categories;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;


public class DustBlock extends SlimefunStaticBlock {

    public DustBlock() {
        super("dust_block", Categories.STATIC_BLOCKS, new ItemStack(Material.DIAMOND_AXE), 4, "§fDust", null);
    }

    @Override
    public String[][] getRecipePattern() {
        return new String[0][];
    }

    @Override
    public int getResearchCost() {
        return 0;
    }

    @Override
    public boolean isPlaceable() {
        return true;
    }

    @Override
    public void interactEvent(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            System.out.println("Clicked block : " + e.getClickedBlock().getType() + " on face :" + e.getBlockFace());
            //placeBlock(e.getClickedBlock().getLocation());
        }
    }

    @Override
    public int stackSize() {
        return 64;
    }
}
