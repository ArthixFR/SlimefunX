package fr.horizons.slimefunx.item.ressources;

import fr.horizons.slimefunx.item.SlimefunResource;

import javax.annotation.Nullable;
import java.util.List;

public class SlimefunIngot extends SlimefunResource {

    public SlimefunIngot(String id, int textureId, @Nullable String name, @Nullable List<String> lore) {
        super(id + "_ingot", textureId, name + " Ingot", lore);
    }

    @Override
    public String[][] getRecipePattern() {
        return new String[0][];
    }

    @Override
    public int getResearchCost() {
        return 0;
    }
}
