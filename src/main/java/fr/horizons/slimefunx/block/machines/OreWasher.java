package fr.horizons.slimefunx.block.machines;

import fr.horizons.slimefunx.base.Category;
import fr.horizons.slimefunx.base.SlimefunObject;
import fr.horizons.slimefunx.block.SlimefunMachine;
import fr.horizons.slimefunx.list.ItemList;
import fr.horizons.slimefunx.util.InventoryUtils;
import fr.horizons.slimefunx.util.LocationUtils;
import fr.horizons.slimefunx.util.MathUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OreWasher extends SlimefunMachine {
    public OreWasher(String id, Category category, ItemStack item, int textureId, @Nullable String name, @Nullable List<String> lore) {
        super(id, category, item, textureId, name, lore);
    }

    @Override
    public int baseTickBreakTime() {
        return 0;
    }

    @Override
    public Map<SlimefunObject, Integer> slimefunToolDamage() {
        return null;
    }

    @Override
    public Map<Material, Integer> vanillaToolDamage() {
        return null;
    }

    @Override
    public String[][] getRecipePattern() {
        return new String[0][];
    }

    @Override
    public Listener getInteractionListener() {
        return null;
    }

    @Override
    public boolean isPlaceable() {
        return true;
    }

    @Override
    public void interactEvent(PlayerInteractEvent e) {
        if (InventoryUtils.isEqual(ItemList.SIFTED_DUST, e.getItem())) {
            if (e.getItem().getAmount() != 1) {
                e.getItem().setAmount(e.getItem().getAmount() - 1);
            } else {
                if (e.getHand() == EquipmentSlot.HAND) e.getPlayer().getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                else e.getPlayer().getInventory().setItemInOffHand(new ItemStack(Material.AIR));
            }

            Map<ItemStack, Double> lootTable = new HashMap<>();
            lootTable.put(ItemList.IRON_DUST.getItem(), 20.0);
            lootTable.put(ItemList.TIN_DUST.getItem(), 15.0);
            lootTable.put(ItemList.COPPER_DUST.getItem(), 15.0);
            lootTable.put(ItemList.ALUMINIUM_DUST.getItem(), 15.0);
            lootTable.put(ItemList.ZINC_DUST.getItem(), 10.0);
            lootTable.put(ItemList.SILICIUM.getItem(), 10.0);
            lootTable.put(ItemList.MAGNESIUM_DUST.getItem(), 8.0);
            lootTable.put(ItemList.LEAD_DUST.getItem(), 5.0);
            lootTable.put(ItemList.SILVER_DUST.getItem(), 5.0);
            lootTable.put(ItemList.GOLD_DUST.getItem(), 1.0);

            ItemStack itemStack = MathUtils.getItemByChance(lootTable);
            if (!itemStack.getType().equals(Material.AIR)) e.getClickedBlock().getWorld().dropItemNaturally(LocationUtils.getBlockCenter(e.getClickedBlock().getLocation()).add(0, 1.0, 0), itemStack);
        }
    }

    @Override
    public int getResearchCost() {
        return 0;
    }
}
