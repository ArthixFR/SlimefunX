package fr.horizons.slimefunx.commands;

import fr.horizons.slimefunx.SlimefunX;
import fr.horizons.slimefunx.block.SlimefunBlock;
import fr.horizons.slimefunx.gui.guis.SlimefunGuideGui;
import fr.horizons.slimefunx.item.SlimefunItem;
import fr.horizons.slimefunx.list.GuiList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.List;

public class BaseCommand implements CommandExecutor, TabCompleter {

    private SlimefunX plugin;

    public BaseCommand(SlimefunX plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) return true;
            if (args[0].equalsIgnoreCase("give")) {
                if (args.length < 2) return true;

                PlayerInventory playerInventory = p.getInventory();
                if (playerInventory.firstEmpty() == -1) {
                    System.out.println("no space");
                    return true;
                }

                SlimefunItem slimefunItem = plugin.getItemsManager().getItemById(args[1]);
                SlimefunBlock slimefunBlock = plugin.getBlocksManager().getBlockById(args[1]);
                int count = 1;
                if (args.length == 3) {
                    try {
                        count = Integer.valueOf(args[2]);
                    } catch (NumberFormatException ex) {}
                }
                if (slimefunBlock != null) {
                    ItemStack is = slimefunBlock.getItem().clone();
                    is.setAmount(count);
                    playerInventory.setItem(p.getInventory().firstEmpty(), is);
                } else if (slimefunItem != null) {
                    ItemStack is = slimefunItem.getItem().clone();
                    is.setAmount(count);
                    playerInventory.setItem(p.getInventory().firstEmpty(), is);
                } else {
                    System.out.println("sfi null");
                    return true;
                }
            } else if (args[0].equalsIgnoreCase("test")) {
                GuiList.SLIMEFUN_GUIDE.openGui(p);
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<>();

        if (sender instanceof Player) {
            if (!sender.hasPermission("slimefunx.admin")) {
                return list;
            }
        }

        if (args.length == 1) {
            list.add("give");
        } else if (args.length == 2) {
            plugin.getItemsManager().getItemsSet().forEach(slimefunItem -> list.add(slimefunItem.getId()));
            plugin.getBlocksManager().getBlockSet().forEach(slimefunBlock -> list.add(slimefunBlock.getId()));
        }
        return list;
    }
}
