package fr.horizons.slimefunx.util;

import net.minecraft.server.v1_13_R2.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class Durability {
    public static final String NBT_SLIMEFUN_CURRENT_DURABILITY = "slimefun_durability";
    public static final String NBT_SLIMEFUN_MAX_DURABILITY = "slimefun_max_durability";

    public static ItemStack init(ItemStack is, int max) {
        net.minecraft.server.v1_13_R2.ItemStack nmsStack = CraftItemStack.asNMSCopy(is);
        NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
        compound.setInt(NBT_SLIMEFUN_CURRENT_DURABILITY, max);
        compound.setInt(NBT_SLIMEFUN_MAX_DURABILITY, max);
        nmsStack.setTag(compound);
        return CraftItemStack.asBukkitCopy(nmsStack);
    }

    public static ItemStack setDurability(ItemStack is, int current) {
        System.out.println("Setting current dura to " + current);
        if (current <= 0) return new ItemStack(Material.AIR);
        net.minecraft.server.v1_13_R2.ItemStack nmsStack = CraftItemStack.asNMSCopy(is);
        NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
        compound.setInt(NBT_SLIMEFUN_CURRENT_DURABILITY, current);
        nmsStack.setTag(compound);
        return CraftItemStack.asBukkitCopy(nmsStack);
    }

    public static ItemStack setMaxDurability(ItemStack is, int max) {
        System.out.println("Setting max dura to " + max);
        net.minecraft.server.v1_13_R2.ItemStack nmsStack = CraftItemStack.asNMSCopy(is);
        NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
        compound.setInt(NBT_SLIMEFUN_MAX_DURABILITY, max);
        nmsStack.setTag(compound);
        return CraftItemStack.asBukkitCopy(nmsStack);
    }

    public static int getDurability(ItemStack is) {
        net.minecraft.server.v1_13_R2.ItemStack nmsStack = CraftItemStack.asNMSCopy(is);
        NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
        return compound.getInt(NBT_SLIMEFUN_CURRENT_DURABILITY);
    }

    public static int getMaxDurability(ItemStack is) {
        net.minecraft.server.v1_13_R2.ItemStack nmsStack = CraftItemStack.asNMSCopy(is);
        NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
        return compound.getInt(NBT_SLIMEFUN_CURRENT_DURABILITY);
    }
}
