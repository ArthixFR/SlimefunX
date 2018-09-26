package fr.horizons.slimefunx.util;

import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class MathUtils {
    public static int randomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static ItemStack getItemByChance(Map<ItemStack, Double> mapChance) {
        RandomCollection<ItemStack> randomCollection = new RandomCollection<>();

        mapChance.forEach((itemStack, chance) -> randomCollection.add(chance, itemStack));

        return randomCollection.next();
    }
}
