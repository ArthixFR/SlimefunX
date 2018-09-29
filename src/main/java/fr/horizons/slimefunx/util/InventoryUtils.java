package fr.horizons.slimefunx.util;

import fr.horizons.slimefunx.base.SlimefunObject;
import net.minecraft.server.v1_13_R2.*;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_13_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.stream.IntStream;

public class InventoryUtils {

    public static int checkFirstEmpty(int slotStart, int slotEnd, Inventory inv) {
        ItemStack[] content = IntStream.rangeClosed(slotStart, slotEnd).boxed().map(inv::getItem).toArray(ItemStack[]::new);

        int i = slotStart;
        for (ItemStack is : content) {
            if (is == null) return i;
            i++;
        }
        return -1;
    }

    public static NBTTagCompound convertItemStackToCompound(ItemStack is, String id) {
        NBTTagCompound nbt = new NBTTagCompound();

        nbt.setByte("Count", (byte) is.getAmount());
        nbt.setString("id", is.getType().getKey().toString());
        nbt.setString(SlimefunObject.NBT_SLIMEFUN_ID, id);
        if (is.hasItemMeta()) {
            ItemMeta im = is.getItemMeta();
            nbt.set("tag", new NBTTagCompound());
            NBTTagCompound tag = nbt.getCompound("tag");
            tag.setInt("Damage", is.getDurability());
            if (im.isUnbreakable()) tag.setBoolean("Unbreakable", true);
        }

        return nbt;
    }

    public static boolean isEqual(SlimefunObject slimefunObject, ItemStack is) {
        if (is == null || !is.hasItemMeta()) return false;
        net.minecraft.server.v1_13_R2.ItemStack nmsStack = CraftItemStack.asNMSCopy(is);
        NBTTagCompound compound = nmsStack.getTag();
        if (compound == null) return false;
        if (compound.hasKey(SlimefunObject.NBT_SLIMEFUN_ID)) return compound.getString(SlimefunObject.NBT_SLIMEFUN_ID).equals(slimefunObject.getId());
        return false;
    }

    public static void setBlockData(Block b, String key, Object value) {
        TileEntity te = ((CraftWorld)b.getWorld()).getHandle().getTileEntity(new BlockPosition(b.getX(), b.getY(), b.getZ()));
        if (!(te instanceof TileEntityMobSpawner)) return;
        NBTTagCompound compound = te.aa_();
        if (compound != null) {
            if (value == null) {
                compound.getCompound("SpawnData").getList("ArmorItems", NBTListType.COMPOUND).getCompound(3).remove(key);
                te.load(compound);
                te.update();
                b.getState().update();
                return;
            }

            NBTBase nbtBase;
            if (value instanceof Byte) {
                nbtBase = new NBTTagByte((Byte) value);
            } else if (value instanceof Short) {
                nbtBase = new NBTTagShort((Short) value);
            } else if (value instanceof Integer) {
                nbtBase = new NBTTagInt((int) value);
            } else if (value instanceof Long) {
                nbtBase = new NBTTagLong((long) value);
            } else if (value instanceof Float) {
                nbtBase = new NBTTagFloat((float) value);
            } else if (value instanceof Double) {
                nbtBase = new NBTTagDouble((double) value);
            } else if (value instanceof Byte[]) {
                nbtBase = new NBTTagByteArray((byte[]) value);
            } else if (value instanceof String) {
                nbtBase = new NBTTagString((String) value);
            } else if (value instanceof NBTTagList) {
                nbtBase = (NBTTagList) value;
            } else if (value instanceof NBTTagCompound) {
                nbtBase = (NBTTagCompound) value;
            } else if (value instanceof Integer[]) {
                nbtBase = new NBTTagIntArray((int[]) value);
            } else return;
            compound.getCompound("SpawnData").getList("ArmorItems", NBTListType.COMPOUND).getCompound(3).set(key, nbtBase);
            te.load(compound);
            te.update();
            b.getState().update();
        }
    }

    public static Object getBlockData(Block b, String key, int listType) {
        TileEntity te = ((CraftWorld)b.getWorld()).getHandle().getTileEntity(new BlockPosition(b.getX(), b.getY(), b.getZ()));
        if (!(te instanceof TileEntityMobSpawner)) return null;
        NBTTagCompound compound = te.aa_();
        if (compound != null) {
            NBTBase nbtBase = compound.getCompound("SpawnData").getList("ArmorItems", NBTListType.COMPOUND).getCompound(3).get(key);
            if (nbtBase == null) return null;
            NBTTagCompound itemCompound = compound.getCompound("SpawnData").getList("ArmorItems", NBTListType.COMPOUND).getCompound(3);
            if (nbtBase instanceof NBTTagByte) {
                return itemCompound.getByte(key);
            } else if (nbtBase instanceof NBTTagShort) {
                return itemCompound.getShort(key);
            } else if (nbtBase instanceof NBTTagInt) {
                return itemCompound.getInt(key);
            } else if (nbtBase instanceof NBTTagLong) {
                return itemCompound.getLong(key);
            } else if (nbtBase instanceof NBTTagFloat) {
                return itemCompound.getFloat(key);
            } else if (nbtBase instanceof NBTTagDouble) {
                return itemCompound.getDouble(key);
            } else if (nbtBase instanceof NBTTagByteArray) {
                return itemCompound.getByteArray(key);
            } else if (nbtBase instanceof NBTTagString) {
                return itemCompound.getString(key);
            } else if (nbtBase instanceof NBTTagList) {
                if (listType == -1) return null;
                return itemCompound.getList(key, listType);
            } else if (nbtBase instanceof NBTTagCompound) {
                return itemCompound.getCompound(key);
            } else if (nbtBase instanceof NBTTagIntArray) {
                return itemCompound.getIntArray(key);
            }
        }
        return null;
    }

    public static Object getBlockData(Block b, String key) {
        return getBlockData(b, key, -1);
    }
}
