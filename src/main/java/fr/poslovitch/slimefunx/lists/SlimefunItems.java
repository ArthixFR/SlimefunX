package fr.poslovitch.slimefunx.lists;

import fr.poslovitch.slimefunx.api.items.SlimefunItem;
import fr.poslovitch.slimefunx.api.items.SlimefunTool;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class SlimefunItems {

    public static final SlimefunItem WOODEN_HAMMER = new SlimefunTool("WOODEN_HAMMER",
            Categories.TOOLS,
            new ItemStack(Material.WOODEN_AXE),
            new String[][]{
                    {"", "OAK_PLANKS", "OAK_PLANKS"},
                    {"", "STICK", "OAK_PLANKS"},
                    {"STICK", "", ""}
            },
            4,
            new Listener() {
                @EventHandler
                public void onBlockBreak(BlockBreakEvent e) {
                    System.out.println("Coucou");
                }
            });
}
