package fr.horizons.slimefunx.commands;

import fr.horizons.slimefunx.SlimefunX;
import fr.horizons.slimefunx.item.SlimefunItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
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

                SlimefunItem slimefunItem = plugin.getItemsManager().getItemById(args[1]);
                if (slimefunItem == null) {
                    System.out.println("sfi null");
                    return true;
                }
                PlayerInventory playerInventory = p.getInventory();
                if (playerInventory.firstEmpty() == -1) {
                    System.out.println("no space");
                    return true;
                }
                playerInventory.setItem(p.getInventory().firstEmpty(), slimefunItem.getItem());
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
        }
        return list;
    }
}
