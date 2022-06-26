package com.matt4499.msmp.commands;
import com.matt4499.msmp.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class CCCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player admin = (Player) commandSender;
        if(!admin.hasPermission("staff.cc")) { admin.sendMessage(Main.hex("#dc143c&lSTAFF &f➠ #dc143cYou do not have permission to use this command.")); return true; }
        for(int i=0; i<250; i++) {
            Bukkit.broadcastMessage(" \n");
        }
        Bukkit.broadcastMessage(Main.hex("#dc143c&lSTAFF &f➠ &7Chat cleared by " + admin.getDisplayName() + "."));
        Main.logToGameLogs("[Staff] Chat cleared by " + admin.getDisplayName());
        return true;
    }
}
