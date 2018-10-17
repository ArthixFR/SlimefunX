package fr.horizons.slimefunx.item.special;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import fr.horizons.slimefunx.base.Category;
import fr.horizons.slimefunx.block.SlimefunBlock;
import fr.horizons.slimefunx.item.SlimefunTool;
import fr.horizons.slimefunx.list.GuiList;
import fr.horizons.slimefunx.util.CraftingType;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class SlimefunGuide extends SlimefunTool {
    public SlimefunGuide(String id, Category category, ItemStack item, int textureId, @Nullable String name, @Nullable List<String> lore) {
        super(id, category, item, textureId, name, lore);
    }

    @Override
    public Table<CraftingType, Integer, ItemStack[]> getRecipesPatterns() {
        return ImmutableTable.<CraftingType, Integer, ItemStack[]>builder()
                .put(CraftingType.CRAFTING_TABLE, 1,
                        new ItemStack[]{
                                null, new ItemStack(Material.LEVER), null,
                                new ItemStack(Material.REDSTONE), new ItemStack(Material.BOOK), new ItemStack(Material.REDSTONE),
                                null, new ItemStack(Material.REDSTONE), null
                        })
                .build();
    }

    @Override
    public int getResearchCost() {
        return 0;
    }

    @Override
    public int stackSize() {
        return 1;
    }

    @Override
    public void interactEvent(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            e.setCancelled(true);
            GuiList.SLIMEFUN_GUIDE.openGui(e.getPlayer(), e.getItem());
        }
    }

    @Override
    public void breakBlockEvent(Player p, Block block) {

    }

    @Override
    public Map<Material, Integer> vanillaBlockBreakTime() {
        return null;
    }

    @Override
    public Map<SlimefunBlock, Integer> slimefunBlockBreakTime() {
        return null;
    }
}
