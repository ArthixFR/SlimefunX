package fr.horizons.slimefunx.multiblock;

import fr.horizons.slimefunx.base.Category;
import fr.horizons.slimefunx.base.SlimefunObject;
import fr.horizons.slimefunx.interfaces.IBuildable;
import fr.horizons.slimefunx.interfaces.IInteractable;
import fr.horizons.slimefunx.interfaces.IResearchable;
import org.bukkit.inventory.ItemStack;

public abstract class SlimefunMultiblock extends SlimefunObject implements IBuildable, IResearchable, IInteractable {
    public SlimefunMultiblock(String id, Category category, ItemStack item, int textureId) {
        super(id, item, category, textureId);
    }
}
