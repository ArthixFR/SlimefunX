package fr.horizons.slimefunx.item;

import fr.horizons.slimefunx.base.Category;
import fr.horizons.slimefunx.base.SlimefunObject;
import fr.horizons.slimefunx.interfaces.ICraftable;
import fr.horizons.slimefunx.interfaces.IResearchable;
import fr.horizons.slimefunx.interfaces.IStackable;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.List;

public abstract class SlimefunItem extends SlimefunObject implements ICraftable, IResearchable, IStackable {

    private String name;
    private List<String> lore;

    public SlimefunItem(String id, Category category, ItemStack item, int textureId, @Nullable String name, @Nullable List<String> lore) {
        super(id, item, category, textureId);

        ItemMeta im = this.getItem().getItemMeta();
        im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
        if (name != null) {
            this.name = name;
            im.setDisplayName(name);
        }
        if (lore != null) {
            this.lore = lore;
            im.setLore(lore);
        }
        this.getItem().setItemMeta(im);
    }

    public String getName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
    }
}
