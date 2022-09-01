package com.matt4499.msmp.economy;
import com.matt4499.msmp.Main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.UUID;
public class EconomySetCommand implements CommandExecutor {
    public EcoHelper ecoHelper;
    public EconomySetCommand() {
        ecoHelper = Main.ecoHelper;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player admin = (Player) sender;
        if(!admin.hasPermission("staff.ecoset")) { admin.sendMessage(Main.hex("&aECO &7➠ &4No permission.")); return true; }
        if(args.length != 2) {
            admin.sendMessage(Main.hex("&aECO &7➠ &cSpecify a player name and amount!"));
        } else {
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            UUID u = target.getUniqueId();
            if(!ecoHelper.doesAccountExist(u)) {
                admin.sendMessage(Main.hex("&aECO &7➠ &cThat player doesn't exist!"));
                return true;
            }
            double amountToSet = Double.parseDouble(args[1]);
            ecoHelper.set(u, amountToSet);
            String amountSetFormat = DecimalFormat.getCurrencyInstance().format(amountToSet);
            admin.sendMessage(Main.hex("&aECO &7➠ " + args[0] +" now has &a" + amountSetFormat));
            Main.logToGameLogs("[ECO] ***" + admin.getName() + "*** set ***" + target.getName() +"***'s balance to: " + amountSetFormat);
        }
        return true;
    }
}
