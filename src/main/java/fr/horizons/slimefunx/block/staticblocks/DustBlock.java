package fr.horizons.slimefunx.block.staticblocks;

import com.google.common.collect.Table;
import fr.horizons.slimefunx.base.Category;
import fr.horizons.slimefunx.base.SlimefunObject;
import fr.horizons.slimefunx.block.SlimefunStaticBlock;
import fr.horizons.slimefunx.list.Categories;
import fr.horizons.slimefunx.util.CraftingType;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;


public class DustBlock extends SlimefunStaticBlock {
    public DustBlock(String id, Category category, ItemStack item, int textureId, @Nullable String name, @Nullable List<String> lore) {
        super(id, category, item, textureId, name, lore);
    }

    @Override
    public Table<CraftingType, Integer, ItemStack[]> getRecipesPatterns() {
        return null;
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
        return null;
    }

    @Override
    public Map<Material, Integer> vanillaToolDamage() {
        return null;
    }
}
