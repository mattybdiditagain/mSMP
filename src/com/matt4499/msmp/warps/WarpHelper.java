package com.matt4499.msmp.warps;
import com.matt4499.msmp.Main;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
public class WarpHelper {
    public File warpsConfigFile;
    public FileConfiguration warpsConfig;
    public WarpHelper() {
        warpsConfigFile = new File(Main.instance.getDataFolder(), "warps.yml");
        warpsConfig = YamlConfiguration.loadConfiguration(warpsConfigFile);
        loadWarps();
    }
    public void loadWarps() {
        try {
            warpsConfig.load(warpsConfigFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void saveWarps() {
        try {
            warpsConfig.save(warpsConfigFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Boolean doesWarpExist(String name) {
        return warpsConfig.getString("warps." + name) != null;
    }
    public void setWarp(Player p, String name, Location loc, String description) {
        if(doesWarpExist(name)) {
            return;
        }
        warpsConfig.set("warps." + name + ".location", loc);
        warpsConfig.set("warps." + name + ".owner", p.getName());
        warpsConfig.set("warps." + name + ".description", description);
        saveWarps();
    }
    public Location getWarpLocation(String name) {
        return warpsConfig.getLocation("warps." + name + ".location");
    }
    public String getWarpOwner(String name) {
        return warpsConfig.getString("warps." + name + ".owner");
    }
    public String getWarpDescription(String name) {
        return warpsConfig.getString("warps." + name + ".description");
    }
    public void removeWarp(String name) {
        warpsConfig.set("warps." + name, null);
        saveWarps();
    }
    public int getWarpCount() {
        return warpsConfig.getConfigurationSection("warps").getKeys(false).size();
    }
    public HashMap<String, Location> getWarps() {
        HashMap<String, Location> warps = new HashMap<>();
        for(String warp : Objects.requireNonNull(warpsConfig.getConfigurationSection("warps")).getKeys(false)) {
            warps.put(warp, warpsConfig.getLocation("warps." + warp));
        }
        return warps;
    }
}
