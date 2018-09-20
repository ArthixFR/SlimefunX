package fr.horizons.slimefunx.item;

import fr.horizons.slimefunx.base.Category;
import fr.horizons.slimefunx.base.SlimefunObject;
import fr.horizons.slimefunx.interfaces.IAttackable;
import fr.horizons.slimefunx.interfaces.ICraftable;
import fr.horizons.slimefunx.interfaces.IResearchable;
import fr.horizons.slimefunx.interfaces.IStackable;
import net.minecraft.server.v1_13_R2.*;
import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.List;

public abstract class SlimefunItem extends SlimefunObject implements ICraftable, IResearchable, IStackable, IAttackable {

    private String name;
    private List<String> lore;

    public SlimefunItem(String id, Category category, ItemStack item, int textureId, @Nullable String name, @Nullable List<String> lore) {
        super(id, item, category, textureId);

        ItemMeta im = this.getItem().getItemMeta();
        if (hideAttributes()) {
            im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
        } else {
            im.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        }
        if (name != null) {
            this.name = name;
            im.setDisplayName(name);
        }
        if (lore != null) {
            this.lore = lore;
            im.setLore(lore);
        }
        this.getItem().setItemMeta(im);


        net.minecraft.server.v1_13_R2.ItemStack nmsStack = CraftItemStack.asNMSCopy(this.getItem());
        NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
        NBTTagList modifiers = new NBTTagList();

        NBTTagCompound attackDamage = new NBTTagCompound();
        attackDamage.set("AttributeName", new NBTTagString("generic.attackDamage"));
        attackDamage.set("Name", new NBTTagString("generic.attackDamage"));
        attackDamage.set("Amount", new NBTTagDouble(canAttack() ? attackDamage() : 0));
        attackDamage.set("Operation", new NBTTagInt(0));
        attackDamage.set("UUIDLeast", new NBTTagInt((int) (Math.random() * 894655)));
        attackDamage.set("UUIDMost", new NBTTagInt((int) (Math.random() * 2873)));
        modifiers.add(attackDamage);

        NBTTagCompound attackSpeed = new NBTTagCompound();
        attackSpeed.set("AttributeName", new NBTTagString("generic.attackSpeed"));
        attackSpeed.set("Name", new NBTTagString("generic.attackSpeed"));
        attackSpeed.set("Amount", new NBTTagDouble(canAttack() ? attackSpeed() : 10));
        attackSpeed.set("Operation", new NBTTagInt(0));
        attackSpeed.set("UUIDLeast", new NBTTagInt((int) (Math.random() * 894655)));
        attackSpeed.set("UUIDMost", new NBTTagInt((int) (Math.random() * 2873)));
        modifiers.add(attackSpeed);

        compound.set("AttributeModifiers", modifiers);
        nmsStack.setTag(compound);

        this.setItem(CraftItemStack.asBukkitCopy(nmsStack));
    }

    public String getName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
    }

    @Override
    public boolean hideAttributes() {
        return true;
    }

    @Override
    public boolean canAttack() {
        return false;
    }

    @Override
    public double attackDamage() {
        return 0;
    }

    @Override
    public double attackSpeed() {
        return 4;
    }
}
