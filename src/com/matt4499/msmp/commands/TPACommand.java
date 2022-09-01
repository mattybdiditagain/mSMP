package com.matt4499.msmp.commands;
import com.matt4499.msmp.Main;
import com.matt4499.msmp.types.TPARequest;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
public class TPACommand implements CommandExecutor {
    public static ArrayList<TPARequest> tpas = new ArrayList<>();
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player from = (Player) commandSender;
        if(args.length == 0) {
            from.sendMessage(Main.hex("#dc143c&lTPA &7➠ #dc143cUsage: /tpa <player>"));
            return true;
        }
        Player to = Bukkit.getPlayerExact(args[0]);
        if(to == null) {
            from.sendMessage(Main.hex("#dc143c&lTPA &7➠ #dc143cThat player is not online!"));
        } else {
            if(from == to) {
                from.sendMessage(Main.hex("#dc143c&lTPA &7➠ #dc143cYou cannot teleport to yourself!"));
                return true;
            }
            from.sendMessage(Main.hex("#dc143c&lTPA &7➠ &7You have sent a request to #dc143c" + to.getDisplayName() + "! Expires in 30 seconds."));
            to.sendMessage(Main.hex("#dc143c&lTPA &7➠ #dc143c" + from.getDisplayName() + " &7has sent you a request!"));
            to.sendMessage(Main.hex("#dc143c&lTPA &7➠ &a/tpyes [name] to accept it &7or #dc143cwait 30 seconds to auto-deny it."));
            TPARequest newtpa = new TPARequest(from, to);
            tpas.add(newtpa);
            Main.logToGameLogs("[TPA] " + from.getDisplayName() + " sent a request to " + to.getDisplayName());
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> {
                for(TPARequest tpa : tpas) {
                    if(tpa.from.equals(from) && tpa.to.equals(to)) {
                        tpa.from.sendMessage(Main.hex("#dc143c&lTPA &7➠ &7Your request to " + tpa.to.getDisplayName() + " has #dc143ctimed out"));
                        tpa.to.sendMessage(Main.hex("#dc143c&lTPA &7➠ &7" + tpa.from.getDisplayName() + "'s request to you has #dc143ctimed out"));
                        Main.logToGameLogs("[TPA] " + tpa.from.getDisplayName() + "'s request to " + tpa.to.getDisplayName() + " has timed out");
                        tpas.remove(tpa);
                        break;
                    }
                }
            }, 20 * 30);
        }
        return true;
    }
}
