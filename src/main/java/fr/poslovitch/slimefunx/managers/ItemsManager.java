package fr.poslovitch.slimefunx.managers;

import fr.poslovitch.slimefunx.SlimefunX;
import fr.poslovitch.slimefunx.api.interfaces.Interactable;
import fr.poslovitch.slimefunx.api.items.SlimefunItem;
import fr.poslovitch.slimefunx.lists.SlimefunItems;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;

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

    public void registerItems() {
        Arrays.stream(SlimefunItems.class.getDeclaredFields())
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
            if (item instanceof Interactable) {
                Bukkit.getPluginManager().registerEvents(((Interactable) item).getInteractionListener(), plugin);
            }
        } catch (Exception e) {
            plugin.getLogger().severe("Item registration failed: " + item.getId());
        }
    }
}
