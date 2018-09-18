package fr.horizons.slimefunx.block;

import fr.horizons.slimefunx.SlimefunX;
import fr.horizons.slimefunx.base.SlimefunObject;
import fr.horizons.slimefunx.list.BlockList;
import net.minecraft.server.v1_13_R2.NBTTagCompound;
import org.apache.commons.lang.Validate;
import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class BlocksManager {
    private final SlimefunX plugin;
    private final Map<String, SlimefunBlock> blocks = new LinkedHashMap<>();

    public BlocksManager(SlimefunX plugin) {
        this.plugin = plugin;
    }

    public Map<String, SlimefunBlock> getItems() {
        return blocks;
    }

    public Set<SlimefunBlock> getBlockSet() {
        return new HashSet<>(blocks.values());
    }

    public Set<String> getIdsSet() {
        return blocks.keySet();
    }

    public SlimefunBlock getBlockById(String id) {
        return blocks.get(id);
    }

    public SlimefunBlock getBlockByTag(ItemStack is) {
        net.minecraft.server.v1_13_R2.ItemStack nmsStack = CraftItemStack.asNMSCopy(is);
        NBTTagCompound compound = nmsStack.getTag();
        final SlimefunBlock[] sfi = {null};
        if (compound != null && compound.hasKey(SlimefunObject.NBT_SLIMEFUN_ID)) {
            String nbt = compound.getString(SlimefunObject.NBT_SLIMEFUN_ID);
            getItems().forEach((s, SlimefunBlock) -> {
                if (s.equalsIgnoreCase(nbt)) {
                    sfi[0] = SlimefunBlock;
                    return;
                }
            });
        }
        return sfi[0];
    }

    public void registerBlocks() {
        Arrays.stream(BlockList.class.getDeclaredFields())
                .filter(field -> SlimefunBlock.class.isAssignableFrom(field.getType()))
                .forEach(field -> {
                    try {
                        registerBlock((SlimefunBlock) field.get(null));
                    } catch (Exception e) {
                        plugin.getLogger().info("Skipping '" + field.getName() + "' due to IllegalAccessException.");
                    }
                });
    }

    private void registerBlock(SlimefunBlock item) {
        Validate.notNull(item, "The item cannot be null.");

        try {
            blocks.put(item.getId(), item);
        } catch (Exception e) {
            plugin.getLogger().severe("Item registration failed: " + item.getId());
        }
    }
}
