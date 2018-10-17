package fr.horizons.slimefunx.storage;

import fr.horizons.slimefunx.SlimefunX;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StorageManager {
    private List<SlimefunPlayer> slimefunPlayers = new ArrayList<>();

    private SlimefunX plugin;

    public StorageManager(SlimefunX plugin) {
        this.plugin = plugin;
    }

    public void savePlayer(SlimefunPlayer slimefunPlayer) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(plugin.getDataFolder() + "/" + slimefunPlayer.getUUID() + ".sfx"));
            objectOutputStream.writeObject(slimefunPlayer);
            objectOutputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void loadPlayer(UUID uuid) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(plugin.getDataFolder() + "/" + uuid + ".sfx"));
            SlimefunPlayer slimefunPlayer = (SlimefunPlayer) objectInputStream.readObject();
            if (slimefunPlayer == null) return;
            SlimefunPlayer currentPlayer = getSlimefunPlayer(uuid);
            if (currentPlayer != null) slimefunPlayers.remove(currentPlayer);
            slimefunPlayers.add(slimefunPlayer);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void unloadPlayer(UUID uuid) {
        SlimefunPlayer slimefunPlayer = getSlimefunPlayer(uuid);
        if (slimefunPlayer == null) return;
        savePlayer(slimefunPlayer);
        slimefunPlayers.remove(slimefunPlayer);
    }

    public SlimefunPlayer getSlimefunPlayer(UUID uuid) {
        for (SlimefunPlayer slimefunPlayer : slimefunPlayers) {
            if (slimefunPlayer.getUUID().equals(uuid)) return slimefunPlayer;
        }
        return null;
    }
}
