package com.matt4499.msmp.homes;
import com.matt4499.msmp.Main;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
public class HomeHelper {
    public File homesConfigFile;
    public FileConfiguration homesConfig;
    public HomeHelper() {
        homesConfigFile = new File(Main.instance.getDataFolder(), "homes.yml");
        homesConfig = YamlConfiguration.loadConfiguration(homesConfigFile);
        loadHomes();
    }
    public void saveHomes() {
        try {
            homesConfig.save(homesConfigFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadHomes() {
        try {
            homesConfig.load(homesConfigFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setPlayerHome(Player p, String name, Location loc) {
        homesConfig.set("homes." + p.getUniqueId() + "." + name, loc);
        saveHomes();
    }
    public Location getPlayerHomeLocation(Player p, String name) {
        return homesConfig.getLocation("homes." + p.getUniqueId() + "." + name);
    }
    public void removePlayerHome(Player p, String name) {
        homesConfig.set("homes." + p.getUniqueId() + "." + name, null);
        saveHomes();
    }
    public int getPlayerHomeCount(Player p) {
        if(homesConfig.getConfigurationSection("homes." + p.getUniqueId()) == null) {
            return 0;
        }
        return Objects.requireNonNull(homesConfig.getConfigurationSection("homes." + p.getUniqueId())).getKeys(false).size();
    }
    public HashMap<String, Location> getPlayerHomes(Player p) {
        HashMap<String, Location> homes = new HashMap<>();
        for(String home : Objects.requireNonNull(homesConfig.getConfigurationSection("homes." + p.getUniqueId())).getKeys(false)) {
            homes.put(home, homesConfig.getLocation("homes." + p.getUniqueId() + "." + home));
        }
        return homes;
    }
}
