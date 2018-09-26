package fr.horizons.slimefunx.block.machines;

import fr.horizons.slimefunx.base.SlimefunObject;
import fr.horizons.slimefunx.block.SlimefunMachine;
import fr.horizons.slimefunx.list.BlockList;
import fr.horizons.slimefunx.list.Categories;
import fr.horizons.slimefunx.util.InventoryUtils;
import net.minecraft.server.v1_13_R2.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

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
        System.out.println(nbtTagCompound != null ? nbtTagCompound.getKeys() : "null");
        System.out.println(InventoryUtils.isEqual(BlockList.DUST_BLOCK, e.getItem()));
        if (e.getMaterial().equals(Material.GRAVEL) || e.getMaterial().equals(Material.SAND) || InventoryUtils.isEqual(BlockList.DUST_BLOCK, e.getItem())) {
            if (nbtTagCompound == null) {
                System.out.println("No progress !");
                NBTTagCompound compound = new NBTTagCompound();
                compound.setInt("progress", 0);
                compound.setString("block", (InventoryUtils.isEqual(BlockList.DUST_BLOCK, e.getItem()) ? BlockList.DUST_BLOCK.getId() : e.getMaterial().getKey().toString()));
                InventoryUtils.setBlockData(b, "SieveData", compound);
                if (e.getItem().getAmount() != 1) {
                    e.getItem().setAmount(e.getItem().getAmount() - 1);
                } else {
                    e.getItem().setType(Material.AIR);
                }
            }
        } else {
            if (nbtTagCompound != null) {
                int progress = nbtTagCompound.getInt("progress");
                System.out.println("Progress : " + progress);
                if (progress < 8) { // TODO: RENDRE CA VARIABLE ?
                    nbtTagCompound.setInt("progress", progress + 1);
                    InventoryUtils.setBlockData(b, "SieveData", nbtTagCompound);
                } else {
                    InventoryUtils.setBlockData(b, "SieveData", null);
                    // TODO: GIVE RANDOM RESSOURCE
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