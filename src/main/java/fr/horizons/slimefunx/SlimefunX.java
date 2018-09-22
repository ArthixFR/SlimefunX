package fr.horizons.slimefunx;

import fr.horizons.slimefunx.block.BlocksManager;
import fr.horizons.slimefunx.commands.BaseCommand;
import fr.horizons.slimefunx.listeners.*;
import fr.horizons.slimefunx.item.ItemsManager;
import fr.horizons.slimefunx.util.BlockDamager;
import net.minecraft.server.v1_13_R2.PacketPlayInBlockDig;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.inventivetalent.packetlistener.PacketListenerAPI;
import org.inventivetalent.packetlistener.handler.PacketHandler;
import org.inventivetalent.packetlistener.handler.ReceivedPacket;
import org.inventivetalent.packetlistener.handler.SentPacket;

public final class SlimefunX extends JavaPlugin {

    private static SlimefunX instance;
    private ItemsManager itemsManager;
    private BlocksManager blocksManager;

    @Override
    public void onEnable() {
        instance = this;

        this.itemsManager = new ItemsManager(this);
        this.itemsManager.registerItems();

        this.blocksManager = new BlocksManager(this);
        this.blocksManager.registerBlocks();

        PluginCommand command = getCommand("sfx");
        command.setExecutor(new BaseCommand(this));
        command.setTabCompleter(new BaseCommand(this));

        registerEvents();

        listen();
    }

    @Override
    public void onDisable() {

    }

    public void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new PlayerInteractListener(this), this);
        pm.registerEvents(new BlockBreakListener(this), this);
        pm.registerEvents(new InventoryClickListener(this), this);
        pm.registerEvents(new EntityPickupItemListener(this), this);
        pm.registerEvents(new InventoryMoveItemListener(this), this);
        pm.registerEvents(new BlockDamageListener(this), this);
    }

    public void listen() {
        PacketListenerAPI.addPacketHandler(new PacketHandler() {
            @Override
            public void onSend(SentPacket packet) {

            }

            @Override
            public void onReceive(ReceivedPacket packet) {
                if (packet.getPacket() instanceof PacketPlayInBlockDig) {
                    PacketPlayInBlockDig.EnumPlayerDigType digType = ((PacketPlayInBlockDig) packet.getPacket()).d();
                    Player p = packet.getPlayer();
                    if (digType.equals(PacketPlayInBlockDig.EnumPlayerDigType.ABORT_DESTROY_BLOCK) || digType.equals(PacketPlayInBlockDig.EnumPlayerDigType.STOP_DESTROY_BLOCK)) {
                        if (BlockDamager.breakingList.containsKey(p.getUniqueId())) {
                            BlockDamager blockDamager = BlockDamager.breakingList.get(p.getUniqueId());
                            blockDamager.setCancelled(true);
                            BlockDamager.breakingList.remove(p.getUniqueId());
                        }
                    }
                }
            }
        });
    }

    public static SlimefunX getInstance() {
        return instance;
    }

    public ItemsManager getItemsManager() {
        return itemsManager;
    }

    public BlocksManager getBlocksManager() {
        return blocksManager;
    }
}
