package fr.poslovitch.slimefunx.api.interfaces;

/**
 * Represents the ability for a {@link fr.poslovitch.slimefunx.api.base.SlimefunObject} to be researched by a {@link org.bukkit.entity.Player}.
 *
 * @author Poslovitch
 */
public interface Researchable {

    /**
     * Returns the XP levels cost of the research.
     * @return the cost of the research in XP levels.
     */
    int getResearchCost();
}
