package fr.horizons.slimefunx.gui;

import net.minecraft.server.v1_13_R2.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.List;

import static fr.horizons.slimefunx.base.SlimefunObject.NBT_SLIMEFUN_ID;

public abstract class SlimefunGuiResource extends SlimefunGui {
    private int textureId;
    private String name;
    private List<String> lore;

    public SlimefunGuiResource(String id, int textureId, @Nullable String name, @Nullable List<String> lore) {
        super(id);
        this.textureId = textureId;
        this.name = name;
        this.lore = lore;
    }

    public SlimefunGuiResource(String id, int textureId) {
        super(id);
        this.textureId = textureId;
        this.name = null;
        this.lore = null;
    }

    public boolean hasCustomTexture() {
        return this.textureId != -1;
    }

    public ItemStack getCustomTexture() {
        if (!hasCustomTexture()) return new ItemStack(Material.AIR);
        ItemStack is = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta im = is.getItemMeta();
        im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS);
        im.setDisplayName(name == null ? "§0" : "§f" + name);
        if (lore != null) im.setLore(lore);
        is.setItemMeta(im);

        net.minecraft.server.v1_13_R2.ItemStack nmsStack = CraftItemStack.asNMSCopy(is);
        NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
        compound.setString(NBT_SLIMEFUN_ID, getId());
        // Bind to custom texture, if textureId equals -1, custom texture is not loaded
        if (this.textureId != -1) {
            compound.setInt("Damage", textureId);
            compound.setBoolean("Unbreakable", true);
        }
        nmsStack.setTag(compound);
        return CraftItemStack.asBukkitCopy(nmsStack);
    }
}
