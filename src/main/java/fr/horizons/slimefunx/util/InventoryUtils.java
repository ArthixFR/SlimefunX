package fr.horizons.slimefunx.util;

import net.minecraft.server.v1_13_R2.NBTTagCompound;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.stream.IntStream;

public class InventoryUtils {

    public static int checkFirstEmpty(int slotStart, int slotEnd, Inventory inv) {
        ItemStack[] content = IntStream.range(slotStart, slotEnd).boxed().map(inv::getItem).toArray(ItemStack[]::new);

        int i = slotStart;
        for (ItemStack is : content) {
            if (is == null) return i;
            i++;
        }
        return -1;
    }

    public static NBTTagCompound convertItemStackToCompound(ItemStack is) {
        NBTTagCompound nbt = new NBTTagCompound();

        nbt.setByte("Count", (byte) is.getAmount());
        nbt.setString("id", is.getType().getKey().toString());
        if (is.hasItemMeta()) {
            ItemMeta im = is.getItemMeta();
            nbt.set("tag", new NBTTagCompound());
            NBTTagCompound tag = nbt.getCompound("tag");
            tag.setInt("Damage", is.getDurability());
            if (im.isUnbreakable()) tag.setBoolean("Unbreakable", true);
        }

        return nbt;
    }
}
