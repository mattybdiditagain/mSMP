package com.matt4499.msmp.commands;
import com.matt4499.msmp.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class SeenCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player p = (Player) commandSender;
        if(args.length == 0) {
            p.sendMessage("§cUsage: /seen <player>");
            return true;
        } else {
            String username = args[0];
            if(Bukkit.getPlayerExact(username) == null) {
                Object lastSeenData = Main.getData(Bukkit.getOfflinePlayer(username).getUniqueId(), "lastseen");
                if(lastSeenData == null) {
                    p.sendMessage(Main.hex("#dc143cSEEN &f➠ #dc143cPlayer has never joined this server."));
                    return true;
                }
                long lastSeen = (long) lastSeenData;
                long now = System.currentTimeMillis();
                long diff = now - lastSeen;
                long days = diff / (1000 * 60 * 60 * 24);
                long hours = (diff - (days * 1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
                long minutes = (diff - (days * 1000 * 60 * 60 * 24) - (hours * 1000 * 60 * 60)) / (1000 * 60);
                long seconds = (diff - (days * 1000 * 60 * 60 * 24) - (hours * 1000 * 60 * 60) - (minutes * 1000 * 60)) / 1000;
                p.sendMessage(Main.hex("#dc143cSEEN &f➠ #dc143c" + username + " &7was last seen " + days + " days, " + hours + " hours, " + minutes + " minutes, " + seconds + " seconds ago."));
            } else {
                p.sendMessage(Main.hex(("#dc143cSEEN &f➠ " + username + " is currently online!")));
            }
            return true;
        }
    }
}
