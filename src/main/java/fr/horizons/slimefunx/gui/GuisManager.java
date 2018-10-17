package fr.horizons.slimefunx.gui;

import fr.horizons.slimefunx.SlimefunX;
import fr.horizons.slimefunx.list.GuiList;
import org.apache.commons.lang.Validate;

import java.util.*;

public class GuisManager {
    private final SlimefunX plugin;
    private final Map<String, SlimefunGui> guis = new LinkedHashMap<>();
    private final Map<String, SlimefunGuiResource> guiResources = new LinkedHashMap<>();

    public GuisManager(SlimefunX plugin) {
        this.plugin = plugin;
    }

    public Map<String, SlimefunGui> getGuis() {
        return guis;
    }

    public Set<SlimefunGui> getGuisSet() {
        return new HashSet<>(guis.values());
    }

    public Map<String, SlimefunGuiResource> getGuiResources() {
        return guiResources;
    }

    public Set<SlimefunGuiResource> getGuiResourcesSet() {
        return new HashSet<>(guiResources.values());
    }

    public SlimefunGuiResource getGuiResourceById(String id) {
        return guiResources.get(id);
    }

    public SlimefunGui getGuiById(String id) {
        return guis.get(id);
    }

    public void registerGuis() {
        Arrays.stream(GuiList.class.getDeclaredFields())
                .filter(field -> SlimefunGui.class.isAssignableFrom(field.getType()))
                .forEach(field -> {
                    try {
                        registerGui((SlimefunGui) field.get(null));
                    } catch (Exception e) {
                        plugin.getLogger().info("Skipping '" + field.getName() + "' due to IllegalAccessException.");
                    }
                });
    }

    private void registerGui(SlimefunGui gui) {
        Validate.notNull(gui, "The gui cannot be null.");

        try {
            if (gui instanceof SlimefunGuiResource) guiResources.put(gui.getId(), (SlimefunGuiResource) gui);
            else guis.put(gui.getId(), gui);
        } catch (Exception e) {
            plugin.getLogger().severe("Gui registration failed: " + gui.getId());
        }
    }

}
