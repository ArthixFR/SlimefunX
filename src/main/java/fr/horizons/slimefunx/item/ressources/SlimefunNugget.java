package fr.horizons.slimefunx.item.ressources;

import fr.horizons.slimefunx.item.SlimefunResource;

import javax.annotation.Nullable;
import java.util.List;

public class SlimefunNugget extends SlimefunResource {

    public SlimefunNugget(String id, int textureId, @Nullable String name, @Nullable List<String> lore) {
        super(id + "_nugget", textureId, name + " Nugget", lore);
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
