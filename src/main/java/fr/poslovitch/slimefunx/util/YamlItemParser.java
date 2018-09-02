package fr.poslovitch.slimefunx.util;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class YamlItemParser {

    public static ItemStack parseYaml(ConfigurationSection item) {
        Material material = Material.valueOf(item.getString("material", "STONE"));

        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setLocalizedName(item.getString("name"));
        itemMeta.setLore(item.getStringList("lore"));

        for (String enchantment : item.getConfigurationSection("enchantments").getKeys(true)) {
            itemMeta.addEnchant(Enchantment.getByKey(new NamespacedKey("minecraft", enchantment.toLowerCase())), item.getInt("enchantments." + enchantment), true);
        }

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}
