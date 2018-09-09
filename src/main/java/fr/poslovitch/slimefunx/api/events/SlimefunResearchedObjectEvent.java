package fr.poslovitch.slimefunx.api.events;

import fr.poslovitch.slimefunx.api.base.SlimefunObject;
import world.bentobox.bentobox.api.events.PremadeEvent;

import java.util.UUID;

/**
 * Triggered when a Player fully researched a researchable SlimefunObject
 */
public class SlimefunResearchedObjectEvent extends PremadeEvent {

    private final UUID player;
    private final SlimefunObject researchedObject;
    private final int researchCost;
    private final int researchTime;

    public SlimefunResearchedObjectEvent(UUID player, SlimefunObject researchedObject, int researchCost, int researchTime) {
        this.player = player;
        this.researchedObject = researchedObject;
        this.researchCost = researchCost;
        this.researchTime = researchTime;
    }

    public UUID getPlayer() {
        return player;
    }

    public SlimefunObject getResearchedObject() {
        return researchedObject;
    }

    public int getResearchCost() {
        return researchCost;
    }

    public int getResearchTime() {
        return researchTime;
    }
}
