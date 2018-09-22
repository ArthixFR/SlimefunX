package fr.horizons.slimefunx.item.tools;

import com.google.common.collect.ImmutableMap;
import fr.horizons.slimefunx.base.SlimefunObject;
import fr.horizons.slimefunx.block.SlimefunBlock;
import fr.horizons.slimefunx.item.SlimefunTool;
import fr.horizons.slimefunx.list.BlockList;
import fr.horizons.slimefunx.list.Categories;
import fr.horizons.slimefunx.list.ItemList;
import fr.horizons.slimefunx.util.Durability;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Map;

public class WoodenHammer extends SlimefunTool {

    public WoodenHammer() {
        super("wooden_hammer", Categories.TOOLS, new ItemStack(Material.DIAMOND_HOE), 2, "§fWooden hammer", null);
    }

    @Override
    public String[][] getRecipePattern() {
        return new String[][]{
                {"", "OAK_PLANKS", "OAK_PLANKS"},
                {"", "STICK", "OAK_PLANKS"},
                {"STICK", "", ""}
        };
    }

    @Override
    public int getResearchCost() {
        return 4;
    }

    @Override
    public int getResearchTime(Player player) {
        return 0;
    }

    @Override
    public void interactEvent(PlayerInteractEvent e) {
        //e.getPlayer().sendMessage("Interact !");
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
                .put(Material.COBBLESTONE, 15)
                .build();
    }

    @Override
    public Map<SlimefunBlock, Integer> slimefunBlockBreakTime() {
        return ImmutableMap.<SlimefunBlock, Integer>builder()
                .put(BlockList.DUST_BLOCK, 15)
                .build();
    }

    @Override
    public boolean hasDurability() {
        return true;
    }

    @Override
    public int maxDurability() {
        return 30;
    }

    @Override
    public int stackSize() {
        return 1;
    }
}
