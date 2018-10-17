package fr.horizons.slimefunx.block;

import fr.horizons.slimefunx.SlimefunX;
import fr.horizons.slimefunx.base.Category;
import fr.horizons.slimefunx.base.SlimefunObject;
import fr.horizons.slimefunx.interfaces.*;
import fr.horizons.slimefunx.item.SlimefunItem;
import fr.horizons.slimefunx.item.SlimefunTool;
import fr.horizons.slimefunx.util.Durability;
import fr.horizons.slimefunx.util.InventoryUtils;
import fr.horizons.slimefunx.util.LocationUtils;
import fr.horizons.slimefunx.util.NBTListType;
import net.minecraft.server.v1_13_R2.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_13_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.List;

public abstract class SlimefunBlock extends SlimefunObject implements ICraftable, IPlaceable, IStackable, IBreakable {
    private String name;
    private List<String> lore;

    public SlimefunBlock(String id, Category category, ItemStack item, int textureId, @Nullable String name, @Nullable List<String> lore) {
        super(id, item, category, textureId);

        ItemMeta im = this.getItem().getItemMeta();
        im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
        if (name != null) {
            this.name = "§f" + name;
            im.setDisplayName(this.name);
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
        attackDamage.set("Amount", new NBTTagDouble(0));
        attackDamage.set("Operation", new NBTTagInt(0));
        attackDamage.set("UUIDLeast", new NBTTagInt((int) (Math.random() * 894655)));
        attackDamage.set("UUIDMost", new NBTTagInt((int) (Math.random() * 2873)));
        modifiers.add(attackDamage);

        NBTTagCompound attackSpeed = new NBTTagCompound();
        attackSpeed.set("AttributeName", new NBTTagString("generic.attackSpeed"));
        attackSpeed.set("Name", new NBTTagString("generic.attackSpeed"));
        attackSpeed.set("Amount", new NBTTagDouble(10));
        attackSpeed.set("Operation", new NBTTagInt(0));
        attackSpeed.set("UUIDLeast", new NBTTagInt((int) (Math.random() * 894655)));
        attackSpeed.set("UUIDMost", new NBTTagInt((int) (Math.random() * 2873)));
        modifiers.add(attackSpeed);

        compound.set("AttributeModifiers", modifiers);
        nmsStack.setTag(compound);

        this.setItem(CraftItemStack.asBukkitCopy(nmsStack));
    }

    public List<String> getLore() {
        return lore;
    }

    public String getName() {
        return name;
    }

    public void placeBlock(ItemStack is, Location loc) {
        loc.getBlock().setType(Material.SPAWNER);
        CraftWorld cw = (CraftWorld) loc.getWorld();
        BlockPosition bp = new BlockPosition(loc.getX(), loc.getY(), loc.getZ());
        TileEntityMobSpawner te = (TileEntityMobSpawner) cw.getHandle().getTileEntity(bp);

        if (te != null) {
            NBTTagCompound tag = te.aa_();
            tag.setShort("RequiredPlayerRange", (short) 0);
            tag.setShort("MaxNearbyEntities", (short) 0);
            tag.set("SpawnData", new NBTTagCompound());
            NBTTagCompound spawnData = tag.getCompound("SpawnData");
            spawnData.setString("id", "minecraft:armor_stand");
            spawnData.setBoolean("NoGravity", true);
            spawnData.setBoolean("Invisible", true);
            spawnData.setBoolean("NoBasePlate", true);
            spawnData.setBoolean("Marker", true);
            spawnData.setBoolean("ShowArms", false);
            spawnData.setBoolean("Invulnerable", true);

            ItemStack item = getItem();
            Damageable damageable = (Damageable) item.getItemMeta();
            damageable.setDamage(getTextureId());
            ItemMeta im = item.getItemMeta();
            im.setUnbreakable(true);
            item.setItemMeta(im);

            spawnData.set("ArmorItems", new NBTTagList());
            NBTTagList armorItems = spawnData.getList("ArmorItems", NBTListType.COMPOUND);
            armorItems.add(new NBTTagCompound());
            armorItems.add(new NBTTagCompound());
            armorItems.add(new NBTTagCompound());
            armorItems.add(InventoryUtils.convertItemStackToCompound(item, getId()));

            spawnData.set("Pose", new NBTTagCompound());
            NBTTagCompound pose = spawnData.getCompound("Pose");
            pose.set("Head", new NBTTagList());
            NBTTagList headPose = pose.getList("Head", NBTListType.FLOAT);
            headPose.add(new NBTTagFloat(0));
            headPose.add(new NBTTagFloat(0));
            headPose.add(new NBTTagFloat(0));

            te.setPosition(bp);
            te.load(tag);
            te.update();

            if (is.getAmount() == 1) {
                is.setAmount(0);
                is.setType(Material.AIR);
            } else {
                is.setAmount(is.getAmount() - 1);
            }
        }
    }

    public void breakBlock(Player p, Location loc, ItemStack breaker, boolean damage) {
        SlimefunBlock slimefunBlock = SlimefunX.getInstance().getBlocksManager().getBlockByTag(loc.getBlock());
        if (slimefunBlock != null) {
            loc.getBlock().setType(Material.AIR);
            loc.getWorld().dropItemNaturally(LocationUtils.getBlockCenter(loc), slimefunBlock.getItem());
            if (damage) {
                SlimefunItem slimefunItem = SlimefunX.getInstance().getItemsManager().getItemByTag(breaker);
                if (slimefunItem != null) {
                    if (slimefunItem instanceof SlimefunTool) {
                        if (((SlimefunTool) slimefunItem).hasDurability()) {
                            p.getInventory().setItemInMainHand(Durability.setDurability(p.getInventory().getItemInMainHand(), (Durability.getDurability(p.getInventory().getItemInMainHand()) - 1)));
                        }
                    }
                } else {
                    SlimefunBlock breakerSlimefunBlock = SlimefunX.getInstance().getBlocksManager().getBlockByTag(breaker);
                    if (breakerSlimefunBlock != null) return;
                    if (breaker.hasItemMeta()) {
                        Damageable damageable = (Damageable) breaker.getItemMeta();
                        damageable.setDamage(damageable.getDamage() + 1);
                        breaker.setItemMeta((ItemMeta) damageable);
                    }
                }
            }
        }
    }

    @Override
    public int stackSize() {
        return 64;
    }
}
