package com.matt4499.msmp.economy;
import com.matt4499.msmp.Main;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.text.NumberFormat;
import java.util.List;
import java.util.UUID;
public class mEconomy implements Economy {
    public EcoHelper ecoHelper;
    public mEconomy() {
        ecoHelper = Main.ecoHelper;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public String getName() {
        return "mEconomy";
    }
    @Override
    public boolean hasBankSupport() {
        return false;
    }
    @Override
    public int fractionalDigits() {
        return 0;
    }
    @Override
    public String format(double v) {
        return NumberFormat.getCurrencyInstance().format(v);
    }
    @Override
    public String currencyNamePlural() {
        return "dollars";
    }
    @Override
    public String currencyNameSingular() {
        return "dollar";
    }
    @Override
    public boolean hasAccount(String s) {
        return ecoHelper.doesAccountExist(Bukkit.getPlayerExact(s).getUniqueId());
    }
    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer) {
        return ecoHelper.doesAccountExist(offlinePlayer.getUniqueId());
    }
    @Override
    public boolean hasAccount(String s, String s1) {
        return ecoHelper.doesAccountExist(Bukkit.getPlayerExact(s).getUniqueId());
    }
    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer, String s) {
        return ecoHelper.doesAccountExist(offlinePlayer.getUniqueId());
    }
    @Override
    public double getBalance(String s) {
        return ecoHelper.get(Bukkit.getPlayer(s).getUniqueId());
    }
    @Override
    public double getBalance(OfflinePlayer offlinePlayer) {
        return ecoHelper.get(offlinePlayer.getUniqueId());
    }
    @Override
    public double getBalance(String s, String s1) {
        return 0;
    }
    @Override
    public double getBalance(OfflinePlayer offlinePlayer, String s) {
        return 0;
    }
    @Override
    public boolean has(String s, double v) {
        return ecoHelper.get(Bukkit.getPlayerExact(s).getUniqueId()) >= v;
    }
    @Override
    public boolean has(OfflinePlayer offlinePlayer, double v) {
        return ecoHelper.get(offlinePlayer.getUniqueId()) >= v;
    }
    @Override
    public boolean has(String s, String s1, double v) {
        return ecoHelper.get(Bukkit.getPlayerExact(s).getUniqueId()) >= v;
    }
    @Override
    public boolean has(OfflinePlayer offlinePlayer, String s, double v) {
        return ecoHelper.get(offlinePlayer.getUniqueId()) >= v;
    }
    @Override
    public EconomyResponse withdrawPlayer(String s, double v) {
        Player p = Bukkit.getPlayerExact(s);
        assert p != null;
        UUID u = p.getUniqueId();
        Boolean success = ecoHelper.trySubtract(u, v);
        if(success) {
            return new EconomyResponse(v, ecoHelper.get(u), EconomyResponse.ResponseType.SUCCESS, null);
        } else {
            return new EconomyResponse(v, ecoHelper.get(u), EconomyResponse.ResponseType.FAILURE, null);
        }
    }
    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, double v) {
        UUID u = offlinePlayer.getUniqueId();
        Boolean success = ecoHelper.trySubtract(u, v);
        if(success) {
            return new EconomyResponse(v, ecoHelper.get(u), EconomyResponse.ResponseType.SUCCESS, null);
        } else {
            return new EconomyResponse(v, ecoHelper.get(u), EconomyResponse.ResponseType.FAILURE, null);
        }
    }
    @Override
    public EconomyResponse withdrawPlayer(String s, String s1, double v) {
        Player p = Bukkit.getPlayerExact(s);
        assert p != null;
        UUID u = p.getUniqueId();
        Boolean success = ecoHelper.trySubtract(u, v);
        if(success) {
            return new EconomyResponse(v, ecoHelper.get(u), EconomyResponse.ResponseType.SUCCESS, null);
        } else {
            return new EconomyResponse(v, ecoHelper.get(u), EconomyResponse.ResponseType.FAILURE, null);
        }
    }
    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        UUID u = offlinePlayer.getUniqueId();
        Boolean success = ecoHelper.trySubtract(u, v);
        if(success) {
            return new EconomyResponse(v, ecoHelper.get(u), EconomyResponse.ResponseType.SUCCESS, null);
        } else {
            return new EconomyResponse(v, ecoHelper.get(u), EconomyResponse.ResponseType.FAILURE, null);
        }
    }
    @Override
    public EconomyResponse depositPlayer(String s, double v) {
        Player p = Bukkit.getPlayerExact(s);
        assert p != null;
        UUID u = p.getUniqueId();
        ecoHelper.add(u, v);
        return new EconomyResponse(v, ecoHelper.get(u), EconomyResponse.ResponseType.SUCCESS, null);
    }
    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, double v) {
        UUID u = offlinePlayer.getUniqueId();
        ecoHelper.add(u, v);
        return new EconomyResponse(v, ecoHelper.get(u), EconomyResponse.ResponseType.SUCCESS, null);
    }
    @Override
    public EconomyResponse depositPlayer(String s, String s1, double v) {
        Player p = Bukkit.getPlayerExact(s);
        assert p != null;
        UUID u = p.getUniqueId();
        ecoHelper.add(u, v);
        return new EconomyResponse(v, ecoHelper.get(u), EconomyResponse.ResponseType.SUCCESS, null);
    }
    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        UUID u = offlinePlayer.getUniqueId();
        ecoHelper.add(u, v);
        return new EconomyResponse(v, ecoHelper.get(u), EconomyResponse.ResponseType.SUCCESS, null);
    }
    @Override
    public EconomyResponse createBank(String s, String s1) {
        return null;
    }
    @Override
    public EconomyResponse createBank(String s, OfflinePlayer offlinePlayer) {
        return null;
    }
    @Override
    public EconomyResponse deleteBank(String s) {
        return null;
    }
    @Override
    public EconomyResponse bankBalance(String s) {
        return null;
    }
    @Override
    public EconomyResponse bankHas(String s, double v) {
        return null;
    }
    @Override
    public EconomyResponse bankWithdraw(String s, double v) {
        return null;
    }
    @Override
    public EconomyResponse bankDeposit(String s, double v) {
        return null;
    }
    @Override
    public EconomyResponse isBankOwner(String s, String s1) {
        return null;
    }
    @Override
    public EconomyResponse isBankOwner(String s, OfflinePlayer offlinePlayer) {
        return null;
    }
    @Override
    public EconomyResponse isBankMember(String s, String s1) {
        return null;
    }
    @Override
    public EconomyResponse isBankMember(String s, OfflinePlayer offlinePlayer) {
        return null;
    }
    @Override
    public List<String> getBanks() {
        return null;
    }
    @Override
    public boolean createPlayerAccount(String s) {
        UUID u = Bukkit.getPlayerExact(s).getUniqueId();
        ecoHelper.createAccount(u);
        return true;
    }
    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer) {
        UUID u = offlinePlayer.getUniqueId();
        ecoHelper.createAccount(u);
        return true;
    }
    @Override
    public boolean createPlayerAccount(String s, String s1) {
        return false;
    }
    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer, String s) {
        return false;
    }
}
