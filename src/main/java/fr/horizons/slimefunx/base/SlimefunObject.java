package fr.horizons.slimefunx.base;

import fr.horizons.slimefunx.interfaces.IIdentifiable;
import net.minecraft.server.v1_13_R2.NBTTagCompound;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class SlimefunObject implements IIdentifiable {

    public static final String NBT_SLIMEFUN_ID = "slimefun_id";

    private final String id;
    private final int textureId;
    private final Category category;
    private ItemStack item;

    public SlimefunObject(String id, ItemStack item, Category category) {
        this(id, item, category, -1);
    }

    public SlimefunObject(String id, ItemStack item, Category category, int textureId) {
        // Validation - make sure that none of the specified value is illegal.
        Validate.notNull(id, "Id cannot be null.");
        Validate.notNull(category, "Category cannot be null.");
        Validate.notNull(item, "Item cannot be null");
        Validate.isTrue(!(item.getType().equals(Material.AIR) || item.getType().equals(Material.CAVE_AIR) || item.getType().equals(Material.VOID_AIR)), "Item cannot be of type AIR: ", item.getType());
        // -------------------------------------------------------------------

        this.id = id;
        this.category = category;
        this.textureId = textureId;

        // Add the NBTTag to the item
        net.minecraft.server.v1_13_R2.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
        compound.setString(NBT_SLIMEFUN_ID, id);
        // Bind to custom texture, if textureId equals -1, custom texture is not loaded
        System.out.println(textureId);
        if (this.textureId != -1) {
            compound.setInt("Damage", textureId);
            compound.setBoolean("Unbreakable", true);
        }
        nmsStack.setTag(compound);
        item = CraftItemStack.asBukkitCopy(nmsStack);

        this.item = item;
    }

    @Override
    public String getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack is) {
        item = is;
    }

    public int getTextureId() {
        return textureId;
    }
}
