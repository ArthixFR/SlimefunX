package fr.poslovitch.slimefunx.managers;

import fr.poslovitch.slimefunx.SlimefunX;
import fr.poslovitch.slimefunx.api.items.SlimefunItem;
import org.apache.commons.lang.Validate;

import java.util.LinkedList;
import java.util.List;

public class ItemsManager {

    private final SlimefunX plugin;
    private final List<SlimefunItem> items = new LinkedList<>();

    public ItemsManager(SlimefunX plugin) {
        this.plugin = plugin;
    }

    public void registerItems() {

    }

    public void registerItem(SlimefunItem item) {
        Validate.notNull(item, "The item cannot be null.");

        try {

        } catch (Exception e) {
            plugin.getLogger().severe("Item registration failed: " + item.getId());
        }
    }
}
