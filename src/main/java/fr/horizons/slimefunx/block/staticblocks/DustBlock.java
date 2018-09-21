package fr.horizons.slimefunx.block.staticblocks;

import com.google.common.collect.ImmutableMap;
import fr.horizons.slimefunx.base.SlimefunObject;
import fr.horizons.slimefunx.block.SlimefunStaticBlock;
import fr.horizons.slimefunx.list.Categories;
import fr.horizons.slimefunx.list.ItemList;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;


public class DustBlock extends SlimefunStaticBlock {

    public DustBlock() {
        super("dust_block", Categories.STATIC_BLOCKS, new ItemStack(Material.DIAMOND_AXE), 4, "§fDust Block", null);
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
    public void interactEvent(PlayerInteractEvent e) { }

    @Override
    public int baseTickBreakTime() {
        return 30;
    }

    @Override
    public Map<SlimefunObject, Integer> slimefunToolDamage() {
        return ImmutableMap.<SlimefunObject, Integer>builder()
                .put(ItemList.WOODEN_HAMMER, 15)
                .put(ItemList.STONE_HAMMER, 10)
                .build();
    }

    @Override
    public Map<Material, Integer> vanillaToolDamage() {
        return null;
    }
}
