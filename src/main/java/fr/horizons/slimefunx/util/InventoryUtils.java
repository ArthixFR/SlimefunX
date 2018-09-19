package fr.horizons.slimefunx.util;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

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
}
