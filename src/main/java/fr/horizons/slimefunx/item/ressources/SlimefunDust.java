package fr.horizons.slimefunx.item.ressources;

import fr.horizons.slimefunx.item.SlimefunRessource;

import javax.annotation.Nullable;
import java.util.List;

public class SlimefunDust extends SlimefunRessource {

    public SlimefunDust(String id, int textureId, @Nullable String name, @Nullable List<String> lore) {
        super(id + "_dust", textureId, name + " Dust", lore);
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
