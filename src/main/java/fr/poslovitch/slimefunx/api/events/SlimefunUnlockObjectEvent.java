package fr.poslovitch.slimefunx.api.events;

import fr.poslovitch.slimefunx.api.base.SlimefunObject;
import world.bentobox.bentobox.api.events.PremadeEvent;

import java.util.UUID;

/**
 * Triggered when a Player unlocked a SlimefunObject by studying the research plan.
 */
public class SlimefunUnlockObjectEvent extends PremadeEvent {

    private final UUID player;
    private final SlimefunObject unlockedObject;

    public SlimefunUnlockObjectEvent(UUID player, SlimefunObject unlockedObject) {
        this.player = player;
        this.unlockedObject = unlockedObject;
    }

    public UUID getPlayer() {
        return player;
    }

    public SlimefunObject getUnlockedObject() {
        return unlockedObject;
    }
}
