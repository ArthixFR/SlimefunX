package fr.horizons.slimefunx.interfaces;

import fr.horizons.slimefunx.base.SlimefunObject;
import org.bukkit.Material;

import java.util.Map;

public interface IBreakable {

    int baseTickBreakTime();
    Map<SlimefunObject, Integer> slimefunToolDamage();
    Map<Material, Integer> vanillaToolDamage();
}
