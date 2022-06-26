package com.matt4499.msmp.commands;
import com.matt4499.msmp.Main;
import com.matt4499.msmp.types.TPARequest;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class TPAAcceptCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player to = (Player) commandSender;
        if(args.length == 0) {
            to.sendMessage(Main.hex("#dc143c&lTPA &f➠ #dc143cUsage: /tpaccept <player>"));
            return true;
        }
        Player from = Bukkit.getPlayerExact(args[0]);
        if(from == null) {
            to.sendMessage(Main.hex("#dc143c&lTPA &f➠ #dc143cThat player is not online!"));
            return true;
        } else {
            for(TPARequest tpa : TPACommand.tpas) {
                if(tpa.from.equals(from) && tpa.to.equals(to)) {
                    tpa.to.sendMessage(Main.hex("#dc143c&lTPA &f➠ &aYou have accepted " + tpa.from.getDisplayName() + "'s request!"));
                    tpa.from.sendMessage(Main.hex("#dc143c&lTPA &f➠ &a" + tpa.to.getDisplayName() + " has accepted your request!"));
                    BackCommand.back.put(from, from.getLocation());
                    from.teleport(tpa.to.getLocation());
                    TPACommand.tpas.remove(tpa);
                    Main.logToGameLogs("[TPA] " + tpa.to.getDisplayName() + " accepted " + tpa.from.getDisplayName() + "'s request");
                    return true;
                }
            }
            to.sendMessage(Main.hex("#dc143c&lTPA &f➠ #dc143cThat player has not sent you a request!"));
        }
        return true;
    }
}
