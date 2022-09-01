package com.matt4499.msmp.commands;
import com.matt4499.msmp.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class CCBanCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player admin = (Player) commandSender;
        if(!admin.hasPermission("staff.cc")) { admin.sendMessage(Main.hex("#dc143c&lSTAFF &7➠ #dc143cYou do not have permission to use this command.")); return true; }
        if(args.length == 0) {
            admin.sendMessage(Main.hex("#dc143c&lSTAFF &7➠ #dc143cUsage: /ccban <player>"));
            return true;
        }
        Player target = Bukkit.getPlayerExact(args[0]);
        if(target == null) {
            admin.sendMessage(Main.hex("#dc143c&lSTAFF &7➠ #dc143cPlayer not found."));
            return true;
        }
        admin.performCommand("ban -s " + target.getName() + " Severe Chat Offence");
        for(int i=0; i<250; i++) {
            Bukkit.broadcastMessage(" \n");
        }
        Bukkit.broadcastMessage(Main.hex("#dc143c&lSTAFF &7➠ #dc143cChat cleared by " + admin.getDisplayName() + "."));
        Main.logToGameLogs("[Staff] Chat cleared by " + admin.getDisplayName());
        return true;
    }
}
