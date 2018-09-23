package fr.horizons.slimefunx.block.machines;

import fr.horizons.slimefunx.base.SlimefunObject;
import fr.horizons.slimefunx.block.SlimefunMachine;
import fr.horizons.slimefunx.list.Categories;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class Sieve extends SlimefunMachine {
    public Sieve() {
        super("sieve", Categories.MACHINES, new ItemStack(Material.DIAMOND_AXE), 3, "§fSieve", null);
    }

    @Override
    public int baseTickBreakTime() {
        return 40;
    }

    @Override
    public Map<SlimefunObject, Integer> slimefunToolDamage() {
        return null;
    }

    @Override
    public Map<Material, Integer> vanillaToolDamage() {
        return null;
    }

    @Override
    public String[][] getRecipePattern() {
        return new String[0][];
    }

    @Override
    public Listener getInteractionListener() {
        return null;
    }

    @Override
    public boolean isPlaceable() {
        return true;
    }

    @Override
    public void interactEvent(PlayerInteractEvent e) {

    }

    @Override
    public int getResearchCost() {
        return 0;
    }

    @Override
    public int stackSize() {
        return 16;
    }
}