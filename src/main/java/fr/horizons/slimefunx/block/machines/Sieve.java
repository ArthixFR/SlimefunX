package fr.horizons.slimefunx.block.machines;

import fr.horizons.slimefunx.base.SlimefunObject;
import fr.horizons.slimefunx.block.SlimefunMachine;
import fr.horizons.slimefunx.list.BlockList;
import fr.horizons.slimefunx.list.Categories;
import fr.horizons.slimefunx.list.ItemList;
import fr.horizons.slimefunx.util.InventoryUtils;
import fr.horizons.slimefunx.util.LocationUtils;
import fr.horizons.slimefunx.util.MathUtils;
import net.minecraft.server.v1_13_R2.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

// TODO: COOLDOWN POUR EVITER LE SPAM CLICK

public class Sieve extends SlimefunMachine {
    public Sieve() {
        super("sieve", Categories.MACHINES, new ItemStack(Material.DIAMOND_AXE), 3, "§fSieve", null);
    }

    @Override
    public int baseTickBreakTime() {
        return 40;
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
    public void interactEvent(PlayerInteractEvent e) { // TODO: ANIMATION !
        Block b = e.getClickedBlock();
        NBTTagCompound nbtTagCompound = (NBTTagCompound) InventoryUtils.getBlockData(b, "SieveData");

        if (nbtTagCompound == null && (e.getMaterial().equals(Material.GRAVEL) || e.getMaterial().equals(Material.SAND) || InventoryUtils.isEqual(BlockList.DUST_BLOCK, e.getItem()))) {
            NBTTagCompound compound = new NBTTagCompound();
            compound.setInt("progress", 0);
            compound.setString("block", (InventoryUtils.isEqual(BlockList.DUST_BLOCK, e.getItem()) ? BlockList.DUST_BLOCK.getId() : e.getMaterial().getKey().toString()));
            InventoryUtils.setBlockData(b, "SieveData", compound);
            if (e.getItem().getAmount() != 1) {
                e.getItem().setAmount(e.getItem().getAmount() - 1);
            } else {
                if (e.getHand() == EquipmentSlot.HAND) e.getPlayer().getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                else e.getPlayer().getInventory().setItemInOffHand(new ItemStack(Material.AIR));
            }
        } else {
            if (nbtTagCompound != null) {
                int progress = nbtTagCompound.getInt("progress");
                String material = nbtTagCompound.getString("block");
                if (progress < 8) { // TODO: RENDRE CA VARIABLE ?
                    nbtTagCompound.setInt("progress", progress + 1);
                    InventoryUtils.setBlockData(b, "SieveData", nbtTagCompound);
                } else {
                    InventoryUtils.setBlockData(b, "SieveData", null);
                    Map<ItemStack, Double> lootTable = new HashMap<>();
                    if (material.equals("minecraft:gravel")) {
                        lootTable.put(new ItemStack(Material.FLINT, 1), 33.0);
                        lootTable.put(ItemList.IRON_CHUNK_ORE.getItem(), 25.0);
                        lootTable.put(new ItemStack(Material.COAL, 1), 14.0);
                        lootTable.put(ItemList.GOLD_CHUNK_ORE.getItem(), 10.0);
                        lootTable.put(new ItemStack(Material.LAPIS_LAZULI, 1), 5.2);
                        lootTable.put(new ItemStack(Material.EMERALD, 1), 0.67);
                        lootTable.put(new ItemStack(Material.DIAMOND, 1), 0.78);
                    }
                    e.getClickedBlock().getWorld().dropItemNaturally(LocationUtils.getBlockCenter(e.getClickedBlock().getLocation()).add(0, 1.0, 0), MathUtils.getItemByChance(lootTable));
                }
            }
        }
    }

    @Override
    public int getResearchCost() {
        return 0;
    }

    @Override
    public int stackSize() {
        return 16;
    }
}