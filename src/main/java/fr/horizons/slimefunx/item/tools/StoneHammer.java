package fr.horizons.slimefunx.item.tools;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import fr.horizons.slimefunx.base.Category;
import fr.horizons.slimefunx.block.SlimefunBlock;
import fr.horizons.slimefunx.item.SlimefunTool;
import fr.horizons.slimefunx.list.BlockList;
import fr.horizons.slimefunx.list.Categories;
import fr.horizons.slimefunx.util.CraftingType;
import fr.horizons.slimefunx.util.Durability;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class StoneHammer extends SlimefunTool {
    public StoneHammer(String id, Category category, ItemStack item, int textureId, @Nullable String name, @Nullable List<String> lore) {
        super(id, category, item, textureId, name, lore);
    }

    @Override
    public Table<CraftingType, Integer, ItemStack[]> getRecipesPatterns() {
        return ImmutableTable.<CraftingType, Integer, ItemStack[]>builder()
                .put(CraftingType.CRAFTING_TABLE, 1,
                        new ItemStack[]{
                                null, new ItemStack(Material.COBBLESTONE), new ItemStack(Material.COBBLESTONE),
                                null, new ItemStack(Material.STICK), new ItemStack(Material.COBBLESTONE),
                                new ItemStack(Material.STICK), null, null
                        })
                .build();
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
