package fr.poslovitch.slimefunx.database;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

public class SlimefunPlayer implements Serializable {

    private final UUID uuid;
    private final Map<String, Long> unlockedObjects;
    private final Map<String, Long> researchedObjects;

    public SlimefunPlayer(UUID uuid, Map<String, Long> unlockedObjects, Map<String, Long> researchedObjects) {
        this.uuid = uuid;
        this.unlockedObjects = unlockedObjects;
        this.researchedObjects = researchedObjects;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Map<String, Long> getUnlockedObjects() {
        return unlockedObjects;
    }

    public Map<String, Long> getResearchedObjects() {
        return researchedObjects;
    }
}
