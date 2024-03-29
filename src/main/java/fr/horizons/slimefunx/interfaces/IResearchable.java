package fr.horizons.slimefunx.interfaces;

import fr.horizons.slimefunx.util.TriFunction;
import org.bukkit.entity.Player;

public interface IResearchable {

    TriFunction<Integer, Integer, Integer, Integer> researchTime = (cost, mastery, prestige) -> {
        double numerator = (5.0*cost)/3.0 - mastery*cost;
        double divisor = 1.0 + prestige/4.0;
        return (int) Math.round(Math.pow(numerator / divisor, Math.sqrt(2)));
    };

    /**
     * Returns the XP levels cost of the research.
     * @return the cost of the research in XP levels.
     */
    int getResearchCost();

    default int getResearchTime(Player player) {
        return researchTime.apply(getResearchCost(), 0, 0);
    }
}
