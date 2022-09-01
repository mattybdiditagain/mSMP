package com.matt4499.msmp.economy;
import com.matt4499.msmp.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.UUID;
public class EcoHelper {
    public File ecoDataFile;
    public FileConfiguration ecoData;
    public EcoHelper() {
        ecoDataFile = new File(Main.instance.getDataFolder(), "ecodata.yml");
        ecoData = YamlConfiguration.loadConfiguration(ecoDataFile);
        loadEcoData();
    }
    public void loadEcoData() {
        try {
            ecoData.load(ecoDataFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void saveData() {
        try {
            ecoData.save(ecoDataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Boolean doesAccountExist(UUID uuid) {
        return ecoData.isSet("balances." + uuid);
    }
    public void createAccount(UUID uuid) {
        ecoData.set("balances." + uuid, 1000);
        saveData();
    }
    public double get(UUID uuid) {
        if(!doesAccountExist(uuid)) {
            createAccount(uuid);
        }
        return ecoData.getDouble("balances." + uuid);
    }
    public String getFormatted(UUID uuid) {
        double bal = get(uuid);
        return DecimalFormat.getCurrencyInstance().format(bal);
    }
    public void set(UUID uuid, double balance) {
        if(!doesAccountExist(uuid)) {
            return;
        }
        ecoData.set("balances." + uuid, balance);
        saveData();
    }
    public double add(UUID uuid, double toAdd) {
        double curBalance = get(uuid);
        double newBalance = curBalance + toAdd;
        set(uuid, newBalance);
        saveData();
        return newBalance;
    }
    public Boolean trySubtract(UUID uuid, double toRemove) {
        if(get(uuid) - toRemove < 0) {
            return false;
        }
        double curBalance = get(uuid);
        double newBalance = curBalance - toRemove;
        set(uuid, newBalance);
        saveData();
        return true;
    }

    public double getGlobalBalance() {
        // TODO
        return 0.0;
    }

}
