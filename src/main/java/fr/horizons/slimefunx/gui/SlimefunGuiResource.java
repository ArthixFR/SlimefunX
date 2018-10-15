package fr.horizons.slimefunx.gui;

import net.minecraft.server.v1_13_R2.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import static fr.horizons.slimefunx.base.SlimefunObject.NBT_SLIMEFUN_ID;

public abstract class SlimefunGuiResource extends SlimefunGui {
    private int textureId;

    public SlimefunGuiResource(String id, int textureId) {
        super(id);
        this.textureId = textureId;
    }

    public boolean hasCustomTexture() {
        return this.textureId != -1;
    }

    public ItemStack getCustomTexture() {
        if (!hasCustomTexture()) return new ItemStack(Material.AIR);
        ItemStack is = new ItemStack(Material.DIAMOND_SWORD);
        net.minecraft.server.v1_13_R2.ItemStack nmsStack = CraftItemStack.asNMSCopy(is);
        NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
        compound.setString(NBT_SLIMEFUN_ID, getId());
        // Bind to custom texture, if textureId equals -1, custom texture is not loaded
        System.out.println(textureId);
        if (this.textureId != -1) {
            compound.setInt("Damage", textureId);
            compound.setBoolean("Unbreakable", true);
        }
        nmsStack.setTag(compound);
        return CraftItemStack.asBukkitCopy(nmsStack);
    }
}
