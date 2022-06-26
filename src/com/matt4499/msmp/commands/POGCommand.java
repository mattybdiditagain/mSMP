package com.matt4499.msmp.commands;
import com.matt4499.msmp.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class POGCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        int totalpogs = (int) Main.getGlobalVar("totalpogs");
        totalpogs += 1;
        Main.setGlobalVar("totalpogs", totalpogs);
        Bukkit.broadcastMessage(Main.hex("#dc143c"+p.getDisplayName()+" &7just pogged using #dc143c/pog #dc143c("+totalpogs+"#dc143c)"));
        Main.logToChatChannel(p.getDisplayName()+" just pogged using /pog ("+totalpogs +")");
        return true;
    }
}
