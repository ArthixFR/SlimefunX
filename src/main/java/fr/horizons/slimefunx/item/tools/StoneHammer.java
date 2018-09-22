package fr.horizons.slimefunx.item.tools;

import com.google.common.collect.ImmutableMap;
import fr.horizons.slimefunx.block.SlimefunBlock;
import fr.horizons.slimefunx.item.SlimefunTool;
import fr.horizons.slimefunx.list.BlockList;
import fr.horizons.slimefunx.list.Categories;
import fr.horizons.slimefunx.util.Durability;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Map;

public class StoneHammer extends SlimefunTool {
    public StoneHammer() {
        super("stone_hammer", Categories.TOOLS, new ItemStack(Material.DIAMOND_HOE), 3, "§fStone hammer", null);
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
    public void breakBlockEvent(Player p, Block b) {
        Location loc = b.getLocation();
        PlayerInventory playerInventory = p.getInventory();
        switch (b.getType()) {
            case COBBLESTONE:
                loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.GRAVEL, 1));
                b.setType(Material.AIR);
                break;
            case GRAVEL:
                loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.SAND, 1));
                b.setType(Material.AIR);
                break;
            case SAND:
                loc.getWorld().dropItemNaturally(loc, BlockList.DUST_BLOCK.getItem());
                b.setType(Material.AIR);
                break;
        }
        if (hasDurability()) playerInventory.setItemInMainHand(Durability.setDurability(playerInventory.getItemInMainHand(), Durability.getDurability(playerInventory.getItemInMainHand()) - 1));
    }

    @Override
    public Map<Material, Integer> vanillaBlockBreakTime() {
        return ImmutableMap.<Material, Integer>builder()
                .put(Material.COBBLESTONE, 10)
                .build();
    }

    @Override
    public Map<SlimefunBlock, Integer> slimefunBlockBreakTime() {
        return ImmutableMap.<SlimefunBlock, Integer>builder()
                .put(BlockList.DUST_BLOCK, 10)
                .build();
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
