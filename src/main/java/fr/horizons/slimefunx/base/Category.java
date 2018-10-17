package fr.horizons.slimefunx.base;

import fr.horizons.slimefunx.interfaces.IIdentifiable;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Category implements IIdentifiable {

    private final String id;
    private String name;
    private List<String> lore;
    private ItemStack icon;

    public Category(String id, ItemStack icon, String name, List<String> lore) {
        this.id = id;
        this.icon = icon;
        this.name = name;
        this.lore = lore;

        ItemMeta im = this.getIcon().getItemMeta();
        im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ENCHANTS);

        if (name != null) {
            this.name = "§f" + name;
            im.setDisplayName(this.name);
        }
        if (lore != null) {
            this.lore = lore;
            im.setLore(lore);
        }
        this.getIcon().setItemMeta(im);
        this.setIcon(this.getIcon());
    }

    @Override
    public String getId() {
        return id;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public List<String> getLore() {
        return lore;
    }

    public String getName() {
        return name;
    }

    public void setIcon(ItemStack icon) {
        this.icon = icon;
    }
}
