package fr.horizons.slimefunx.storage;

import fr.horizons.slimefunx.util.Research;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SlimefunPlayer implements Serializable {
    private List<Research> activeResearches = new ArrayList<>();

    private UUID uuid;

    public SlimefunPlayer(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUUID() {
        return uuid;
    }

    public List<Research> getActiveResearches() {
        return activeResearches;
    }


}
