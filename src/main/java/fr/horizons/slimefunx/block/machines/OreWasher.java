package fr.horizons.slimefunx.block.machines;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import fr.horizons.slimefunx.base.Category;
import fr.horizons.slimefunx.base.SlimefunObject;
import fr.horizons.slimefunx.block.SlimefunMachine;
import fr.horizons.slimefunx.list.ItemList;
import fr.horizons.slimefunx.util.CraftingType;
import fr.horizons.slimefunx.util.InventoryUtils;
import fr.horizons.slimefunx.util.LocationUtils;
import fr.horizons.slimefunx.util.MathUtils;
import net.minecraft.server.v1_13_R2.NBTTagCompound;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Levelled;
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
    public Table<CraftingType, Integer, ItemStack[]> getRecipesPatterns() {
        return ImmutableTable.<CraftingType, Integer, ItemStack[]>builder()
                .put(CraftingType.CRAFTING_TABLE, 1,
                        new ItemStack[]{
                                new ItemStack(Material.IRON_INGOT), null, new ItemStack(Material.IRON_INGOT),
                                new ItemStack(Material.IRON_INGOT), new ItemStack(Material.COBWEB), new ItemStack(Material.IRON_INGOT),
                                new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT)
                        })
                .build();
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
        if (e.getItem() == null) return;
        if (e.getItem().getType().equals(Material.WATER_BUCKET)) {
            if (!hasWater(b)) {
                NBTTagCompound compound = new NBTTagCompound();
                compound.setInt("progress", 0);
                InventoryUtils.setBlockData(b, "OreWasherData", compound);
                animate(b, 0);

                ItemStack emptyBukket = new ItemStack(Material.BUCKET);
                if (e.getHand() == EquipmentSlot.HAND) e.getPlayer().getInventory().setItemInMainHand(emptyBukket);
                else e.getPlayer().getInventory().setItemInOffHand(emptyBukket);
            }
        } else if (InventoryUtils.isEqual(ItemList.SIFTED_DUST, e.getItem())) {
            NBTTagCompound nbtTagCompound = (NBTTagCompound) InventoryUtils.getBlockData(b, "OreWasherData");
            boolean hasWaterAround = hasWaterAround(b);
            if (hasWater(b) || hasWaterAround) {
                if (!hasWaterAround(b)) {
                    int progress = nbtTagCompound.getInt("progress");
                    animate(b, progress + 1);
                    nbtTagCompound.setInt("progress", progress + 1);
                    InventoryUtils.setBlockData(b, "OreWasherData", nbtTagCompound);
                } else {
                    NBTTagCompound compound = new NBTTagCompound();
                    compound.setInt("progress", 0);
                    InventoryUtils.setBlockData(b, "OreWasherData", compound);
                    animate(b, 0);
                }

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
    }

    @Override
    public int getResearchCost() {
        return 0;
    }

    private void animate(Block b, int progress) {
        if (progress > 2) InventoryUtils.setBlockData(b, "OreWasherData", null);
        InventoryUtils.setBlockData(b, "id", progress < 3 ? "minecraft:diamond_pickaxe" : "minecraft:diamond_axe");
        NBTTagCompound compound = new NBTTagCompound();
        compound.setInt("Damage", progress < 3 ? progress + 9 : getTextureId());
        compound.setBoolean("Unbreakable", true);
        InventoryUtils.setBlockData(b, "tag", compound);
    }

    private boolean hasWater(Block b) {
        NBTTagCompound nbtTagCompound = (NBTTagCompound) InventoryUtils.getBlockData(b, "OreWasherData");
        if (nbtTagCompound == null) return false;
        if (nbtTagCompound.getInt("progress") > 2) {
            InventoryUtils.setBlockData(b, "OreWasherData", null);
            return false;
        }
        return true;
    }

    private boolean hasWaterAround(Block b) {
        Location loc = b.getLocation();
        Block block1 = loc.getWorld().getBlockAt(loc.clone().add(1, 0, 0));
        Block block2 = loc.getWorld().getBlockAt(loc.clone().add(-1, 0, 0));
        Block block3 = loc.getWorld().getBlockAt(loc.clone().add(0, 0, 1));
        Block block4 = loc.getWorld().getBlockAt(loc.clone().add(0, 0, -1));

        if ((block1.getType() == Material.WATER && ((Levelled) block1.getBlockData()).getLevel() == 0)
                && (block2.getType() == Material.WATER && ((Levelled) block2.getBlockData()).getLevel() == 0)
                && (block3.getType() == Material.WATER && ((Levelled) block3.getBlockData()).getLevel() == 0)
                && (block4.getType() == Material.WATER && ((Levelled) block4.getBlockData()).getLevel() == 0)) {
            return true;
        }

        return false;
    }
}
