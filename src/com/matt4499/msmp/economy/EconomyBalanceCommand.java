package com.matt4499.msmp.economy;
import com.matt4499.msmp.Main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;
public class EconomyBalanceCommand implements CommandExecutor {
    public EcoHelper ecoHelper;
    public EconomyBalanceCommand() {
        ecoHelper = Main.ecoHelper;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player p = (Player) sender;
        if(args.length == 0) {
            String balance = ecoHelper.getFormatted(p.getUniqueId());
            p.sendMessage(Main.hex("&aECO &7➠ You have &a" + balance));
        } else {
            UUID u = Bukkit.getOfflinePlayer(args[0]).getUniqueId();
            if(ecoHelper.doesAccountExist(u)) {
                String balance = ecoHelper.getFormatted(u);
                p.sendMessage(Main.hex("&aECO &7➠ &e" + args[0].toString() + " &7has &a" + balance));
            } else {
                p.sendMessage(Main.hex("&aECO &7➠ &c" + args[0].toString() + " &chas never joined or doesn't have a bank account."));
            }
            return true;
        }
        return true;
    }
}
