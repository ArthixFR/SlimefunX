package fr.horizons.slimefunx.util;

import fr.horizons.slimefunx.SlimefunX;
import fr.horizons.slimefunx.block.SlimefunBlock;
import fr.horizons.slimefunx.item.SlimefunItem;
import fr.horizons.slimefunx.item.SlimefunTool;
import net.minecraft.server.v1_13_R2.BlockPosition;
import net.minecraft.server.v1_13_R2.PacketPlayOutBlockBreakAnimation;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

// TODO: AJOUTER LES SONS ET LES PARTICULES

public class BlockDamager {

    public static Map<UUID, BlockDamager> breakingList = new HashMap<>();

    private Player p;
    private Block block;
    private ItemStack breaker;
    private int time;
    private boolean cancel;

    public BlockDamager(Player p, Block block, ItemStack breaker, int time) {
        this.p = p;
        this.block = block;
        this.breaker = breaker;
        this.time = time;
        this.cancel = false;

        SlimefunBlock sfBlock = SlimefunX.getInstance().getBlocksManager().getBlockByTag(block);
        if (sfBlock == null) return;

        new BukkitRunnable() {
            int i = time;
            BlockPosition blockPosition = new BlockPosition(block.getX(), block.getY(), block.getZ());
            @Override
            public void run() {
                if (BlockDamager.this.isCancelled()) {
                    breakingList.remove(p.getUniqueId());
                    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(new PacketPlayOutBlockBreakAnimation(0, blockPosition, -1));
                    cancel();
                    return;
                }
                double calc = (1 - ((double)i / (double)time)) * 10 - 1;
                PacketPlayOutBlockBreakAnimation packet = new PacketPlayOutBlockBreakAnimation(0, blockPosition, (int) Math.ceil(calc));
                ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);

                if (i == 0) {
                    breakingList.remove(p.getUniqueId());
                    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(new PacketPlayOutBlockBreakAnimation(0, blockPosition, -1));
                    sfBlock.breakBlock(p, block.getLocation(), breaker);
                    cancel();
                    return;
                }
                i--;
            }
        }.runTaskTimer(SlimefunX.getInstance(), 0L, 1L);
    }

    public Block getBlock() {
        return block;
    }

    public int getTime() {
        return time;
    }

    public Player getPlayer() {
        return p;
    }

    public boolean isCancelled() {
        return cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    public static int getBlockDurability(SlimefunBlock sfBlock, SlimefunItem sfItem) {
        return sfBlock.slimefunToolDamage() == null ? sfBlock.baseTickBreakTime() : sfBlock.slimefunToolDamage().getOrDefault(sfItem, sfBlock.baseTickBreakTime());
    }

    public static int getBlockDurability(SlimefunBlock sfBlock, ItemStack is) {
        return sfBlock.vanillaToolDamage() == null ? sfBlock.baseTickBreakTime() : sfBlock.vanillaToolDamage().getOrDefault(is.getType(), sfBlock.baseTickBreakTime());
    }

    public ItemStack getBreaker() {
        return breaker;
    }
}
