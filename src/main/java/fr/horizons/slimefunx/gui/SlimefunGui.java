package fr.horizons.slimefunx.gui;

import fr.horizons.slimefunx.interfaces.IIdentifiable;
import org.apache.commons.lang.Validate;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class SlimefunGui implements IIdentifiable {
    private String id;

    public SlimefunGui(String id) {
        Validate.notNull(id, "Id cannot be null.");

        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    public void openGui(Player p, ItemStack is) {}
}
