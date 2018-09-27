package fr.horizons.slimefunx.list;

import fr.horizons.slimefunx.block.SlimefunMachine;
import fr.horizons.slimefunx.block.SlimefunStaticBlock;
import fr.horizons.slimefunx.block.machines.Sieve;
import fr.horizons.slimefunx.block.staticblocks.DustBlock;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class BlockList {

    public static SlimefunStaticBlock DUST_BLOCK = new DustBlock("dust_block", Categories.STATIC_BLOCKS, new ItemStack(Material.DIAMOND_AXE), 2, "§fDust Block", null);

    public static SlimefunMachine SIEVE = new Sieve("sieve", Categories.MACHINES, new ItemStack(Material.DIAMOND_AXE), 3, "§fSieve", null);
}
