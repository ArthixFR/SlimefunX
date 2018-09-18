package fr.horizons.slimefunx.item;

import fr.horizons.slimefunx.SlimefunX;
import fr.horizons.slimefunx.base.SlimefunObject;
import fr.horizons.slimefunx.list.ItemList;
import net.minecraft.server.v1_13_R2.NBTTagCompound;
import org.apache.commons.lang.Validate;
import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class ItemsManager {
    private final SlimefunX plugin;
    private final Map<String, SlimefunItem> items = new LinkedHashMap<>();

    public ItemsManager(SlimefunX plugin) {
        this.plugin = plugin;
    }

    public Map<String, SlimefunItem> getItems() {
        return items;
    }

    public Set<SlimefunItem> getItemsSet() {
        return new HashSet<>(items.values());
    }

    public Set<String> getIdsSet() {
        return items.keySet();
    }

    public SlimefunItem getItemById(String id) {
        return items.get(id);
    }

    public SlimefunItem getItemByTag(ItemStack is) {
        net.minecraft.server.v1_13_R2.ItemStack nmsStack = CraftItemStack.asNMSCopy(is);
        NBTTagCompound compound = nmsStack.getTag();
        final SlimefunItem[] sfi = {null};
        if (compound != null && compound.hasKey(SlimefunObject.NBT_SLIMEFUN_ID)) {
            String nbt = compound.getString(SlimefunObject.NBT_SLIMEFUN_ID);
            getItems().forEach((s, slimefunItem) -> {
                if (s.equalsIgnoreCase(nbt)) {
                    sfi[0] = slimefunItem;
                    return;
                }
            });
        }
        return sfi[0];
    }

    public void registerItems() {
        Arrays.stream(ItemList.class.getDeclaredFields())
                .filter(field -> SlimefunItem.class.isAssignableFrom(field.getType()))
                .forEach(field -> {
                    try {
                        registerItem((SlimefunItem) field.get(null));
                    } catch (Exception e) {
                        plugin.getLogger().info("Skipping '" + field.getName() + "' due to IllegalAccessException.");
                    }
                });
    }

    private void registerItem(SlimefunItem item) {
        Validate.notNull(item, "The item cannot be null.");

        try {
            items.put(item.getId(), item);
        } catch (Exception e) {
            plugin.getLogger().severe("Item registration failed: " + item.getId());
        }
    }
}
