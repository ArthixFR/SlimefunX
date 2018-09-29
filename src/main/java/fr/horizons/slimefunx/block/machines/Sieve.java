package fr.horizons.slimefunx.block.machines;

import fr.horizons.slimefunx.base.Category;
import fr.horizons.slimefunx.base.SlimefunObject;
import fr.horizons.slimefunx.block.SlimefunMachine;
import fr.horizons.slimefunx.list.BlockList;
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

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: COOLDOWN POUR EVITER LE SPAM CLICK

public class Sieve extends SlimefunMachine {
    public Sieve(String id, Category category, ItemStack item, int textureId, @Nullable String name, @Nullable List<String> lore) {
        super(id, category, item, textureId, name, lore);
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
    public void interactEvent(PlayerInteractEvent e) {
        Block b = e.getClickedBlock();
        NBTTagCompound nbtTagCompound = (NBTTagCompound) InventoryUtils.getBlockData(b, "SieveData");

        if (nbtTagCompound == null && InventoryUtils.isEqual(BlockList.DUST_BLOCK, e.getItem())) {
            NBTTagCompound compound = new NBTTagCompound();
            compound.setInt("progress", 0);
            compound.setString("block", BlockList.DUST_BLOCK.getId());
            InventoryUtils.setBlockData(b, "SieveData", compound);
            animate(b, 0);
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
                animate(b, progress + 1);
                if ((progress + 1) < 8) {
                    nbtTagCompound.setInt("progress", progress + 1);
                    InventoryUtils.setBlockData(b, "SieveData", nbtTagCompound);
                } else {
                    InventoryUtils.setBlockData(b, "SieveData", null);
                    Map<ItemStack, Double> lootTable = new HashMap<>();
                    if (material.equals("dust_block")) {
                        lootTable.put(ItemList.SIFTED_DUST.getItem(), 80.0);
                        lootTable.put(new ItemStack(Material.CLAY_BALL), 15.0);
                        lootTable.put(new ItemStack(Material.AIR, 1), 5.0);
                    }
                    ItemStack itemStack = MathUtils.getItemByChance(lootTable);
                    if (!itemStack.getType().equals(Material.AIR)) e.getClickedBlock().getWorld().dropItemNaturally(LocationUtils.getBlockCenter(e.getClickedBlock().getLocation()).add(0, 1.0, 0), itemStack);
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

    private void animate(Block b, int progress) {
        InventoryUtils.setBlockData(b, "id", progress < 8 ? "minecraft:diamond_pickaxe" : "minecraft:diamond_axe");
        NBTTagCompound compound = new NBTTagCompound();
        compound.setInt("Damage", progress < 8 ? progress + 1 : getTextureId());
        compound.setBoolean("Unbreakable", true);
        InventoryUtils.setBlockData(b, "tag", compound);
    }
}